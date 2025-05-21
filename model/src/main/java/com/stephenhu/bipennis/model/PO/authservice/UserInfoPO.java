package com.stephenhu.bipennis.model.PO.authservice;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import java.time.LocalDate;
import java.util.Date;

/**
* 
* @author Stephen Hu
 * @TableName t_userinfo
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_userinfo")
@Entity
public final class UserInfoPO {
    @Id
    private String uinfoId;
    private String uId;
    private String firstName;
    private String lastName;
    private String gender;
    private String genderIsPublic;
    private String avatar;
    private String birthdate;
    private String birthdateIsPublic;
    private String createAt;
    private String updateAt;
    private String reservedBigint1;
    private String reservedBigint2;
    private String reservedBigint3;
    private String reservedBigint4;
    private String reservedDecimal1;
    private String reservedDecimal2;
    private String reservedDecimal3;
    private String reservedDecimal4;
    private String reservedVarchar1;
    private String reservedVarchar2;
    private String reservedVarchar3;
    private String reservedVarchar4;
    private String reservedDatetime1;
    private String reservedDatetime2;
    private String reservedDatetime3;
    private String reservedDatetime4;
}
