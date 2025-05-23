package com.stephenhu.bipennis.model.DTO.userservice;

import com.stephenhu.bipennis.model.BO.authservice.SpareEmailBO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Stephen Hu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class UserInformationDTO {
    private String uId;
    private String uEmail;
    private String uPhone;
    private String firstName;
    private String lastName;
    private String gender;
    private String genderIsPublic;
    private String avatar;
    private String birthdate;
    private String birthdateIsPublic;

    private List<SpareEmailDTO> spareEmailDTOList;

    private List<SecondaryEmailDTO> secondaryEmailDTOList;

    private List<AddressDTO> addressDTOList;

}
