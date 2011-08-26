package com.travel.mentor.dao.assemble.general;

import com.travel.mentor.dao.dto.general.RegionDTO;
import com.travel.mentor.domain.general.Region;

import java.util.List;

public interface RegionAssembler {

    List<RegionDTO> assembleToDTOList(List<Region> regionList);

    Region assembleToEntityInstance(RegionDTO regionDTO);

    RegionDTO assembleToDTOInstance(Region region);

    Region deepCopy(RegionDTO regionDTO, Region region);

}
