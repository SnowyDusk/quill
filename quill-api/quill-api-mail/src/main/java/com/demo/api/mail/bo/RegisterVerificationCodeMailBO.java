package com.demo.api.mail.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: TODO
 * @author: tuberose
 * @date: 2024/6/11 19:03
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterVerificationCodeMailBO {

    private String to;

    private String verificationCode;

}
