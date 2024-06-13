package com.quill.common.core.feign;

import lombok.Getter;
import org.springframework.context.annotation.Configuration;



@Getter
@Configuration
public class FeignAuthConfig {

    public static final String FEIGN_URL_PREFIX = "/feign";

//    @Value("${auth.feign.key}")
    private final String key = "quill-root";

//    @Value("${auth.feign.secret}")
    private final String secret = "quill-root";


}
