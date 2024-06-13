package com.quill.identity.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: tuberose
 * @date: 2024/6/1 18:26
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    @NotBlank(message = "用户昵称不可为空")
    private String name;

    @NotBlank(message = "邮箱不可为空")
    private String email;

    @NotBlank(message = "密码不可为空")
    private String password;

    @NotBlank(message = "验证码不可为空")
    private String verificationCode;    // 验证码
}
