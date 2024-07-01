package com.quill.identity.service.impl;

import com.demo.api.mail.bo.RegisterVerificationCodeMailBO;
import com.quill.api.identity.bo.UserPermitBO;
import com.quill.api.identity.constant.UserIdentity;
import com.quill.api.identity.constant.UserStatus;
import com.quill.api.identity.vo.UserTokenVO;
import com.quill.common.core.constant.ResponseStatus;
import com.quill.common.core.exception.QuillException;
import com.quill.common.util.SnowFlake;
import com.quill.common.util.VerificationCode;
import com.quill.identity.constant.Gender;
import com.quill.identity.dto.LoginDTO;
import com.quill.identity.dto.RegisterDTO;
import com.quill.identity.mapper.UserMapper;
import com.quill.identity.model.User;
import com.quill.identity.repository.UserTokenRepository;
import com.quill.identity.repository.VerificationCodeRepository;
import com.quill.identity.service.UserService;
import com.quill.identity.util.CheckUtil;
import constant.RocketMQTopic;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author: tuberose
 * @date: 2024/6/1 18:39
 */

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private VerificationCodeRepository verificationCodeRepository;

    @Resource
    private UserTokenRepository userTokenRepository;

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public void sendRegisterVerificationCode(String email) {

        // 验证邮箱是否已经被注册
        User user = userMapper.selectByEmail(email);
        if (user != null) {
            log.info("邮箱 {} 已被注册", email);
            throw new QuillException(ResponseStatus.EMAIL_EXIST);
        }

        String verificationCode = VerificationCode.generate();
        log.info("邮箱 {} 生成注册验证码: {}", email, verificationCode);

        // 发送邮件
        RegisterVerificationCodeMailBO mailBO = new RegisterVerificationCodeMailBO(email, verificationCode);
        rocketMQTemplate.syncSend(RocketMQTopic.REGISTER_MAIL, mailBO);

        // 验证码放入 redis, 15分钟有效
        verificationCodeRepository.putRegisterVerificationCode(email, verificationCode);
        log.info("调用远程服务向邮箱 {} 发送注册验证码: {}", email, verificationCode);
    }

    @Override
    public void register(RegisterDTO registerDTO) {
        String verificationCode = verificationCodeRepository.getRegisterVerificationCode(registerDTO.getEmail());
        if (verificationCode == null || verificationCode.isBlank()) {
            log.info("找不到 {} 对应的注册验证码", registerDTO.getEmail());
            throw new QuillException(ResponseStatus.VERIFICATION_CODE_MISS);
        }

        // 比对验证码
        if (!verificationCode.equals(registerDTO.getVerificationCode())) {
            log.info("{} 注册验证码错误", registerDTO.getEmail());
            throw new QuillException(ResponseStatus.VERIFICATION_CODE_ERROR);
        }

        User user = new User();
        user.setUserId(SnowFlake.nextId());
        user.setName(registerDTO.getName());
        user.setEmail(registerDTO.getEmail());
        user.setAvatar(null);
        user.setGender(Gender.UNKNOWN);
        user.setIdentity(UserIdentity.READER);
        user.setStatus(UserStatus.ACTIVE);

        CheckUtil.passwordValidityCheck(registerDTO.getPassword()); // 检查密码有效性
        String encodedPassword = passwordEncoder.encode(registerDTO.getPassword()); // 密码加密存储
        user.setPassword(encodedPassword);

        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());

        userMapper.insert(user);

        // 删除验证码
        verificationCodeRepository.deleteRegisterVerificationCode(registerDTO.getEmail());

    }

    @Override
    public UserTokenVO login(LoginDTO loginDTO) {

        // 查询登录用户信息
        User user = userMapper.selectByEmail(loginDTO.getEmail());
        if (user == null) {
            log.info("邮箱 {} 未注册", loginDTO.getEmail());
            throw new QuillException(ResponseStatus.EMAIL_NOT_EXIST);
        }

        // 判断密码是否匹配
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            log.info("邮箱 {} 密码错误", user.getEmail());
            throw new QuillException(ResponseStatus.PASSWORD_ERROR);
        }

        // 生成用户登录令牌
        String utoken = UUID.randomUUID().toString();

        UserPermitBO userPermitBO = new UserPermitBO();
        userPermitBO.setUserId(user.getUserId());
        userPermitBO.setUtoken(utoken);
        userPermitBO.setIdentity(user.getIdentity());
        userTokenRepository.putUserToken(userPermitBO);

        return new UserTokenVO(user.getUserId(), utoken);
    }

    @Override
    public void logout(Long userId) {
        // 删除 redis 中的用户令牌
        userTokenRepository.deleteUserToken(userId);

        log.info("用户 {} 退出登录", userId);
    }

    @Override
    public void logoff(Long userId) {
        // 删除数据库 user 表数据
        userMapper.deleteByUserId(userId);

        userTokenRepository.deleteUserToken(userId);

        log.info("用户 {} 注销账户", userId);
    }

    @Override
    public void activeAuthorIdentity(Long userId) {
        User user = userMapper.selectByUserId(userId);
        if (user == null) {
            throw new QuillException(ResponseStatus.USER_NOT_EXIST);
        }

        userMapper.updateIdentity(userId, user.getIdentity() | UserIdentity.AUTHOR);
    }
}
