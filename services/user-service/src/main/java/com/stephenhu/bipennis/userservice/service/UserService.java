package com.stephenhu.bipennis.userservice.service;

import com.stephenhu.bipennis.model.DTO.userservice.AllUserInformationDTO;
import com.stephenhu.bipennis.model.DTO.userservice.UserInformationDTO;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.response.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Stephen Hu
 */
public interface UserService {
    /**
     * 添加地址
     *
     * @param allUserInformationDTO 请求体
     * @return ResponseResult<LoginDTO>
     */
    ResponseResult<AllUserInformationDTO> insertAddress(AllUserInformationDTO allUserInformationDTO);

    /**
     * 添加备用邮箱
     *
     * @param allUserInformationDTO 请求体
     * @return ResponseResult<LoginDTO>
     */
    ResponseResult<AllUserInformationDTO> insertSpareEmail(AllUserInformationDTO allUserInformationDTO);

    /**
     * 添加辅助邮箱
     *
     * @param allUserInformationDTO 请求体
     * @return ResponseResult<LoginDTO>
     */
    ResponseResult<AllUserInformationDTO> insertSecondaryEmail(AllUserInformationDTO allUserInformationDTO);

    /**
     * 删除地址
     *
     * @param allUserInformationDTO 请求体
     * @return ResponseResult<LoginDTO>
     */
    ResponseResult<AllUserInformationDTO> deleteAddress(AllUserInformationDTO allUserInformationDTO);

    /**
     * 删除备用邮箱
     *
     * @param allUserInformationDTO 请求体
     * @return ResponseResult<LoginDTO>
     */
    ResponseResult<AllUserInformationDTO> deleteSpareEmail(AllUserInformationDTO allUserInformationDTO);

    /**
     * 删除辅助邮箱
     *
     * @param allUserInformationDTO 请求体
     * @return ResponseResult<LoginDTO>
     */
    ResponseResult<AllUserInformationDTO> deleteSecondaryEmail(AllUserInformationDTO allUserInformationDTO);

    /**
     * 更新地址
     *
     * @param allUserInformationDTO 请求体
     * @return ResponseResult<LoginDTO>
     */
    ResponseResult<AllUserInformationDTO> updateAddress(AllUserInformationDTO allUserInformationDTO);

    /**
     * 更新姓名
     *
     * @param allUserInformationDTO 请求体
     * @return ResponseResult<LoginDTO>
     */
    ResponseResult<AllUserInformationDTO> updateName(AllUserInformationDTO allUserInformationDTO);

    /**
     * 更新性别
     *
     * @param allUserInformationDTO 请求体
     * @return ResponseResult<LoginDTO>
     */
    ResponseResult<AllUserInformationDTO> updateGender(AllUserInformationDTO allUserInformationDTO);

    /**
     * 更新照片
     *
     * @param uId 用户id
     * @param file 文件
     * @return ResponseResult<LoginDTO>
     */
    ResponseResult<AllUserInformationDTO> updateAvatar(String uId, MultipartFile file);

    /**
     * 更新生日
     *
     * @param allUserInformationDTO 请求体
     * @return ResponseResult<LoginDTO>
     */
    ResponseResult<AllUserInformationDTO> updateBirthdate(AllUserInformationDTO allUserInformationDTO);

    /**
     * 更新手机
     *
     * @param allUserInformationDTO 请求体
     * @return ResponseResult<LoginDTO>
     */
    ResponseResult<AllUserInformationDTO> updatePhone(AllUserInformationDTO allUserInformationDTO);

    /**
     * 更新密码
     *
     * @param allUserInformationDTO 请求体
     * @return ResponseResult<LoginDTO>
     */
    ResponseResult<AllUserInformationDTO> updatePassword(AllUserInformationDTO allUserInformationDTO);

    /**
     * 更新备用邮箱
     *
     * @param allUserInformationDTO 请求体
     * @return ResponseResult<LoginDTO>
     */
    ResponseResult<AllUserInformationDTO> updateSpareEmail(AllUserInformationDTO allUserInformationDTO);

    /**
     * 更新辅助邮箱
     *
     * @param allUserInformationDTO 请求体
     * @return ResponseResult<LoginDTO>
     */
    ResponseResult<AllUserInformationDTO> updateSecondaryEmail(AllUserInformationDTO allUserInformationDTO);

    /**
     * 查找
     *
     * @param allUserInformationDTO 请求体
     * @return ResponseResult<LoginDTO>
     */
    ResponseResult<UserInformationDTO> findAll(AllUserInformationDTO allUserInformationDTO);
}
