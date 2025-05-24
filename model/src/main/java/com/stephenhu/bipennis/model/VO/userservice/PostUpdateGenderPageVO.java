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
public final class PostUpdateGenderPageVO {
    private String uId;
    private String gender;
    private String genderIsPublic;
}
