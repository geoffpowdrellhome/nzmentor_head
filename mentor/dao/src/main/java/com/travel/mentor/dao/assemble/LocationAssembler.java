package com.travel.mentor.dao.assemble;

import com.travel.mentor.dao.dto.impl.LocationDTO;
import com.travel.mentor.model.impl.Location;

import java.util.List;

public interface LocationAssembler {

    List<LocationDTO> assembleToDTOList(List<Location> locationList);

    Location assembleToDomainObject(LocationDTO locationDTO);

    LocationDTO assembleToDTO(Location location);

}
