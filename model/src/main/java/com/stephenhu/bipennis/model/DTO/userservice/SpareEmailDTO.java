package com.stephenhu.bipennis.model.DTO.userservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Stephen Hu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class SpareEmailDTO {
    private String seId;
    private String seIsPublic;
    private String seEmail;
}
