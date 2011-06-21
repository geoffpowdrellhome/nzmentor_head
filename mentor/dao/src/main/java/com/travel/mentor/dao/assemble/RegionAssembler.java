package com.travel.mentor.dao.assemble;

import com.travel.mentor.dao.dto.impl.RegionDTO;
import com.travel.mentor.model.impl.Region;

import java.util.List;

public interface RegionAssembler {

    List<RegionDTO> assembleToRegionDTOList(List<Region> regionList);

    Region assembleToRegionDomainObject(RegionDTO regionDTO);

    RegionDTO assembleToRegionDTO(Region region);

}
