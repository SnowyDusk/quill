package com.quill.identity.service;

import com.quill.api.identity.vo.UserTokenVO;
import com.quill.identity.dto.LoginDTO;
import com.quill.identity.dto.RegisterDTO;

public interface UserService {

    /**
     * 向指定邮箱发送注册验证码
     */
    void sendRegisterVerificationCode(String email);


    /**
     * 用户注册
     */
    void register(RegisterDTO registerDTO);

    /**
     * 用户登录
     */

    UserTokenVO login(LoginDTO loginDTO);

    /**
     * 退出登录
     */
    void logout(Long userId);

    /**
     * 注销账户
     */
    void logoff(Long userId);

}
