package com.stephenhu.bipennis.model.BO.userservice;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Stephen Hu
 * &#064;TableName  s_email
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class SpareEmailBO implements Serializable {
    private String seId;
    private String uId;
    private String isPublic;
    private String seEmail;
}
