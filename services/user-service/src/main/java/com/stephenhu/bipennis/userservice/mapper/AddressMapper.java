package com.stephenhu.bipennis.userservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stephenhu.bipennis.model.PO.userservice.AddressPO;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Stephen Hu
* @description 针对表【t_address(地址信息表)】的数据库操作Mapper
* @createDate 2025-05-19 15:23:59
*/
@Mapper
public interface AddressMapper extends BaseMapper<AddressPO> {

}




