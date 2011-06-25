package com.travel.mentor.dao.assemble;

import com.travel.mentor.dao.dto.impl.IslandDTO;
import com.travel.mentor.model.impl.Island;

import java.util.List;

public interface IslandAssembler {

    List<IslandDTO> assembleToDTOList(List<Island> islandList);

    Island assembleToDomainObject(IslandDTO islandDTO);

    IslandDTO assembleToDTO(Island island);

}
