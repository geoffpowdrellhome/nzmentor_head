package com.travel.mentor.dao.assemble.impl;

import com.travel.mentor.dao.assemble.AccommodationSiteAssembler;
import com.travel.mentor.dao.dto.base.ReferenceTypeDTO;
import com.travel.mentor.dao.dto.impl.AccommodationSiteDTO;
import com.travel.mentor.dao.dto.impl.UserDTO;
import com.travel.mentor.model.impl.AccommodationSite;
import com.travel.mentor.model.impl.User;
import com.travel.mentor.type.impl.AccommodationSiteType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccommodationSiteAssemblerImpl extends BaseAssemblerImpl implements AccommodationSiteAssembler {

    @Override
    public List<AccommodationSiteDTO> assembleToDTOList(List<AccommodationSite> accommodationSiteList) {
        List<AccommodationSiteDTO> accommodationSiteDTOList = new ArrayList<AccommodationSiteDTO>();
        for (AccommodationSite accommodationSite : accommodationSiteList) {
            accommodationSiteDTOList.add( assembleToDTO(accommodationSite) );
        }
        return accommodationSiteDTOList;
    }

    @Override
    public AccommodationSite assembleToDomainObject(AccommodationSiteDTO accommodationSiteDTO) {
        AccommodationSite accommodationSite = (AccommodationSite) shallowCopy(accommodationSiteDTO, AccommodationSite.class);

        accommodationSite.setCreateUser((User) shallowCopy(accommodationSiteDTO.getCreateUserDTO(), User.class));
        accommodationSite.setUpdateUser((User) shallowCopy(accommodationSiteDTO.getUpdateUserDTO(), User.class));

        accommodationSite.setAccommodationSiteType((AccommodationSiteType) shallowCopy(accommodationSiteDTO.getAccommodationSiteTypeDTO(), AccommodationSiteType.class));
        return accommodationSite;
    }

    @Override
    public AccommodationSiteDTO assembleToDTO(AccommodationSite accommodationSite) {
        AccommodationSiteDTO accommodationSiteDTO = (AccommodationSiteDTO) shallowCopy(accommodationSite, AccommodationSiteDTO.class);

        accommodationSiteDTO.setCreateUserDTO((UserDTO) shallowCopy(accommodationSite.getCreateUser(), UserDTO.class));
        accommodationSiteDTO.setUpdateUserDTO((UserDTO) shallowCopy(accommodationSite.getUpdateUser(), UserDTO.class));

        accommodationSiteDTO.setAccommodationSiteTypeDTO((ReferenceTypeDTO) shallowCopy(accommodationSite.getAccommodationSiteType(), ReferenceTypeDTO.class));

        return accommodationSiteDTO;
    }

}
