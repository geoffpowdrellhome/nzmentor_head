package com.travel.mentor.dao.assemble.general.impl;

import com.travel.mentor.dao.assemble.general.LocationAssembler;
import com.travel.mentor.dao.assemble.base.impl.BaseAssemblerImpl;
import com.travel.mentor.dao.dto.reference.ReferenceTypeDTO;
import com.travel.mentor.dao.dto.general.LocationDTO;
import com.travel.mentor.dao.dto.general.RegionDTO;
import com.travel.mentor.dao.dto.security.SecureUserDTO;
import com.travel.mentor.domain.general.Location;
import com.travel.mentor.domain.general.Region;
import com.travel.mentor.domain.reference.LocationType;
import com.travel.mentor.domain.security.SecureUser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LocationAssemblerImpl extends BaseAssemblerImpl implements LocationAssembler {

    @Override
    public List<LocationDTO> assembleToDTOList(List<Location> locationList) {
        List<LocationDTO> locationDTOList = new ArrayList<LocationDTO>();
        for (Location location : locationList) {
            locationDTOList.add(assembleToDTO(location));
        }
        return locationDTOList;
    }

    @Override
    public Location assembleToDomainObject(LocationDTO locationDTO) {
        Location location = (Location) shallowCopy(locationDTO, Location.class);

        location.setCreateUser((SecureUser) shallowCopy(locationDTO.getCreateUserDTO(), SecureUser.class));
        location.setUpdateUser((SecureUser) shallowCopy(locationDTO.getUpdateUserDTO(), SecureUser.class));

        location.setLocationType((LocationType) shallowCopy(locationDTO.getLocationTypeDTO(), LocationType.class));
        location.setRegion((Region) shallowCopy(locationDTO.getRegionDTO(), Region.class));

        return location;
    }

    @Override
    public LocationDTO assembleToDTO(Location location) {
        LocationDTO locationDTO = (LocationDTO) shallowCopy(location, LocationDTO.class);

        locationDTO.setCreateUserDTO((SecureUserDTO) shallowCopy(location.getCreateUser(), SecureUserDTO.class));
        locationDTO.setUpdateUserDTO((SecureUserDTO) shallowCopy(location.getUpdateUser(), SecureUserDTO.class));

        locationDTO.setLocationTypeDTO((ReferenceTypeDTO) shallowCopy(location.getLocationType(), ReferenceTypeDTO.class));
        locationDTO.setRegionDTO((RegionDTO) shallowCopy(location.getRegion(), RegionDTO.class));

        return locationDTO;
    }

}
