package com.quill.common.security.config;

import com.quill.common.security.interceptor.AuthInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;


/**
 * @author: tuberose
 * @date: 2024/6/6 12:37
 */
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    private static final List<String> excludePathPatterns = new ArrayList<>();
    static {
        excludePathPatterns.add("/welcome/**");
    }


    @Resource
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(excludePathPatterns);
    }
}
