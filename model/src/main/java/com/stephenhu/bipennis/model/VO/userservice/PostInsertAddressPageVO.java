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
public final class PostInsertAddressPageVO {
    private String uId;
    private String aIsPublic;
    private String city;
    private String latitude;
    private String longitude;
    private String addressLabel;
}
