package com.quill.mail.repository;

import com.quill.common.core.constant.RedisPrefix;
import jakarta.annotation.Resource;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Repository;

import java.time.Duration;

/**
 * @description: 用于注册验证码防刷, 60s内不允许二次请求
 * @author: tuberose
 * @date: 2024/6/12 23:21
 */

@Repository
public class RegisterVerificationCodeRepository {

    @Resource
    private RedissonClient redissonClient;

    public void putRegisterVerificationCodeMail(String email) {
        RBucket<String> bucket = redissonClient.getBucket(RedisPrefix.RegisterVerificationCodeMail + email);
        bucket.set(" ", Duration.ofMinutes(1));
    }

    public void deleteRegisterVerificationCodeMail(String email) {
        RBucket<String> bucket = redissonClient.getBucket(RedisPrefix.RegisterVerificationCodeMail + email);
        bucket.delete();
    }

    public boolean existRegisterVerificationCodeMail(String email) {
        RBucket<String> bucket = redissonClient.getBucket(RedisPrefix.RegisterVerificationCodeMail + email);
        return bucket.isExists();
    }
}
