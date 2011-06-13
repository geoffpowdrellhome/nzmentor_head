package com.travel.mentor.dao.assemble.impl;

import com.travel.mentor.dao.assemble.RegionAssembler;
import com.travel.mentor.dao.dto.IslandDTO;
import com.travel.mentor.dao.dto.RegionDTO;
import com.travel.mentor.model.impl.Island;
import com.travel.mentor.model.impl.Region;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RegionAssemblerImpl extends BaseAssemblerImpl implements RegionAssembler {

    @Override
    public List<RegionDTO> assembleToRegionDTOList(List<Region> regionList) {
        List<RegionDTO> regionDTOList = new ArrayList<RegionDTO>();
        for (Region region : regionList) {
            regionDTOList.add( assembleToRegionDTO(region) );
        }
        return regionDTOList;
    }

    @Override
    public Region assembleToRegionDomainObject(RegionDTO regionDTO) {
        Region region = (Region) shallowCopy(regionDTO, Region.class);
        region.setIsland( (Island) shallowCopy(regionDTO.getIslandDTO(), Island.class) );
        return region;
    }

    @Override
    public RegionDTO assembleToRegionDTO(Region region) {
        RegionDTO regionDTO = (RegionDTO) shallowCopy(region, RegionDTO.class);
        regionDTO.setIslandDTO( (IslandDTO) shallowCopy(region.getIsland(), IslandDTO.class) );
        return regionDTO;
    }

}
