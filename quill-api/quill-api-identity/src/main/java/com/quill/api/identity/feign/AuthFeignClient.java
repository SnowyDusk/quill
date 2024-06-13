package com.quill.api.identity.feign;

import com.quill.api.identity.bo.UserPermitBO;
import com.quill.common.core.feign.FeignAuthConfig;
import com.quill.common.core.response.QuillResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "auth")
public interface AuthFeignClient {

    @GetMapping(FeignAuthConfig.FEIGN_URL_PREFIX + "/auth/userPermit")
    QuillResponse<UserPermitBO> queryUserPermit(String userId);

}
