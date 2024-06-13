package com.quill.mail.service.impl;

import com.demo.api.mail.bo.RegisterVerificationCodeMailBO;
import com.quill.mail.service.MailService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @description: 邮件服务
 * @author: tuberose
 * @date: 2024/6/11 18:27
 */
@Service
@Slf4j
public class MailServiceImpl implements MailService {

    private static final String MAIL_ADDR = "snowy_dusk@163.com";

    @Resource
    private JavaMailSender javaMailSender;

    @Override
    public void sendRegisterVerificationCode(RegisterVerificationCodeMailBO mailBO) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(MAIL_ADDR);
        mailMessage.setTo(mailBO.getTo());
        mailMessage.setSubject("【Quill】注册验证码");
        String text = "感谢您关注Quill，您的注册验证码为： " + mailBO.getVerificationCode()
                + "\n\n验证码15分钟内有效，请您尽快完成注册。祝您使用愉快！";
        mailMessage.setText(text);

        javaMailSender.send(mailMessage);
        log.info("向 {} 发送注册验证码", mailBO.getTo());
    }
}
