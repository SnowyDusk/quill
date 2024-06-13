package com.quill.common.security.context;

import com.quill.api.identity.bo.UserPermitBO;

/**
 * @author: tuberose
 * @date: 2024/6/3 23:14
 */
public class UserHolder {
    private static final ThreadLocal<UserPermitBO> UID = new ThreadLocal<>();

    public static UserPermitBO get() {
        return UID.get();
    }

    public static void set(UserPermitBO userPermitBO) {
        UID.set(userPermitBO);
    }
}
