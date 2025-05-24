package com.stephenhu.bipennis.model.VO.userservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Stephen Hu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class PostUpdateBirthdatePageVO {
    private String uId;
    private String birthdate;
    private String birthdateIsPublic;
}
