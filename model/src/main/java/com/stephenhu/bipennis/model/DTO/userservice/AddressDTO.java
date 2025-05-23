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
public final class AddressDTO {
    private String aId;
    private String aIsPublic;
    private String city;
    private String latitude;
    private String longitude;
    private String addressLabel;
}
