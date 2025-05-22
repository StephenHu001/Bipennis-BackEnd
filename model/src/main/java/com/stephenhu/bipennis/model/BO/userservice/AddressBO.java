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
public final class AddressBO {
    private String aId;
    private String uId;
    private String aIsPublic;
    private String city;
    private String latitude;
    private String longitude;
    private String addressLabel;
}