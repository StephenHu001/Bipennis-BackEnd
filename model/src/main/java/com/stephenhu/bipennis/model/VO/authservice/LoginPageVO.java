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
public final class LoginPageVO {
    private String account;
    private String originPassword;
}
