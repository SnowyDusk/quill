package com.quill.common.util;

import java.util.Random;

/**
 * @description: 生成随机验证码
 * @author: tuberose
 * @date: 2024/6/1 19:41
 */
public class VerificationCode {

    private static final Random random = new Random();

    public static String generate() {
        return String.valueOf(random.nextInt(1000, 9999));
    }
}
