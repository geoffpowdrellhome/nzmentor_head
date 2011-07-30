package com.travel.mentor.dao.reference;

import com.travel.mentor.dao.dto.reference.ReferenceTypeDTO;

import java.util.List;

public interface ReferenceTypeDAO {

    List<ReferenceTypeDTO> findAll(String findAllNamedQuery);

    ReferenceTypeDTO saveOrUpdate(ReferenceTypeDTO referenceTypeDTO);

    void delete(ReferenceTypeDTO referenceTypeDTO);

    ReferenceTypeDTO find(ReferenceTypeDTO referenceTypeDTO);

}
