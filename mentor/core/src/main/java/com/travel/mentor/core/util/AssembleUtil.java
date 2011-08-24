package com.travel.mentor.core.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class AssembleUtil {

    public Object shallowCopy(Object copyFrom, Class clazz) {
        Assert.notNull(copyFrom);
        Object instance = BeanUtils.instantiateClass(clazz);
        Assert.notNull(instance);
        BeanUtils.copyProperties(copyFrom, instance);
        return instance;
    }

    public Object shallowCopy(Object copyFrom, Object copyTo) {
        Assert.notNull(copyFrom);
        Assert.notNull(copyTo);
        BeanUtils.copyProperties(copyFrom, copyTo);
        return copyTo;
    }

    public Object shallowCopy(Object copyFrom, Object copyTo, String[] ignoreProperties) {
        Assert.notNull(copyFrom);
        Assert.notNull(copyTo);
        BeanUtils.copyProperties(copyFrom, copyTo, ignoreProperties);
        return copyTo;
    }

}
