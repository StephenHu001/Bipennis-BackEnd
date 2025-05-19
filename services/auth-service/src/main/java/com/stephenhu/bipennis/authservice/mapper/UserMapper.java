package com.stephenhu.bipennis.authservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stephenhu.bipennis.model.PO.authservice.UserPO;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Stephen Hu
* @description 针对表【t_user】的数据库操作Mapper
* @createDate 2025-05-14 15:30:47
*/
@Mapper
public interface UserMapper extends BaseMapper<UserPO> {

}




