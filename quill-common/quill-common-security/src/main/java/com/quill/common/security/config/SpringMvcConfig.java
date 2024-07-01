package com.quill.common.security.config;

import com.quill.common.security.interceptor.AuthInterceptor;
import com.quill.common.security.interceptor.AuthorInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @author: tuberose
 * @date: 2024/6/6 12:37
 */
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {


    @Resource
    private AuthInterceptor authInterceptor;

    @Resource
    private AuthorInterceptor authorInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 用户登录信息过滤器
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/welcome/**");

        // 作家专区过滤器
        registry.addInterceptor(authorInterceptor)
                .addPathPatterns("/identity/author/")
                .excludePathPatterns("/identity/author/welcome/**");

    }
}
