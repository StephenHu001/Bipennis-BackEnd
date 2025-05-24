package com.stephenhu.bipennis.userservice.service.publicmethod.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stephenhu.bipennis.model.DTO.userservice.AllUserInformationDTO;
import com.stephenhu.bipennis.model.PO.userservice.AddressPO;
import com.stephenhu.bipennis.model.PO.userservice.SecondaryEmailPO;
import com.stephenhu.bipennis.model.PO.userservice.SpareEmailPO;
import com.stephenhu.bipennis.userservice.mapper.AddressMapper;
import com.stephenhu.bipennis.userservice.mapper.SecondaryEmailMapper;
import com.stephenhu.bipennis.userservice.mapper.SpareEmailMapper;
import com.stephenhu.bipennis.userservice.service.publicmethod.PublicMethod;
import com.stephenhu.bipennis.util.Regular.EmailRegular;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Stephen Hu
 */
@Component
public class PublicMethodImpl implements PublicMethod {

    private final AddressMapper addressMapper;

    private final SpareEmailMapper spareEmailMapper;

    private final SecondaryEmailMapper secondaryEmailMapper;

    @Autowired
    public PublicMethodImpl(AddressMapper addressMapper, SpareEmailMapper spareEmailMapper, SecondaryEmailMapper secondaryEmailMapper) {
        this.spareEmailMapper = spareEmailMapper;
        this.addressMapper = addressMapper;
        this.secondaryEmailMapper = secondaryEmailMapper;
    }

    @Override
    public boolean isRightInsertAddress(AllUserInformationDTO allUserInformationDTO) {
        return allUserInformationDTO.getUId() == null ||
                allUserInformationDTO.getUId().trim().isEmpty() ||
                allUserInformationDTO.getAIsPublic() == null ||
                allUserInformationDTO.getAIsPublic().trim().isEmpty() ||
                allUserInformationDTO.getLatitude() == null ||
                allUserInformationDTO.getLatitude().trim().isEmpty() ||
                allUserInformationDTO.getLongitude() == null ||
                allUserInformationDTO.getLongitude().trim().isEmpty() ||
                allUserInformationDTO.getAddressLabel() == null ||
                allUserInformationDTO.getAddressLabel().trim().isEmpty();
    }

