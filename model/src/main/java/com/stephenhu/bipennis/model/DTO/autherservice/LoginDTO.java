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
public final class LoginDTO {
    private String account;
    private String originPassword;
}
