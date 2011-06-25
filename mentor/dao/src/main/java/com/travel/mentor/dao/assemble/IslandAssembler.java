package com.travel.mentor.dao.assemble;

import com.travel.mentor.dao.dto.impl.IslandDTO;
import com.travel.mentor.model.impl.Island;

import java.util.List;

public interface IslandAssembler {

    List<IslandDTO> assembleToIslandDTOList(List<Island> islandList);

    Island assembleToIslandDomainObject(IslandDTO islandDTO);

    IslandDTO assembleToIslandDTO(Island island);

}
