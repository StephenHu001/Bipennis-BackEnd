package com.stephenhu.bipennis.model.PO.userservice;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Stephen Hu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_address")
@Entity
public final class AddressPO {
    @Id
    private String aId;
    private String uId;
    private String isPublic;
    private String countryCode;
    private String stateProvince;
    private String city;
    private String district;
    private String streetAddress;
    private String postalCode;
    private String latitude;
    private String longitude;
    private String addressLabel;
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