package com.travel.mentor.dao.type;

import com.travel.mentor.dao.dto.base.ReferenceTypeDTO;

import java.util.List;

public interface ReferenceTypeDAO {

    List<ReferenceTypeDTO> findAllReferenceTypes(String findAllNamedQuery);

    void addReferenceType(ReferenceTypeDTO referenceTypeDTO);

    void updateReferenceType(ReferenceTypeDTO referenceTypeDTO);

    void deleteReferenceType(ReferenceTypeDTO referenceTypeDTO);

}
