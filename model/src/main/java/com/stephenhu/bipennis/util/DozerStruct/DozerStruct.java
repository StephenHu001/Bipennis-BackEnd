package com.stephenhu.bipennis.util.DozerStruct;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import java.util.List;
import java.util.stream.Collectors;


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

    /**
     * 转换集合对象
     */
    public List<Target> transFormList(List<Original> originalList, Class<Target> target) {
        return originalList.stream()
                .map(o -> dozerMapper.map(o, target))
                .collect(Collectors.toList());
    }

}
