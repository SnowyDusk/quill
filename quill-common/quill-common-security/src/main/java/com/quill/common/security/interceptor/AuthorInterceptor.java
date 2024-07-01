package com.quill.common.security.interceptor;

import com.quill.api.identity.bo.UserPermitBO;
import com.quill.api.identity.constant.UserIdentity;
import com.quill.common.security.context.UserHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @description: 作者专区拦截器
 * @author: tuberose
 * @date: 2024/6/18 16:57
 */

@Component
public class AuthorInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        // 获取认证信息
        UserPermitBO userPermitBO = UserHolder.get();

        return (userPermitBO.getIdentity() & UserIdentity.AUTHOR) == UserIdentity.AUTHOR;
    }
}
