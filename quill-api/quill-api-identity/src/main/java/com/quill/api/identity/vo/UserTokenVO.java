package com.quill.api.identity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: tuberose
 * @date: 2024/6/5 22:41
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTokenVO {

    private Long userId;

    private String utoken;
}
