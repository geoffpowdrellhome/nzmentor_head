package com.travel.mentor.dao.assemble.general;

import com.travel.mentor.dao.dto.general.IslandDTO;
import com.travel.mentor.domain.general.Island;

import java.util.List;

public interface IslandAssembler {

    List<IslandDTO> assembleToDTOList(List<Island> islandList);

    Island assembleToEntityInstance(IslandDTO islandDTO);

    IslandDTO assembleToDTOInstance(Island island);

    Island deepCopy(IslandDTO islandDTO, Island island);

}
