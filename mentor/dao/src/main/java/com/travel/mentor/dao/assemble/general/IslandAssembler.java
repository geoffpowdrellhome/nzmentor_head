package com.travel.mentor.dao.assemble.general;

import com.travel.mentor.dao.dto.general.IslandDTO;
import com.travel.mentor.domain.general.Island;

import java.util.List;

public interface IslandAssembler {

    List<IslandDTO> assembleToDTOList(List<Island> islandList);

    Island assembleToDomainObject(IslandDTO islandDTO);

    IslandDTO assembleToDTO(Island island);

}
