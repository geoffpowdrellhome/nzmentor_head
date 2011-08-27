package com.travel.mentor.dao.assemble.general.impl;

import com.travel.mentor.dao.assemble.base.AbstractAssembler;
import com.travel.mentor.dao.assemble.general.IslandAssembler;
import com.travel.mentor.dao.dto.general.CountryDTO;
import com.travel.mentor.dao.dto.general.IslandDTO;
import com.travel.mentor.dao.dto.security.SecureUserDTO;
import com.travel.mentor.domain.general.Country;
import com.travel.mentor.domain.general.Island;
import com.travel.mentor.domain.security.SecureUser;
import org.springframework.beans.BeanUtils;
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
        Island island = (Island) assembleUtil.copyPropertiesToInstantiatedClass(islandDTO, Island.class);

        island.setCreateUser((SecureUser) assembleUtil.copyPropertiesToInstantiatedClass(islandDTO.getCreateUserDTO(), SecureUser.class));
        island.setUpdateUser((SecureUser) assembleUtil.copyPropertiesToInstantiatedClass(islandDTO.getUpdateUserDTO(), SecureUser.class));
        island.setCountry( (Country) assembleUtil.copyPropertiesToInstantiatedClass(islandDTO.getCountryDTO(), Country.class));

        return island;
    }

    @Override
    public IslandDTO assembleToDTOInstance(Island island) {
        IslandDTO islandDTO = (IslandDTO) assembleUtil.copyPropertiesToInstantiatedClass(island, IslandDTO.class);

        islandDTO.setCreateUserDTO((SecureUserDTO) assembleUtil.copyPropertiesToInstantiatedClass(island.getCreateUser(), SecureUserDTO.class));
        islandDTO.setUpdateUserDTO((SecureUserDTO) assembleUtil.copyPropertiesToInstantiatedClass(island.getUpdateUser(), SecureUserDTO.class));
        islandDTO.setCountryDTO((CountryDTO) assembleUtil.copyPropertiesToInstantiatedClass(island.getCountry(), CountryDTO.class));

        return islandDTO;
    }

    @Override
    public Island deepCopy(IslandDTO islandDTO, Island island) {
        String[] ignoreProperties = {"id"};
        BeanUtils.copyProperties(islandDTO, island, ignoreProperties);

        BeanUtils.copyProperties(islandDTO.getCreateUserDTO(), island.getCreateUser());
        BeanUtils.copyProperties(islandDTO.getUpdateUserDTO(), island.getUpdateUser());
        BeanUtils.copyProperties(islandDTO.getCountryDTO(), island.getCountry());

        return island;
    }

}
