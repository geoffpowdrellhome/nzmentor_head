package com.travel.mentor.dao.assemble.general.impl;

import com.travel.mentor.dao.assemble.general.AccommodationSiteAssembler;
import com.travel.mentor.dao.assemble.base.impl.BaseAssemblerImpl;
import com.travel.mentor.dao.dto.reference.ReferenceTypeDTO;
import com.travel.mentor.dao.dto.general.AccommodationSiteDTO;
import com.travel.mentor.dao.dto.general.LocationDTO;
import com.travel.mentor.dao.dto.security.SecureUserDTO;
import com.travel.mentor.domain.general.AccommodationSite;
import com.travel.mentor.domain.general.Location;
import com.travel.mentor.domain.reference.AccommodationSiteType;
import com.travel.mentor.domain.reference.SiteType;
import com.travel.mentor.domain.security.SecureUser;
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

        accommodationSite.setCreateUser((SecureUser) shallowCopy(accommodationSiteDTO.getCreateUserDTO(), SecureUser.class));
        accommodationSite.setUpdateUser((SecureUser) shallowCopy(accommodationSiteDTO.getUpdateUserDTO(), SecureUser.class));

        accommodationSite.setAccommodationSiteType((AccommodationSiteType) shallowCopy(accommodationSiteDTO.getAccommodationSiteTypeDTO(), AccommodationSiteType.class));
        accommodationSite.setSiteType((SiteType) shallowCopy(accommodationSiteDTO.getAccommodationSiteTypeDTO(), SiteType.class));
        accommodationSite.setLocation((Location) shallowCopy(accommodationSiteDTO.getLocationDTO(), Location.class));

        return accommodationSite;
    }

    @Override
    public AccommodationSiteDTO assembleToDTO(AccommodationSite accommodationSite) {
        AccommodationSiteDTO accommodationSiteDTO = (AccommodationSiteDTO) shallowCopy(accommodationSite, AccommodationSiteDTO.class);

        accommodationSiteDTO.setCreateUserDTO((SecureUserDTO) shallowCopy(accommodationSite.getCreateUser(), SecureUserDTO.class));
        accommodationSiteDTO.setUpdateUserDTO((SecureUserDTO) shallowCopy(accommodationSite.getUpdateUser(), SecureUserDTO.class));

        accommodationSiteDTO.setAccommodationSiteTypeDTO((ReferenceTypeDTO) shallowCopy(accommodationSite.getAccommodationSiteType(), ReferenceTypeDTO.class));
        accommodationSiteDTO.setSiteTypeDTO((ReferenceTypeDTO) shallowCopy(accommodationSite.getSiteType(), ReferenceTypeDTO.class));
        accommodationSiteDTO.setLocationDTO((LocationDTO) shallowCopy(accommodationSite.getLocation(), LocationDTO.class));

        return accommodationSiteDTO;
    }

}