    @Override
    public boolean isRightInsertSpareEmail(AllUserInformationDTO allUserInformationDTO) {
        if (allUserInformationDTO.getUId() == null || allUserInformationDTO.getUId().trim().isEmpty() ||
                allUserInformationDTO.getSeIsPublic() == null || allUserInformationDTO.getSeIsPublic().trim().isEmpty() ||
                allUserInformationDTO.getSeEmail() == null || allUserInformationDTO.getSeEmail().trim().isEmpty()) {
            return false;
        }
        if (!EmailRegular.isEmail(allUserInformationDTO.getSeEmail())) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isRightInsertSecondaryEmail(AllUserInformationDTO allUserInformationDTO) {
        if (allUserInformationDTO.getUId() == null || allUserInformationDTO.getUId().trim().isEmpty() ||
                allUserInformationDTO.getReEmail() == null || allUserInformationDTO.getReEmail().trim().isEmpty() ||
                allUserInformationDTO.getReIsPublic() == null || allUserInformationDTO.getReIsPublic().trim().isEmpty()) {
            return false;
        }
        if (!EmailRegular.isEmail(allUserInformationDTO.getReEmail())) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isRightDeleteAddress(AllUserInformationDTO allUserInformationDTO) {
        if (allUserInformationDTO.getAId() == null || allUserInformationDTO.getAId().trim().isEmpty()) {
            return false;
        }

        QueryWrapper<AddressPO> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("a_id", allUserInformationDTO.getAId());

        return addressMapper.exists(queryWrapper);
    }

    @Override
    public boolean isRightDeleteSpareEmail(AllUserInformationDTO allUserInformationDTO) {
        if (allUserInformationDTO.getSeId() == null || allUserInformationDTO.getSeId().trim().isEmpty()) {
            return false;
        }
        QueryWrapper<SpareEmailPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("se_id", allUserInformationDTO.getSeId());
        return spareEmailMapper.exists(queryWrapper);
    }

    @Override
    public boolean isRightDeleteSecondaryEmail(AllUserInformationDTO allUserInformationDTO) {
        if (allUserInformationDTO.getReId() == null || allUserInformationDTO.getReId().trim().isEmpty()) {
            return false;
        }
        QueryWrapper<SecondaryEmailPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("re_id", allUserInformationDTO.getReId());
        return secondaryEmailMapper.exists(queryWrapper);
    }

    @Override
    public boolean isRightUpdateAddress(AllUserInformationDTO allUserInformationDTO) {
        QueryWrapper<AddressPO> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("a_id", allUserInformationDTO.getAId());

        return allUserInformationDTO.getAIsPublic() != null &&
                !allUserInformationDTO.getAIsPublic().trim().isEmpty() &&
                allUserInformationDTO.getLatitude() != null &&
                !allUserInformationDTO.getLatitude().trim().isEmpty() &&
                allUserInformationDTO.getLongitude() != null &&
                !allUserInformationDTO.getLongitude().trim().isEmpty() &&
                allUserInformationDTO.getAddressLabel() != null &&
                !allUserInformationDTO.getAddressLabel().trim().isEmpty() &&
                addressMapper.exists(queryWrapper);
    }

    @Override
    public boolean isRightUpdateName(AllUserInformationDTO allUserInformationDTO) {
        return
                // 必须为非null或空白字符串的字段
                (allUserInformationDTO.getUId() != null && !StringUtils.isBlank(allUserInformationDTO.getUId())) &&
                        (allUserInformationDTO.getLastName() != null && !StringUtils.isBlank(allUserInformationDTO.getLastName())) &&
                        // 必须为null或空白字符串的字段
                        StringUtils.isBlank(allUserInformationDTO.getAvatar()) &&
                        StringUtils.isBlank(allUserInformationDTO.getGender()) &&
                        StringUtils.isBlank(allUserInformationDTO.getGenderIsPublic()) &&
                        StringUtils.isBlank(allUserInformationDTO.getBirthdate()) &&
                        StringUtils.isBlank(allUserInformationDTO.getBirthdateIsPublic());
    }

    @Override
    public boolean isRightUpdateGender(AllUserInformationDTO allUserInformationDTO) {
        return
                // 必须为非null或空白字符串的字段
                (allUserInformationDTO.getUId() != null && !StringUtils.isBlank(allUserInformationDTO.getUId())) &&
                        (allUserInformationDTO.getGender() != null && !StringUtils.isBlank(allUserInformationDTO.getGender())) &&
                        (allUserInformationDTO.getGenderIsPublic() != null && !StringUtils.isBlank(allUserInformationDTO.getGenderIsPublic())) &&
                        // 必须为null或空白字符串的字段
                        StringUtils.isBlank(allUserInformationDTO.getAvatar()) &&
                        StringUtils.isBlank(allUserInformationDTO.getLastName()) &&
                        StringUtils.isBlank(allUserInformationDTO.getFirstName()) &&
                        StringUtils.isBlank(allUserInformationDTO.getBirthdate()) &&
                        StringUtils.isBlank(allUserInformationDTO.getBirthdateIsPublic());
    }

    @Override
    public boolean isRightUpdateBirthdate(AllUserInformationDTO allUserInformationDTO) {
        return
                // 必须为非null或空白字符串的字段
                (allUserInformationDTO.getUId() != null && !StringUtils.isBlank(allUserInformationDTO.getUId())) &&
                        (allUserInformationDTO.getBirthdate() != null && !StringUtils.isBlank(allUserInformationDTO.getBirthdate())) &&
                        (allUserInformationDTO.getBirthdateIsPublic() != null && !StringUtils.isBlank(allUserInformationDTO.getBirthdateIsPublic())) &&
                        // 必须为null或空白字符串的字段
                        StringUtils.isBlank(allUserInformationDTO.getGender()) &&
                        StringUtils.isBlank(allUserInformationDTO.getGenderIsPublic()) &&
                        StringUtils.isBlank(allUserInformationDTO.getLastName()) &&
                        StringUtils.isBlank(allUserInformationDTO.getFirstName()) &&
                        StringUtils.isBlank(allUserInformationDTO.getAvatar());
    }

    @Override
    public boolean isRightUpdatePhone(AllUserInformationDTO allUserInformationDTO) {
        return
                // 必须为非null或空白字符串的字段
                (allUserInformationDTO.getUId() != null && !StringUtils.isBlank(allUserInformationDTO.getUId())) &&
                        (allUserInformationDTO.getUPhone() != null && !StringUtils.isBlank(allUserInformationDTO.getUPhone())) &&
                        // 必须为null或空白字符串的字段
                        StringUtils.isBlank(allUserInformationDTO.getHashPassword()) &&
                        StringUtils.isBlank(allUserInformationDTO.getUEmail());
    }

    @Override
    public boolean isRightUpdatePassword(AllUserInformationDTO allUserInformationDTO) {
        return
                // 必须为非null或空白字符串的字段
                (allUserInformationDTO.getUId() != null && !StringUtils.isBlank(allUserInformationDTO.getUId())) &&
                        (allUserInformationDTO.getHashPassword() != null && !StringUtils.isBlank(allUserInformationDTO.getHashPassword())) &&
                        // 必须为null或空白字符串的字段
                        StringUtils.isBlank(allUserInformationDTO.getUPhone()) &&
                        StringUtils.isBlank(allUserInformationDTO.getUEmail());
    }

    @Override
    public boolean isRightUpdateSpareEmail(AllUserInformationDTO allUserInformationDTO) {
        if (allUserInformationDTO.getSeId() == null || allUserInformationDTO.getSeId().trim().isEmpty() ||
                allUserInformationDTO.getUId() == null || allUserInformationDTO.getUId().trim().isEmpty() ||
                allUserInformationDTO.getSeIsPublic() == null || allUserInformationDTO.getSeIsPublic().trim().isEmpty() ||
                allUserInformationDTO.getSeEmail() == null || allUserInformationDTO.getSeEmail().trim().isEmpty()) {
            return false;
        }
        if (!EmailRegular.isEmail(allUserInformationDTO.getSeEmail())) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isRightUpdateSecondaryEmail(AllUserInformationDTO allUserInformationDTO) {
        if (allUserInformationDTO.getReId() == null || allUserInformationDTO.getReId().trim().isEmpty() ||
                allUserInformationDTO.getReIsPublic() == null || allUserInformationDTO.getReIsPublic().trim().isEmpty() ||
                allUserInformationDTO.getReEmail() == null || allUserInformationDTO.getReEmail().trim().isEmpty()) {
            return false;
        }
        if (!EmailRegular.isEmail(allUserInformationDTO.getReEmail())) {
            return false;
        }
        return true;
    }


}
