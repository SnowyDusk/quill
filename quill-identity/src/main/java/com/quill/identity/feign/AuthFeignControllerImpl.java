package com.quill.identity.feign;

import com.quill.api.identity.bo.UserPermitBO;
import com.quill.api.identity.feign.AuthFeignClient;
import com.quill.common.core.response.QuillResponse;
import com.quill.identity.service.AuthService;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: TODO
 * @author: tuberose
 * @date: 2024/6/9 19:42
 */

@RestController
public class AuthFeignControllerImpl implements AuthFeignClient {

    private static final Logger log = LoggerFactory.getLogger(AuthFeignControllerImpl.class);
    @Resource
    private AuthService authService;

    @Override
    public QuillResponse<UserPermitBO> queryUserPermit(String userId) {
        UserPermitBO userPermitBO = authService.queryUserPermit(userId);
        log.info("查询用户 {} 的登录信息: {}", userId, userPermitBO);
        return QuillResponse.success(userPermitBO);
    }
}
