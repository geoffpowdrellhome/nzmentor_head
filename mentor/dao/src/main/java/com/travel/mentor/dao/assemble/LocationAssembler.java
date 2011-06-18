package com.travel.mentor.dao.assemble;

import com.travel.mentor.dao.dto.LocationDTO;
import com.travel.mentor.model.impl.Location;

import java.util.List;

public interface LocationAssembler {

    List<LocationDTO> assembleToLocationDTOList(List<Location> locationList);

    Location assembleToLocationDomainObject(LocationDTO locationDTO);

    LocationDTO assembleToLocationDTO(Location location);

}
