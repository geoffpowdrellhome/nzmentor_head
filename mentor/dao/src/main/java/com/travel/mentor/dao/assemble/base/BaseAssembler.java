package com.travel.mentor.dao.assemble.base;

public interface BaseAssembler {

    Object shallowCopy(Object existingInstance, Class newClassType);

}
