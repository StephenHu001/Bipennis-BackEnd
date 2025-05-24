package com.stephenhu.bipennis.userservice.controller;

import com.stephenhu.bipennis.model.BO.userservice.UserInfoBO;
import com.stephenhu.bipennis.model.DTO.userservice.AllUserInformationDTO;
import com.stephenhu.bipennis.model.VO.userservice.*;
import com.stephenhu.bipennis.util.DozerStruct.DozerStruct;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.code.Code;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.error.ApiException;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.error.ErrorHandler;
import com.stephenhu.bipennis.util.GlobalExceptionHandler.response.ResponseResult;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Stephen Hu
 */
@RequestMapping("/user/")
public interface UserController {
    /**
     * 获取用户信息
     *
     * @param postAccountPageVO 请求体
     * @return ResponseResult<GetAccountPageVO>
     */
    @Operation(summary = "getUser")
    @PostMapping("getUser")
    ResponseResult<GetAccountPageVO> getUser(@RequestBody PostAccountPageVO postAccountPageVO);

    /**
     * 插入地址
     *
     * @param postAddressPageVO 请求体
     * @return ResponseResult<PostInsertAddressPageVO>
     */
    @Operation(summary = "addAddress")
    @PostMapping("addAddress")
    ResponseResult<PostInsertAddressPageVO> addAddress(@RequestBody PostInsertAddressPageVO postAddressPageVO);

    /**
     * 插入备用邮箱
     *
     * @param postInsertSpareEmailPageVO 请求体
     * @return ResponseResult<PostInsertSpareEmailPageVO>
     */
    @Operation(summary = "addSpareEmail")
    @PostMapping("addSpareEmail")
    ResponseResult<PostInsertSpareEmailPageVO> addSpareEmail(@RequestBody PostInsertSpareEmailPageVO postInsertSpareEmailPageVO);

    /**
     * 插入辅助邮箱
     *
     * @param postInsertSecondaryEmailPageVO 请求体
     * @return ResponseResult<PostInsertSecondaryEmailPageVO>
     */
    @Operation(summary = "addSecondaryEmail")
    @PostMapping("addSecondaryEmail")
    ResponseResult<PostInsertSecondaryEmailPageVO> addSecondaryEmail(@RequestBody PostInsertSecondaryEmailPageVO postInsertSecondaryEmailPageVO);

    /**
     * 删除地址
     *
     * @param postDeleteAddressPageVO 请求体
     * @return ResponseResult<PostDeleteAddressPageVO>
     */
    @Operation(summary = "deleteAddress")
    @PostMapping("deleteAddress")
    ResponseResult<PostDeleteAddressPageVO> deleteAddress(@RequestBody PostDeleteAddressPageVO postDeleteAddressPageVO);

    /**
     * 删除备用邮箱
     *
     * @param postDeleteSpareEmailPageVO 请求体
     * @return ResponseResult<PostDeleteSpareEmailPageVO>
     */
    @Operation(summary = "deleteSpareEmail")
    @PostMapping("deleteSpareEmail")
    ResponseResult<PostDeleteSpareEmailPageVO> deleteSpareEmail(@RequestBody PostDeleteSpareEmailPageVO postDeleteSpareEmailPageVO);

    /**
     * 删除辅助邮箱
     *
     * @param postDeleteSecondaryEmailPageVO 请求体
     * @return ResponseResult<PostDeleteSecondaryEmailPageVO>
     */
    @Operation(summary = "deleteSecondaryEmail")
    @PostMapping("deleteSecondaryEmail")
    ResponseResult<PostDeleteSecondaryEmailPageVO> deleteSecondaryEmail(@RequestBody PostDeleteSecondaryEmailPageVO postDeleteSecondaryEmailPageVO);

    /**
     * 更新地址
     *
     * @param postUpdateAddressPageVO 请求体
     * @return ResponseResult<PostUpdateAddressPagePO>
     */
    @Operation(summary = "updateAddress")
    @PostMapping("updateAddress")
    ResponseResult<PostUpdateAddressPageVO> updateAddress(@RequestBody PostUpdateAddressPageVO postUpdateAddressPageVO);

    /**
     * 更新姓名
     *
     * @param postUpdateNamePageVO 请求体
     * @return ResponseResult<PostUpdateNamePageVO>
     */
    @Operation(summary = "updateName")
    @PostMapping("updateName")
    ResponseResult<PostUpdateNamePageVO> updateName(@RequestBody PostUpdateNamePageVO postUpdateNamePageVO);

    /**
     * 更新性别
     *
     * @param postUpdateGenderPageVO 请求体
     * @return ResponseResult<PostUpdateGenderPageVO>
     */
    @Operation(summary = "updateGender")
    @PostMapping("updateGender")
    ResponseResult<PostUpdateGenderPageVO> updateGender(@RequestBody PostUpdateGenderPageVO postUpdateGenderPageVO);

    /**
     * 上传头像
     *
     * @param uId        用户ID
     * @param avatarFile 图片
     * @return ResponseResult<AllUserInformationDTO>
     */
    @Operation(summary = "updateAvatar")
    @PostMapping("updateAvatar")
    ResponseResult<AllUserInformationDTO> updateAvatar(String uId, MultipartFile avatarFile);

    /**
     * 更新生日
     *
     * @param postUpdateBirthdatePageVO 请求体
     * @return ResponseResult<PostUpdateGenderPageVO>
     */
    @Operation(summary = "updateBirthdate")
    @PostMapping("updateBirthdate")
    ResponseResult<PostUpdateBirthdatePageVO> updateGender(@RequestBody PostUpdateBirthdatePageVO postUpdateBirthdatePageVO);

    /**
     * 更新电话
     *
     * @param postUpdatePhonePageVO 请求体
     * @return ResponseResult<PostUpdatePhonePageVO>
     */
    @Operation(summary = "updatePhone")
    @PostMapping("updatePhone")
    ResponseResult<PostUpdatePhonePageVO> updatePhone(@RequestBody PostUpdatePhonePageVO postUpdatePhonePageVO);

    /**
     * 更新密码
     *
     * @param postUpdatePasswordPageVO 请求体
     * @return ResponseResult<PostUpdatePasswordPageVO>
     */
    @Operation(summary = "updatePassword")
    @PostMapping("updatePassword")
    ResponseResult<PostUpdatePasswordPageVO> updatePassword(@RequestBody PostUpdatePasswordPageVO postUpdatePasswordPageVO);

    /**
     * 更新备用邮箱
     *
     * @param postUpdateSpareEmailPageVO 请求体
     * @return ResponseResult<PostUpdateSpareEmailPageVO>
     */
    @Operation(summary = "updateSpareEmail")
    @PostMapping("updateSpareEmail")
    ResponseResult<PostUpdateSpareEmailPageVO> updateSpareEmail(@RequestBody PostUpdateSpareEmailPageVO postUpdateSpareEmailPageVO);

    /**
     * 更新备用邮箱
     *
     * @param postUpdateSecondaryEmailPageVO 请求体
     * @return ResponseResult<PostUpdateSecondaryEmailPageVO>
     */
    @Operation(summary = "updateSecondaryEmail")
    @PostMapping("updateSecondaryEmail")
    ResponseResult<PostUpdateSecondaryEmailPageVO> updateSecondaryEmail(@RequestBody PostUpdateSecondaryEmailPageVO postUpdateSecondaryEmailPageVO);

}
