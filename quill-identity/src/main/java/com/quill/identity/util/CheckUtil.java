package com.quill.identity.util;

import com.quill.common.core.constant.ResponseStatus;
import com.quill.common.core.exception.QuillException;

/**
 * @description: 各种检查方法
 * @author: tuberose
 * @date: 2024/6/2 15:45
 */
public class CheckUtil {

    /**
     * 检查密码是否符合规范
     */
    public static void passwordValidityCheck(String password) {
        // 密码不可包含空格
        if (password.contains(" ")) {
            throw new QuillException(ResponseStatus.PASSWORD_CONTAINS_SPACE);
        }

        // 密码长度不得小于6
        if (password.length() < 6) {
            throw new QuillException(ResponseStatus.SHORT_PASSWORD);
        }

        // 密码长度不得大于32
        if (password.length() > 32) {
            throw new QuillException(ResponseStatus.LONG_PASSWORD);
        }
    }
}
