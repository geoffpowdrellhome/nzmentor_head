package com.travel.mentor.dao.assemble;

public interface BaseAssembler {

    Object shallowCopy(Object existingInstance, Class newClassType);

}
