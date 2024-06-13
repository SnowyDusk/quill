package com.quill.api.identity.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 需要存入 ThreadLocal 的用户信息
 * @author: tuberose
 * @date: 2024/6/3 23:17
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPermitBO {

    private Long userId;

    private String utoken;

    private Integer identity;       // 具体信息见 UserIdentity 类

}
