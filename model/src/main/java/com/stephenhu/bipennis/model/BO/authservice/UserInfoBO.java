package com.stephenhu.bipennis.model.BO.authservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Stephen Hu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class UserInfoBO {
    private String uId;
    private String firstName;
    private String lastName;
    private String gender;
    private String avatar;
    private String birthdate;
}
