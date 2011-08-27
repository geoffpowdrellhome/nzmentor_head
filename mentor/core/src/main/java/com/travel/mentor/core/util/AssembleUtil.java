package com.travel.mentor.core.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class AssembleUtil {

    public Object copyPropertiesToInstantiatedClass(Object copyFrom, Class copyToClass) {
        Assert.notNull(copyFrom);
        Object instance = BeanUtils.instantiateClass(copyToClass);
        Assert.notNull(instance);
        BeanUtils.copyProperties(copyFrom, instance);
        return instance;
    }

}
