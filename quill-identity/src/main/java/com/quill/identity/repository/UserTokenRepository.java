package com.quill.identity.repository;

import com.quill.api.identity.bo.UserPermitBO;
import com.quill.common.core.constant.RedisPrefix;
import jakarta.annotation.Resource;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Repository;

import java.time.Duration;

/**
 * @author: tuberose
 * @date: 2024/6/2 16:06
 */

@Repository
public class UserTokenRepository {
    @Resource
    private RedissonClient redissonClient;

    public void putUserToken(UserPermitBO userPermitBO) {
        RBucket<UserPermitBO> bucket = redissonClient.getBucket(RedisPrefix.LoginVerificationCode + userPermitBO.getUserId());
        bucket.set(userPermitBO, Duration.ofDays(14));
    }

    public void deleteUserToken(Long userId) {
        RBucket<UserPermitBO> bucket = redissonClient.getBucket(RedisPrefix.LoginVerificationCode + userId);
        bucket.delete();
    }

    public UserPermitBO getUserToken(Long userId) {
        RBucket<UserPermitBO> bucket = redissonClient.getBucket(RedisPrefix.LoginVerificationCode + userId);
        return bucket.get();
    }
}
