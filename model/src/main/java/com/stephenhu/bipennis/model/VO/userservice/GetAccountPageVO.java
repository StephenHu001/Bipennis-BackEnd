package com.stephenhu.bipennis.model.VO.userservice;

import com.stephenhu.bipennis.model.DTO.userservice.AddressDTO;
import com.stephenhu.bipennis.model.DTO.userservice.SecondaryEmailDTO;
import com.stephenhu.bipennis.model.DTO.userservice.SpareEmailDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Stephen Hu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class GetAccountPageVO {
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
