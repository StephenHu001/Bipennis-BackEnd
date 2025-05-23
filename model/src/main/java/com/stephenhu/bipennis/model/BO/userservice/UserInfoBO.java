package com.stephenhu.bipennis.model.BO.userservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* 
* @author Stephen Hu
 * @TableName t_userinfo
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class UserInfoBO{
    private String uiId;
    private String uId;
    private String firstName;
    private String lastName;
    private String gender;
    private String genderIsPublic;
    private String avatar;
    private String birthdate;
    private String birthdateIsPublic;
}
