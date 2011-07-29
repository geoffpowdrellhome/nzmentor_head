package com.travel.mentor.dao.assemble.impl;

import com.travel.mentor.dao.assemble.IslandAssembler;
import com.travel.mentor.dao.assemble.base.impl.BaseAssemblerImpl;
import com.travel.mentor.dao.dto.impl.IslandDTO;
import com.travel.mentor.dao.dto.security.SecureUserDTO;
import com.travel.mentor.model.impl.Country;
import com.travel.mentor.model.impl.Island;
import com.travel.mentor.model.security.SecureUser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class IslandAssemblerImpl extends BaseAssemblerImpl implements IslandAssembler {

    @Override
    public List<IslandDTO> assembleToDTOList(List<Island> islandList) {
        List<IslandDTO> islandDTOList = new ArrayList<IslandDTO>();
        for (Island island : islandList) {
            islandDTOList.add( assembleToDTO(island) );
        }
        return islandDTOList;
    }

    @Override
    public Island assembleToDomainObject(IslandDTO islandDTO) {
        Island island = (Island) shallowCopy(islandDTO, Island.class);

        island.setCreateUser((SecureUser) shallowCopy(islandDTO.getCreateUserDTO(), SecureUser.class));
        island.setUpdateUser((SecureUser) shallowCopy(islandDTO.getUpdateUserDTO(), SecureUser.class));

        island.setCountry( (Country) shallowCopy(islandDTO.getCountryDTO(), Country.class));

        return island;
    }

    @Override
    public IslandDTO assembleToDTO(Island island) {
        IslandDTO islandDTO = (IslandDTO) shallowCopy(island, IslandDTO.class);

        islandDTO.setCreateUserDTO((SecureUserDTO) shallowCopy(island.getCreateUser(), SecureUserDTO.class));
        islandDTO.setUpdateUserDTO((SecureUserDTO) shallowCopy(island.getUpdateUser(), SecureUserDTO.class));

        return islandDTO;
    }

}
