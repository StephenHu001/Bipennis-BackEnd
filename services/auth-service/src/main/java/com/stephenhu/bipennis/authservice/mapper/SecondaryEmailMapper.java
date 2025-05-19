package com.stephenhu.bipennis.authservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stephenhu.bipennis.model.PO.authservice.SecondaryEmailPO;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Stephen Hu
* @description 针对表【r_email】的数据库操作Mapper
* @createDate 2025-05-18 23:24:03
*/
@Mapper
public interface SecondaryEmailMapper extends BaseMapper<SecondaryEmailPO> {

}




