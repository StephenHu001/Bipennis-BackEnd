package com.stephenhu.bipennis.model.BO.userservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Stephen Hu
 * &#064;TableName  s_email
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class SpareEmailBO{
    private String seId;
    private String uId;
    private String seIsPublic;
    private String seEmail;
}
