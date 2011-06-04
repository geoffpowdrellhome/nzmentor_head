package com.travel.mentor.dao.type;

import com.travel.mentor.dao.dto.ReferenceTypeDTO;

import java.util.List;

public interface ReferenceTypeDAO {

    List<ReferenceTypeDTO> findAllReferenceTypes(String findAllNamedQuery);

}
