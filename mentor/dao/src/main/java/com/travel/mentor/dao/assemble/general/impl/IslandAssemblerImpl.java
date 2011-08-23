package com.travel.mentor.dao.assemble.general.impl;

import com.travel.mentor.core.util.AssembleUtil;
import com.travel.mentor.dao.assemble.base.AbstractAssembler;
import com.travel.mentor.dao.assemble.general.IslandAssembler;
import com.travel.mentor.dao.dto.general.IslandDTO;
import com.travel.mentor.dao.dto.security.SecureUserDTO;
import com.travel.mentor.domain.general.Country;
import com.travel.mentor.domain.general.Island;
import com.travel.mentor.domain.security.SecureUser;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class IslandAssemblerImpl extends AbstractAssembler implements IslandAssembler {

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
        Island island = (Island) assembleUtil.shallowCopy(islandDTO, Island.class);

        island.setCreateUser((SecureUser) assembleUtil.shallowCopy(islandDTO.getCreateUserDTO(), SecureUser.class));
        island.setUpdateUser((SecureUser) assembleUtil.shallowCopy(islandDTO.getUpdateUserDTO(), SecureUser.class));

        island.setCountry( (Country) assembleUtil.shallowCopy(islandDTO.getCountryDTO(), Country.class));

        return island;
    }

    @Override
    public IslandDTO assembleToDTO(Island island) {
        IslandDTO islandDTO = (IslandDTO) assembleUtil.shallowCopy(island, IslandDTO.class);

        islandDTO.setCreateUserDTO((SecureUserDTO) assembleUtil.shallowCopy(island.getCreateUser(), SecureUserDTO.class));
        islandDTO.setUpdateUserDTO((SecureUserDTO) assembleUtil.shallowCopy(island.getUpdateUser(), SecureUserDTO.class));

        return islandDTO;
    }

}
