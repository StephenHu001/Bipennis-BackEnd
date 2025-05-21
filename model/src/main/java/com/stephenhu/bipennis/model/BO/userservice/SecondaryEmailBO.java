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
public final class SecondaryEmailBO {
    private String reId;
    private String uId;
    private String isPublic;
    private String reEmail;
}