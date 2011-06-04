package com.travel.mentor.service;


import com.travel.mentor.dao.dto.ReferenceTypeDTO;

import java.util.List;

public interface ReferenceTypeService {

    List<ReferenceTypeDTO> findAllAccommodationSiteTypes();
    List<ReferenceTypeDTO> findAllActivitySiteTypes();
    List<ReferenceTypeDTO> findAllRoomConfigurationTypes();
    List<ReferenceTypeDTO> findAllRoomTypes();

}
