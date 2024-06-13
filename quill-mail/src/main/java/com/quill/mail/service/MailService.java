package com.quill.mail.service;

import com.demo.api.mail.bo.RegisterVerificationCodeMailBO;

public interface MailService {

    /**
     * 发送注册验证码
     */
    void sendRegisterVerificationCode(RegisterVerificationCodeMailBO mailBO);
}
