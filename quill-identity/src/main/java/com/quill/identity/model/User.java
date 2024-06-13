package com.quill.identity.model;

import com.quill.identity.dto.RegisterDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author: tuberose
 * @date: 2024/6/1 13:23
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long userId;

    private String name;
    private String email;

    private String avatar;  // 头像路径
    private Integer gender;     // 性别   1: male 2: female

    private Integer identity;   // 身份
    private Integer status;     // 用户状态

    private String password;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
