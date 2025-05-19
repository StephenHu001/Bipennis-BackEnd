package com.stephenhu.bipennis.model.DTO.autherservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Stephen Hu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class RegisterDTO {
    private String firstName;
    private String lastName;
    private String uEmail;
    private String uPhone;
    private String birthdate;
    private String hashPassword;
}
