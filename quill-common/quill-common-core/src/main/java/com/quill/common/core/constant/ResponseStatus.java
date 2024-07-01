package com.quill.common.core.constant;

import lombok.Getter;

@Getter
public enum ResponseStatus {

    SUCCESS(0, "成功"),
    UNKNOWN_ERROR(-1, "未知错误"),

    EMAIL_EXIST(1001, "邮箱已被注册"),
    VERIFICATION_CODE_MISS(1002, "验证码失效"),
    VERIFICATION_CODE_ERROR(1003, "验证码错误"),
    PASSWORD_CONTAINS_SPACE(1004, "密码包含空格"),
    SHORT_PASSWORD(1005, "密码长度小于6"),
    LONG_PASSWORD(1006, "密码长度大于32"),
    EMAIL_NOT_EXIST(1007, "邮箱未注册"),
    PASSWORD_ERROR(1008, "密码错误"),
    USER_TOKEN_ERROR(1009, "登录信息错误"),
    USER_NOT_EXIST(1010, "用户不存在"),

    AUTHOR_NAME_EXIST(1101, "笔名已存在"),

    REQUEST_PARAM_ERROR(9001, "参数错误")
    ;

    private final int code;
    private final String msg;

    ResponseStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
