package com.stephenhu.bipennis.userservice.service.publicmethod;

import com.stephenhu.bipennis.model.DTO.userservice.AllUserInformationDTO;

/**
 * @author Stephen Hu
 */
public interface PublicMethod {
    /**
     * 判断是否是正确的插入地址的DTO
     * @param allUserInformationDTO 请求体
     * @return 是否是正确的插入地址的DTO
     * */
    public boolean isRightInsertAddress(AllUserInformationDTO allUserInformationDTO);

    /**
     * 判断是否是正确的插入备用邮箱的DTO
     * @param allUserInformationDTO 请求体
     * @return 是否是正确的插入备用邮箱的DTO
     * */
    public boolean isRightInsertSpareEmail(AllUserInformationDTO allUserInformationDTO);

    /**
     * 判断是否是正确的插入辅助邮箱的DTO
     * @param allUserInformationDTO 请求体
     * @return 是否是正确的插入辅助邮箱的DTO
     * */
    public boolean isRightInsertSecondaryEmail(AllUserInformationDTO allUserInformationDTO);

    /**
     * 判断是否是正确的删除地址的DTO
     * @param allUserInformationDTO 请求体
     * @return 是否是正确的删除地址的DTO
     * */
    public boolean isRightDeleteAddress(AllUserInformationDTO allUserInformationDTO);

    /**
     * 判断是否是正确的删除备用邮箱的DTO
     * @param allUserInformationDTO 请求体
     * @return 是否是正确的删除备用邮箱的DTO
     * */
    public boolean isRightDeleteSpareEmail(AllUserInformationDTO allUserInformationDTO);

    /**
     * 判断是否是正确的删除辅助邮箱的DTO
     * @param allUserInformationDTO 请求体
     * @return 是否是正确的删除辅助邮箱的DTO
     * */
    public boolean isRightDeleteSecondaryEmail(AllUserInformationDTO allUserInformationDTO);

    /**
     * 判断是否是正确的更新地址的DTO
     * @param allUserInformationDTO 请求体
     * @return 是否是正确的更新地址的DTO
     * */
    public boolean isRightUpdateAddress(AllUserInformationDTO allUserInformationDTO);

    /**
     * 判断是否是正确的更新姓名的DTO
     * @param allUserInformationDTO 请求体
     * @return 是否是正确的更新姓名的DTO
     * */
    public boolean isRightUpdateName(AllUserInformationDTO allUserInformationDTO);

    /**
     * 判断是否是正确的更新性别的DTO
     * @param allUserInformationDTO 请求体
     * @return 是否是正确的更新性别的DTO
     * */
    public boolean isRightUpdateGender(AllUserInformationDTO allUserInformationDTO);

    /**
     * 判断是否是正确的更新生日的DTO
     * @param allUserInformationDTO 请求体
     * @return 是否是正确的更新生日的DTO
     * */
    public boolean isRightUpdateBirthdate(AllUserInformationDTO allUserInformationDTO);

    /**
     * 判断是否是正确的更新手机的DTO
     * @param allUserInformationDTO 请求体
     * @return 是否是正确的更新手机的DTO
     * */
    public boolean isRightUpdatePhone(AllUserInformationDTO allUserInformationDTO);

    /**
     * 判断是否是正确的更新密码的DTO
     * @param allUserInformationDTO 请求体
     * @return 是否是正确的更新密码的DTO
     * */
    public boolean isRightUpdatePassword(AllUserInformationDTO allUserInformationDTO);

    /**
     * 判断是否是正确的更新备用邮箱的DTO
     * @param allUserInformationDTO 请求体
     * @return 是否是正确的更新备用邮箱的DTO
     * */
    public boolean isRightUpdateSpareEmail(AllUserInformationDTO allUserInformationDTO);

    /**
     * 判断是否是正确的更新辅助邮箱的DTO
     * @param allUserInformationDTO 请求体
     * @return 是否是正确的更新辅助邮箱的DTO
     * */
    public boolean isRightUpdateSecondaryEmail(AllUserInformationDTO allUserInformationDTO);

}
