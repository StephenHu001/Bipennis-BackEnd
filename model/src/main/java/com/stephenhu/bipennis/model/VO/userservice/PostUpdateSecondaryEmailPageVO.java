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
public final class PostUpdateSecondaryEmailPageVO {
    private String reId;
    private String reIsPublic;
    private String reEmail;
}
