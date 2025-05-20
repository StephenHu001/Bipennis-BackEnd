package com.stephenhu.bipennis.userservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stephenhu.bipennis.model.PO.userservice.UserInfoPO;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Stephen Hu
* @description 针对表【t_userinfo】的数据库操作Mapper
* @createDate 2025-05-18 01:39:35
*/
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfoPO> {

}




