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
import org.springframework.beans.BeanUtils;
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
        Location location = (Location) assembleUtil.copyPropertiesToInstantiatedClass(locationDTO, Location.class);

        location.setCreateUser((SecureUser) assembleUtil.copyPropertiesToInstantiatedClass(locationDTO.getCreateUserDTO(), SecureUser.class));
        location.setUpdateUser((SecureUser) assembleUtil.copyPropertiesToInstantiatedClass(locationDTO.getUpdateUserDTO(), SecureUser.class));
        location.setLocationType((LocationType) assembleUtil.copyPropertiesToInstantiatedClass(locationDTO.getLocationTypeDTO(), LocationType.class));
        location.setRegion((Region) assembleUtil.copyPropertiesToInstantiatedClass(locationDTO.getRegionDTO(), Region.class));

        return location;
    }

    @Override
    public LocationDTO assembleToDTOInstance(Location location) {
        LocationDTO locationDTO = (LocationDTO) assembleUtil.copyPropertiesToInstantiatedClass(location, LocationDTO.class);

        locationDTO.setCreateUserDTO((SecureUserDTO) assembleUtil.copyPropertiesToInstantiatedClass(location.getCreateUser(), SecureUserDTO.class));
        locationDTO.setUpdateUserDTO((SecureUserDTO) assembleUtil.copyPropertiesToInstantiatedClass(location.getUpdateUser(), SecureUserDTO.class));
        locationDTO.setLocationTypeDTO((ReferenceTypeDTO) assembleUtil.copyPropertiesToInstantiatedClass(location.getLocationType(), ReferenceTypeDTO.class));
        locationDTO.setRegionDTO((RegionDTO) assembleUtil.copyPropertiesToInstantiatedClass(location.getRegion(), RegionDTO.class));

        return locationDTO;
    }

    @Override
    public Location deepCopy(LocationDTO locationDTO, Location location) {
        String[] ignoreProperties = {"id"};
        BeanUtils.copyProperties(locationDTO, location, ignoreProperties);

        BeanUtils.copyProperties(locationDTO.getCreateUserDTO(), location.getCreateUser());
        BeanUtils.copyProperties(locationDTO.getUpdateUserDTO(), location.getUpdateUser());
        BeanUtils.copyProperties(locationDTO.getLocationTypeDTO(), location.getLocationType());
        BeanUtils.copyProperties(locationDTO.getRegionDTO(), location.getRegion());

        return location;
    }

}
