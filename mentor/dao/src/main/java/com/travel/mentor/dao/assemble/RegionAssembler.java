package com.travel.mentor.dao.assemble;

import com.travel.mentor.dao.dto.impl.RegionDTO;
import com.travel.mentor.model.impl.Region;

import java.util.List;

public interface RegionAssembler {

    List<RegionDTO> assembleToDTOList(List<Region> regionList);

    Region assembleToDomainObject(RegionDTO regionDTO);

    RegionDTO assembleToDTO(Region region);

}
