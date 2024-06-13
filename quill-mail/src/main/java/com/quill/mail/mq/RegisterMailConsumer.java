package com.quill.mail.mq;

import com.demo.api.mail.bo.RegisterVerificationCodeMailBO;
import com.quill.mail.repository.RegisterVerificationCodeRepository;
import com.quill.mail.service.MailService;
import constant.RocketMQTopic;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @description: 注册验证码邮件任务消费者
 * @author: tuberose
 * @date: 2024/6/12 11:09
 */

@Component
@Slf4j
@RocketMQMessageListener(topic = RocketMQTopic.MAIL_TOPIC,
                        consumerGroup = "mail",
                        selectorExpression = RocketMQTopic.REGISTER_TAG)
public class RegisterMailConsumer implements RocketMQListener<RegisterVerificationCodeMailBO> {

    @Resource
    private RegisterVerificationCodeRepository verificationCodeRepository;

    @Resource
    private MailService mailService;

    @Override
    public void onMessage(RegisterVerificationCodeMailBO mailBO) {
        String to = mailBO.getTo();
        log.info("收到向 {} 发送注册验证码的消息", to);
        // 判断短时间内的重复请求
        verificationCodeRepository.existRegisterVerificationCodeMail(to);
        mailService.sendRegisterVerificationCode(mailBO);
    }
}
