package com.quill.api.identity.dubbo;

import com.quill.api.identity.bo.UserPermitBO;

public interface AuthDubboService {
    UserPermitBO queryUserPermit(Long userId);

    UserPermitBO queryUserPermit(String userId);

}
