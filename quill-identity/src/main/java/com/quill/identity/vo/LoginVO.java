package com.quill.identity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: tuberose
 * @date: 2024/6/2 16:14
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginVO {
    private Long userId;
    private String utoken;
}
