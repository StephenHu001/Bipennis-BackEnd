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
public final class AllUserInformationDTO {
    private String uId;
    private String firstName;
    private String lastName;
    private String aId;
    private String aIsPublic;
    private String city;
    private String latitude;
    private String longitude;
    private String addressLabel;
    private String gender;
    private String genderIsPublic;
    private String avatar;
    private String birthdate;
    private String birthdateIsPublic;
    private String uEmail;
    private String uPhone;
    private String hashPassword;
    private String seId;
    private String seIsPublic;
    private String seEmail;
    private String reId;
    private String reIsPublic;
    private String reEmail;
}
