package com.travel.mentor.dao.assemble.impl;

import com.travel.mentor.dao.assemble.BaseAssembler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class BaseAssemblerImpl implements BaseAssembler {

    public Object shallowCopy(Object existingInstance, Class newClassType) {
        Object newType = BeanUtils.instantiateClass(newClassType);
        if (newType != null) {
            BeanUtils.copyProperties(existingInstance, newType);
        }
        return newType;
    }

}
