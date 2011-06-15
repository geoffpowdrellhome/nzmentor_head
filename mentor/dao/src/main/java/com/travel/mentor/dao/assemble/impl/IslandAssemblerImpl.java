package com.travel.mentor.dao.assemble.impl;

import com.travel.mentor.dao.assemble.IslandAssembler;
import com.travel.mentor.dao.dto.IslandDTO;
import com.travel.mentor.model.impl.Island;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class IslandAssemblerImpl extends BaseAssemblerImpl implements IslandAssembler {

    @Override
    public List<IslandDTO> assembleToIslandDTOList(List<Island> islandList) {
        List<IslandDTO> islandDTOList = new ArrayList<IslandDTO>();
        for (Island island : islandList) {
            islandDTOList.add( assembleToIslandDTO(island) );
        }
        return islandDTOList;
    }

    @Override
    public IslandDTO assembleToIslandDTO(Island island) {
        IslandDTO islandDTO = (IslandDTO) shallowCopy(island, IslandDTO.class);
        return islandDTO;
    }

}
