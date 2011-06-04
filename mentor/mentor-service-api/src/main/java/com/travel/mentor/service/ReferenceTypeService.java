package com.travel.mentor.service;


import com.travel.mentor.dao.dto.ReferenceTypeDTO;

import java.util.List;

public interface ReferenceTypeService {

    List<ReferenceTypeDTO> findAllReferenceTypes(String findAllNamedQuery);

}
