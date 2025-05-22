package com.stephenhu.bipennis.model.BO.userservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Stephen Hu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class UserBO {
    private String uId;
    private String uEmail;
    private String uPhone;
    private String hashPassword;
}
