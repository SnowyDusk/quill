package com.quill.identity.repository;

import com.quill.common.core.constant.RedisPrefix;
import jakarta.annotation.Resource;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Repository;

import java.time.Duration;

/**
 * @description: 管理 Redis 中的注册和登录验证码
 * @author: tuberose
 * @date: 2024/6/1 19:31
 */

@Repository
public class VerificationCodeRepository {

    @Resource
    private RedissonClient redissonClient;

    public void putRegisterVerificationCode(String email, String code) {
        RBucket<String> bucket = redissonClient.getBucket(RedisPrefix.RegisterVerificationCode + email);
        bucket.set(code, Duration.ofMinutes(15));
    }

    public void deleteRegisterVerificationCode(String email) {
        RBucket<String> bucket = redissonClient.getBucket(RedisPrefix.RegisterVerificationCode + email);
        bucket.delete();
    }

    public String getRegisterVerificationCode(String email) {
        RBucket<String> bucket = redissonClient.getBucket(RedisPrefix.RegisterVerificationCode + email);
        return bucket.get();
    }

}
