package com.travel.mentor.dao.assemble.general.impl;

import com.travel.mentor.dao.assemble.base.AbstractAssembler;
import com.travel.mentor.dao.assemble.general.IslandAssembler;
import com.travel.mentor.dao.dto.general.IslandDTO;
import com.travel.mentor.dao.dto.security.SecureUserDTO;
import com.travel.mentor.domain.general.Country;
import com.travel.mentor.domain.general.Island;
import com.travel.mentor.domain.security.SecureUser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class IslandAssemblerImpl extends AbstractAssembler implements IslandAssembler {

    @Override
    public List<IslandDTO> assembleToDTOList(List<Island> islandList) {
        List<IslandDTO> islandDTOList = new ArrayList<IslandDTO>();
        for (Island island : islandList) {
            islandDTOList.add( assembleToDTOInstance(island) );
        }
        return islandDTOList;
    }

    @Override
    public Island assembleToEntityInstance(IslandDTO islandDTO) {
        Island island = (Island) assembleUtil.shallowCopy(islandDTO, Island.class);

        island.setCreateUser((SecureUser) assembleUtil.shallowCopy(islandDTO.getCreateUserDTO(), SecureUser.class));
        island.setUpdateUser((SecureUser) assembleUtil.shallowCopy(islandDTO.getUpdateUserDTO(), SecureUser.class));

        island.setCountry( (Country) assembleUtil.shallowCopy(islandDTO.getCountryDTO(), Country.class));

        return island;
    }

    @Override
    public IslandDTO assembleToDTOInstance(Island island) {
        IslandDTO islandDTO = (IslandDTO) assembleUtil.shallowCopy(island, IslandDTO.class);

        islandDTO.setCreateUserDTO((SecureUserDTO) assembleUtil.shallowCopy(island.getCreateUser(), SecureUserDTO.class));
        islandDTO.setUpdateUserDTO((SecureUserDTO) assembleUtil.shallowCopy(island.getUpdateUser(), SecureUserDTO.class));

        return islandDTO;
    }

    @Override
    public Island deepCopy(IslandDTO islandDTO, Island island) {
        String[] ignoreProperties = {"id"};
        assembleUtil.shallowCopy(islandDTO, island, ignoreProperties);

        assembleUtil.shallowCopy(islandDTO.getCreateUserDTO(), island.getCreateUser());
        assembleUtil.shallowCopy(islandDTO.getUpdateUserDTO(), island.getUpdateUser());
        assembleUtil.shallowCopy(islandDTO.getCountryDTO(), island.getCountry());

        return island;
    }

}
