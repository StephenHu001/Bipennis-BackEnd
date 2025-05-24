package com.stephenhu.bipennis.userservice.controller.publicmethod;

import com.stephenhu.bipennis.model.VO.userservice.*;

import com.stephenhu.bipennis.util.GlobalExceptionHandler.code.Code;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.response.ResponseResult;
import com.stephenhu.bipennis.util.Regular.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Stephen Hu
 */
@Component
public final class JudgeMethod {
    private static final Logger logger = LoggerFactory.getLogger(JudgeMethod.class);

    public boolean isRightPostAccountPageVO(PostAccountPageVO postAccountPageVO) {
        if (postAccountPageVO.getUId() == null ||
                postAccountPageVO.getUId().trim().isEmpty() ||
                !NumberRegular.isUnsignedPositiveInteger(postAccountPageVO.getUId())) {
            return false;
        }
        return true;
    }

    public boolean isRightPostInsertAddressPageVO(PostInsertAddressPageVO postInsertAddressPageVO) {
        if (postInsertAddressPageVO.getUId() == null || postInsertAddressPageVO.getUId().trim().isEmpty() ||
                postInsertAddressPageVO.getAIsPublic() == null || postInsertAddressPageVO.getAIsPublic().trim().isEmpty() ||
                postInsertAddressPageVO.getLatitude() == null || postInsertAddressPageVO.getLatitude().trim().isEmpty() ||
                postInsertAddressPageVO.getLongitude() == null || postInsertAddressPageVO.getLongitude().trim().isEmpty() ||
                postInsertAddressPageVO.getAddressLabel() == null || postInsertAddressPageVO.getAddressLabel().trim().isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean isRightPostInsertSpareEmailPageVO(PostInsertSpareEmailPageVO postInsertSpareEmailPageVO) {
        if (postInsertSpareEmailPageVO.getUId() == null || postInsertSpareEmailPageVO.getUId().trim().isEmpty() ||
                postInsertSpareEmailPageVO.getSeIsPublic() == null || postInsertSpareEmailPageVO.getSeIsPublic().trim().isEmpty() ||
                postInsertSpareEmailPageVO.getSeEmail() == null || postInsertSpareEmailPageVO.getSeEmail().trim().isEmpty() ||
                !EmailRegular.isEmail(postInsertSpareEmailPageVO.getSeEmail())) {
            return false;
        }
        return true;
    }

    public boolean isRightPostInsertSecondaryEmailPageVO(PostInsertSecondaryEmailPageVO postInsertSecondaryEmailPageVO) {
        if (postInsertSecondaryEmailPageVO.getUId() == null || postInsertSecondaryEmailPageVO.getUId().trim().isEmpty() ||
                postInsertSecondaryEmailPageVO.getReIsPublic() == null || postInsertSecondaryEmailPageVO.getReIsPublic().trim().isEmpty() ||
                postInsertSecondaryEmailPageVO.getReEmail() == null || postInsertSecondaryEmailPageVO.getReEmail().trim().isEmpty() ||
                !EmailRegular.isEmail(postInsertSecondaryEmailPageVO.getReEmail())) {
            return false;
        }
        return true;
    }

    public boolean isRightPostDeleteAddressPageVO(PostDeleteAddressPageVO postDeleteAddressPageVO) {
        if (postDeleteAddressPageVO.getAId() == null || postDeleteAddressPageVO.getAId().trim().isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean isRightPostDeleteSpareEmailPageVO(PostDeleteSpareEmailPageVO postDeleteSpareEmailPageVO) {
        if (postDeleteSpareEmailPageVO.getSeId() == null || postDeleteSpareEmailPageVO.getSeId().trim().isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean isRightPostDeleteSecondaryEmailPageVO(PostDeleteSecondaryEmailPageVO postDeleteSecondaryEmailPageVO) {
        if (postDeleteSecondaryEmailPageVO.getReId() == null || postDeleteSecondaryEmailPageVO.getReId().trim().isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean isRightPostUpdateAddress(PostUpdateAddressPageVO postUpdateAddressPageVO) {
        if (postUpdateAddressPageVO.getAIsPublic() == null || postUpdateAddressPageVO.getAIsPublic().trim().isEmpty() ||
                postUpdateAddressPageVO.getLatitude() == null || postUpdateAddressPageVO.getLatitude().trim().isEmpty() ||
                postUpdateAddressPageVO.getLongitude() == null || postUpdateAddressPageVO.getLongitude().trim().isEmpty() ||
                postUpdateAddressPageVO.getAddressLabel() == null || postUpdateAddressPageVO.getAddressLabel().trim().isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean isRightPostUpdateName(PostUpdateNamePageVO postUpdateNamePageVO) {
        if (postUpdateNamePageVO.getUId() == null || postUpdateNamePageVO.getUId().trim().isEmpty() ||
                postUpdateNamePageVO.getLastName() == null || postUpdateNamePageVO.getLastName().trim().isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean isRightPostUpdateGender(PostUpdateGenderPageVO postUpdateGenderPageVO) {
        if (postUpdateGenderPageVO.getUId() == null || postUpdateGenderPageVO.getUId().trim().isEmpty() ||
                postUpdateGenderPageVO.getGenderIsPublic() == null || postUpdateGenderPageVO.getGenderIsPublic().trim().isEmpty() ||
                postUpdateGenderPageVO.getGender() == null || postUpdateGenderPageVO.getGender().trim().isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean isRightPostUpdateAvatar(String uId) {
        if (uId == null || uId.trim().isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean isRightPostUpdateBirthdate(PostUpdateBirthdatePageVO postUpdateBirthdatePageVO) {
        if (postUpdateBirthdatePageVO.getUId() == null || postUpdateBirthdatePageVO.getUId().trim().isEmpty() ||
                postUpdateBirthdatePageVO.getBirthdateIsPublic() == null || postUpdateBirthdatePageVO.getBirthdateIsPublic().trim().isEmpty() ||
                postUpdateBirthdatePageVO.getBirthdate() == null || postUpdateBirthdatePageVO.getBirthdate().trim().isEmpty() ||
                !DateRegular.isValidDate(postUpdateBirthdatePageVO.getBirthdate())) {
            return false;
        }
        return true;
    }

    public boolean isRightPostUpdatePhone(PostUpdatePhonePageVO postUpdatePhonePageVO) {
        if (postUpdatePhonePageVO.getUId() == null || postUpdatePhonePageVO.getUId().trim().isEmpty() ||
                postUpdatePhonePageVO.getUPhone() == null || postUpdatePhonePageVO.getUPhone().trim().isEmpty() ||
                !PhoneRegular.isInternationalPhone(postUpdatePhonePageVO.getUPhone())) {
            return false;
        }
        return true;
    }

    public boolean isRightPostUpdatePassword(PostUpdatePasswordPageVO postUpdatePasswordPageVO) {
        if (postUpdatePasswordPageVO.getUId() == null || postUpdatePasswordPageVO.getUId().trim().isEmpty() ||
                postUpdatePasswordPageVO.getHashPassword() == null || postUpdatePasswordPageVO.getHashPassword().trim().isEmpty() ||
                !PasswordRegular.isValidPassword(postUpdatePasswordPageVO.getHashPassword())) {
            return false;
        }
        return true;
    }

    public boolean isRightPostUpdateSpareEmail(PostUpdateSpareEmailPageVO postUpdateSpareEmailPageVO) {
        if (postUpdateSpareEmailPageVO.getSeId() == null || postUpdateSpareEmailPageVO.getSeId().trim().isEmpty() ||
                postUpdateSpareEmailPageVO.getSeIsPublic() == null || postUpdateSpareEmailPageVO.getSeIsPublic().trim().isEmpty() ||
                postUpdateSpareEmailPageVO.getSeEmail() == null || postUpdateSpareEmailPageVO.getSeEmail().trim().isEmpty() ||
                !EmailRegular.isEmail(postUpdateSpareEmailPageVO.getSeEmail())) {
            return false;
        }
        return true;
    }

    public boolean isRightPostUpdateSecondaryEmail(PostUpdateSecondaryEmailPageVO postUpdateSecondaryEmailPageVO) {
        if (postUpdateSecondaryEmailPageVO.getReId() == null || postUpdateSecondaryEmailPageVO.getReId().trim().isEmpty() ||
                postUpdateSecondaryEmailPageVO.getReIsPublic() == null || postUpdateSecondaryEmailPageVO.getReIsPublic().trim().isEmpty() ||
                postUpdateSecondaryEmailPageVO.getReEmail() == null || postUpdateSecondaryEmailPageVO.getReEmail().trim().isEmpty() ||
                !EmailRegular.isEmail(postUpdateSecondaryEmailPageVO.getReEmail())) {
            return false;
        }
        return true;
    }
}
