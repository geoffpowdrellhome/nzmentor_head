package com.travel.mentor.dao.assemble.general;

import com.travel.mentor.dao.dto.general.RegionDTO;
import com.travel.mentor.domain.general.Region;

import java.util.List;

public interface RegionAssembler {

    List<RegionDTO> assembleToDTOList(List<Region> regionList);

    Region assembleToDomainObject(RegionDTO regionDTO);

    RegionDTO assembleToDTO(Region region);

}
