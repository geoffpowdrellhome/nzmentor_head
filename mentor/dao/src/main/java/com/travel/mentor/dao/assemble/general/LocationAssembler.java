package com.travel.mentor.dao.assemble.general;

import com.travel.mentor.dao.dto.general.LocationDTO;
import com.travel.mentor.domain.general.Location;

import java.util.List;

public interface LocationAssembler {

    List<LocationDTO> assembleToDTOList(List<Location> locationList);

    Location assembleToEntityInstance(LocationDTO locationDTO);

    LocationDTO assembleToDTOInstance(Location location);

    Location deepCopy(LocationDTO locationDTO, Location location);

}
