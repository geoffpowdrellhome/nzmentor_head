package com.travel.mentor.dao.assemble.impl;

import com.travel.mentor.dao.assemble.LocationAssembler;
import com.travel.mentor.dao.dto.base.ReferenceTypeDTO;
import com.travel.mentor.dao.dto.impl.LocationDTO;
import com.travel.mentor.dao.dto.impl.RegionDTO;
import com.travel.mentor.model.impl.Location;
import com.travel.mentor.model.impl.Region;
import com.travel.mentor.type.impl.LocationType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LocationAssemblerImpl extends BaseAssemblerImpl implements LocationAssembler {

    @Override
    public List<LocationDTO> assembleToLocationDTOList(List<Location> locationList) {
        List<LocationDTO> locationDTOList = new ArrayList<LocationDTO>();
        for (Location location : locationList) {
            locationDTOList.add( assembleToLocationDTO(location) );
        }
        return locationDTOList;
    }

    @Override
    public Location assembleToLocationDomainObject(LocationDTO locationDTO) {
        Location location = (Location) shallowCopy(locationDTO, Location.class);
        location.setLocationType((LocationType) shallowCopy(locationDTO.getLocationTypeDTO(), LocationType.class));
        location.setRegion((Region) shallowCopy(locationDTO.getRegionDTO(), Region.class));
        return location;
    }

    @Override
    public LocationDTO assembleToLocationDTO(Location location) {
        LocationDTO locationDTO = (LocationDTO) shallowCopy(location, LocationDTO.class);
        locationDTO.setLocationTypeDTO((ReferenceTypeDTO) shallowCopy(location.getLocationType(), ReferenceTypeDTO.class));
        locationDTO.setRegionDTO((RegionDTO) shallowCopy(location.getRegion(), RegionDTO.class));
        return locationDTO;
    }

}
