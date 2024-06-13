package com.quill.identity.service;

import com.quill.api.identity.bo.UserPermitBO;

public interface AuthService {
    /**
     * 按照传入 uid 查询 redis 中的用户登录信息
     */
    UserPermitBO queryUserPermit(String userId);
}
