package com.stephenhu.bipennis.util.DozerStruct;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;


/**
 * @author Stephen Hu
 * @param <Original> 原始类
 * @param <Target>   目标类
 */
public final class DozerStruct<Original, Target> {
    Mapper dozerMapper = new DozerBeanMapper();

    /**
     * @param original 原始类对象
     * @param target   目标类的类
     * @return target  目标类
     */
    public Target transForm(Original original, Class<Target> target) {
        return dozerMapper.map(original, target);
    }

}
