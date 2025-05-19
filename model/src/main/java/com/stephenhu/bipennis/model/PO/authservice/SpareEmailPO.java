package com.stephenhu.bipennis.model.PO.authservice;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Stephen Hu
 * &#064;TableName  s_email
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "s_email")
@Entity
public final class SpareEmailPO implements Serializable {
    @Id
    private String seId;
    private String uId;
    private String isPublic;
    private String seEmail;
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
