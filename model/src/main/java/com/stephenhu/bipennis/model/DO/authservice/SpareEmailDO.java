package com.stephenhu.bipennis.model.DO.authservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Stephen Hu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class SpareEmailDO{
    private String uId;
    private String seEmail;
}
