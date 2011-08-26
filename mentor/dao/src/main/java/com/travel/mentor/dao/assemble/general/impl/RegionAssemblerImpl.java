package com.travel.mentor.dao.assemble.general.impl;

import com.travel.mentor.dao.assemble.base.AbstractAssembler;
import com.travel.mentor.dao.assemble.general.RegionAssembler;
import com.travel.mentor.dao.dto.general.IslandDTO;
import com.travel.mentor.dao.dto.general.RegionDTO;
import com.travel.mentor.dao.dto.security.SecureUserDTO;
import com.travel.mentor.domain.general.Island;
import com.travel.mentor.domain.general.Region;
import com.travel.mentor.domain.security.SecureUser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RegionAssemblerImpl extends AbstractAssembler implements RegionAssembler {

    @Override
    public List<RegionDTO> assembleToDTOList(List<Region> regionList) {
        List<RegionDTO> regionDTOList = new ArrayList<RegionDTO>();
        for (Region region : regionList) {
            regionDTOList.add(assembleToDTOInstance(region));
        }
        return regionDTOList;
    }

    @Override
    public Region assembleToEntityInstance(RegionDTO regionDTO) {
        Region region = (Region) assembleUtil.shallowCopy(regionDTO, Region.class);

        region.setCreateUser((SecureUser) assembleUtil.shallowCopy(regionDTO.getCreateUserDTO(), SecureUser.class));
        region.setUpdateUser((SecureUser) assembleUtil.shallowCopy(regionDTO.getUpdateUserDTO(), SecureUser.class));

        region.setIsland((Island) assembleUtil.shallowCopy(regionDTO.getIslandDTO(), Island.class));

        return region;
    }

    @Override
    public RegionDTO assembleToDTOInstance(Region region) {
        RegionDTO regionDTO = (RegionDTO) assembleUtil.shallowCopy(region, RegionDTO.class);

        regionDTO.setCreateUserDTO((SecureUserDTO) assembleUtil.shallowCopy(region.getCreateUser(), SecureUserDTO.class));
        regionDTO.setUpdateUserDTO((SecureUserDTO) assembleUtil.shallowCopy(region.getUpdateUser(), SecureUserDTO.class));

        regionDTO.setIslandDTO((IslandDTO) assembleUtil.shallowCopy(region.getIsland(), IslandDTO.class));

        return regionDTO;
    }

    @Override
    public Region deepCopy(RegionDTO regionDTO, Region region) {
        String[] ignoreProperties = {"id"};
        assembleUtil.shallowCopy(regionDTO, region, ignoreProperties);

        assembleUtil.shallowCopy(regionDTO.getCreateUserDTO(), region.getCreateUser());
        assembleUtil.shallowCopy(regionDTO.getUpdateUserDTO(), region.getUpdateUser());
        assembleUtil.shallowCopy(regionDTO.getIslandDTO(), region.getIsland());

        return region;
    }

}
