package com.travel.mentor.dao.assemble.impl;

import com.travel.mentor.dao.assemble.RegionAssembler;
import com.travel.mentor.dao.assemble.base.impl.BaseAssemblerImpl;
import com.travel.mentor.dao.dto.impl.IslandDTO;
import com.travel.mentor.dao.dto.impl.RegionDTO;
import com.travel.mentor.dao.dto.security.SecureUserDTO;
import com.travel.mentor.model.impl.Island;
import com.travel.mentor.model.impl.Region;
import com.travel.mentor.model.security.SecureUser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RegionAssemblerImpl extends BaseAssemblerImpl implements RegionAssembler {

    @Override
    public List<RegionDTO> assembleToDTOList(List<Region> regionList) {
        List<RegionDTO> regionDTOList = new ArrayList<RegionDTO>();
        for (Region region : regionList) {
            regionDTOList.add(assembleToDTO(region));
        }
        return regionDTOList;
    }

    @Override
    public Region assembleToDomainObject(RegionDTO regionDTO) {
        Region region = (Region) shallowCopy(regionDTO, Region.class);

        region.setCreateUser((SecureUser) shallowCopy(regionDTO.getCreateUserDTO(), SecureUser.class));
        region.setUpdateUser((SecureUser) shallowCopy(regionDTO.getUpdateUserDTO(), SecureUser.class));

        region.setIsland((Island) shallowCopy(regionDTO.getIslandDTO(), Island.class));

        return region;
    }

    @Override
    public RegionDTO assembleToDTO(Region region) {
        RegionDTO regionDTO = (RegionDTO) shallowCopy(region, RegionDTO.class);

        regionDTO.setCreateUserDTO((SecureUserDTO) shallowCopy(region.getCreateUser(), SecureUserDTO.class));
        regionDTO.setUpdateUserDTO((SecureUserDTO) shallowCopy(region.getUpdateUser(), SecureUserDTO.class));

        regionDTO.setIslandDTO((IslandDTO) shallowCopy(region.getIsland(), IslandDTO.class));

        return regionDTO;
    }

}
