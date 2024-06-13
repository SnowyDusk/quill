package com.quill.identity.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: tuberose
 * @date: 2024/6/1 18:29
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    @NotBlank(message = "邮箱不可为空")
    private String email;

    @NotBlank(message = "密码不可为空")
    private String password;
}
