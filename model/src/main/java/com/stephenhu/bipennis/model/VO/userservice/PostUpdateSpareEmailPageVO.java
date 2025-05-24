package com.stephenhu.bipennis.model.VO.userservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Stephen Hu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class PostUpdateSpareEmailPageVO {
    private String seId;
    private String seIsPublic;
    private String seEmail;
}
