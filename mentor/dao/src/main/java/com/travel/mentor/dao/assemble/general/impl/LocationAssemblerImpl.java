package com.travel.mentor.dao.assemble.general.impl;

import com.travel.mentor.dao.assemble.base.AbstractAssembler;
import com.travel.mentor.dao.assemble.general.LocationAssembler;
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
public class LocationAssemblerImpl extends AbstractAssembler implements LocationAssembler {

    @Override
    public List<LocationDTO> assembleToDTOList(List<Location> locationList) {
        List<LocationDTO> locationDTOList = new ArrayList<LocationDTO>();
        for (Location location : locationList) {
            locationDTOList.add(assembleToDTOInstance(location));
        }
        return locationDTOList;
    }

    @Override
    public Location assembleToEntityInstance(LocationDTO locationDTO) {
        Location location = (Location) assembleUtil.shallowCopy(locationDTO, Location.class);

        location.setCreateUser((SecureUser) assembleUtil.shallowCopy(locationDTO.getCreateUserDTO(), SecureUser.class));
        location.setUpdateUser((SecureUser) assembleUtil.shallowCopy(locationDTO.getUpdateUserDTO(), SecureUser.class));

        location.setLocationType((LocationType) assembleUtil.shallowCopy(locationDTO.getLocationTypeDTO(), LocationType.class));
        location.setRegion((Region) assembleUtil.shallowCopy(locationDTO.getRegionDTO(), Region.class));

        return location;
    }

    @Override
    public LocationDTO assembleToDTOInstance(Location location) {
        LocationDTO locationDTO = (LocationDTO) assembleUtil.shallowCopy(location, LocationDTO.class);

        locationDTO.setCreateUserDTO((SecureUserDTO) assembleUtil.shallowCopy(location.getCreateUser(), SecureUserDTO.class));
        locationDTO.setUpdateUserDTO((SecureUserDTO) assembleUtil.shallowCopy(location.getUpdateUser(), SecureUserDTO.class));

        locationDTO.setLocationTypeDTO((ReferenceTypeDTO) assembleUtil.shallowCopy(location.getLocationType(), ReferenceTypeDTO.class));
        locationDTO.setRegionDTO((RegionDTO) assembleUtil.shallowCopy(location.getRegion(), RegionDTO.class));

        return locationDTO;
    }

    @Override
    public Location deepCopy(LocationDTO locationDTO, Location location) {
        String[] ignoreProperties = {"id"};
        assembleUtil.shallowCopy(locationDTO, location, ignoreProperties);

        assembleUtil.shallowCopy(locationDTO.getCreateUserDTO(), location.getCreateUser());
        assembleUtil.shallowCopy(locationDTO.getUpdateUserDTO(), location.getUpdateUser());
        assembleUtil.shallowCopy(locationDTO.getLocationTypeDTO(), location.getLocationType());
        assembleUtil.shallowCopy(locationDTO.getRegionDTO(), location.getRegion());

        return location;
    }

}
