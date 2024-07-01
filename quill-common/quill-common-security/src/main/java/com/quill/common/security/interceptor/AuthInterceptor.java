package com.quill.common.security.interceptor;

import com.quill.api.identity.bo.UserPermitBO;
import com.quill.api.identity.feign.AuthFeignClient;
import com.quill.common.core.feign.FeignAuthConfig;
import com.quill.common.core.response.QuillResponse;
import com.quill.common.security.context.UserHolder;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author: tuberose
 * @date: 2024/6/6 12:24
 */

@Component
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    @Resource
    private AuthFeignClient authFeignClient;
    
    @Resource
    private FeignAuthConfig feignAuthConfig;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String path = request.getRequestURI();
        log.info("收到访问 {} 的请求", path);

        // feign 校验
        if (feignRequestCheck(request)) {
            log.info("访问 {} 的请求为 feign 请求", path);
            return true;
        }

        // 获取请求头对应的验证字段 uid 和 utoken
        String uid = request.getHeader("uid");
        String utoken = request.getHeader("utoken");

        if (uid == null || uid.isBlank() || utoken == null || utoken.isBlank()) {
            log.info("请求 {} 未携带身份令牌", path);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

        QuillResponse<UserPermitBO> userPermitResponse = authFeignClient.queryUserPermit(uid);
        if (userPermitResponse == null || userPermitResponse.getCode() != 0) {
            log.info("请求 {} 携带失效的身份令牌", path);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
        UserPermitBO userPermitBO = userPermitResponse.getData();
        if (userPermitBO == null || !utoken.equals(userPermitBO.getUtoken())) {
            log.info("请求 {} 携带错误的身份令牌", path);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

        // 将用户id保存到ThreadLocal
        UserHolder.set(userPermitBO);
        log.info("请求 {} 通过身份验证", path);
        return true;
    }

    /**
     * 检查是否为内部 feign 调用
     */
    private boolean feignRequestCheck(HttpServletRequest request) {
        if (!request.getRequestURI().startsWith(FeignAuthConfig.FEIGN_URL_PREFIX)) {
            // 判断路径 是否为 feign 请求
            return false;
        }

        String feignSecret = request.getHeader(feignAuthConfig.getKey());
        // 判断请求头中携带的 token
        return feignSecret != null && feignSecret.equals(feignAuthConfig.getSecret());
    }
}
