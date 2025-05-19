package com.stephenhu.bipennis.model.VO.authservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Stephen Hu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class RegisterPageVO {
    private String firstName;
    private String lastName;
    private String uEmail;
    private String uPhone;
    private String birthdate;
    private String hashPassword;
}
