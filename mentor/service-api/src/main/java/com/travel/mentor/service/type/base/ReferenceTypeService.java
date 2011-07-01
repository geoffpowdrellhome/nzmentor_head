package com.travel.mentor.service.type.base;

import com.travel.mentor.dao.dto.base.ReferenceTypeDTO;

import java.util.List;

public interface ReferenceTypeService {

    List<ReferenceTypeDTO> findAll();

    ReferenceTypeDTO add(ReferenceTypeDTO referenceTypeDTO);

    ReferenceTypeDTO update(ReferenceTypeDTO referenceTypeDTO);

    void delete(ReferenceTypeDTO referenceTypeDTO);

    ReferenceTypeDTO find(ReferenceTypeDTO referenceTypeDTO);

}
