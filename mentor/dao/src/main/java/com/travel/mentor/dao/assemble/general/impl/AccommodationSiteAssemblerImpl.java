package com.travel.mentor.dao.assemble.general.impl;

import com.travel.mentor.core.util.AssembleUtil;
import com.travel.mentor.dao.assemble.base.AbstractAssembler;
import com.travel.mentor.dao.assemble.general.AccommodationSiteAssembler;
import com.travel.mentor.dao.dto.general.AccommodationSiteDTO;
import com.travel.mentor.dao.dto.general.LocationDTO;
import com.travel.mentor.dao.dto.reference.ReferenceTypeDTO;
import com.travel.mentor.dao.dto.security.SecureUserDTO;
import com.travel.mentor.domain.general.AccommodationSite;
import com.travel.mentor.domain.general.Location;
import com.travel.mentor.domain.reference.AccommodationSiteType;
import com.travel.mentor.domain.reference.SiteType;
import com.travel.mentor.domain.security.SecureUser;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class AccommodationSiteAssemblerImpl extends AbstractAssembler implements AccommodationSiteAssembler {

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
        AccommodationSite accommodationSite = (AccommodationSite) assembleUtil.shallowCopy(accommodationSiteDTO, AccommodationSite.class);

        accommodationSite.setCreateUser((SecureUser) assembleUtil.shallowCopy(accommodationSiteDTO.getCreateUserDTO(), SecureUser.class));
        accommodationSite.setUpdateUser((SecureUser) assembleUtil.shallowCopy(accommodationSiteDTO.getUpdateUserDTO(), SecureUser.class));

        accommodationSite.setAccommodationSiteType((AccommodationSiteType) assembleUtil.shallowCopy(accommodationSiteDTO.getAccommodationSiteTypeDTO(), AccommodationSiteType.class));
        accommodationSite.setSiteType((SiteType) assembleUtil.shallowCopy(accommodationSiteDTO.getAccommodationSiteTypeDTO(), SiteType.class));
        accommodationSite.setLocation((Location) assembleUtil.shallowCopy(accommodationSiteDTO.getLocationDTO(), Location.class));

        return accommodationSite;
    }

    @Override
    public AccommodationSiteDTO assembleToDTO(AccommodationSite accommodationSite) {
        AccommodationSiteDTO accommodationSiteDTO = (AccommodationSiteDTO) assembleUtil.shallowCopy(accommodationSite, AccommodationSiteDTO.class);

        accommodationSiteDTO.setCreateUserDTO((SecureUserDTO) assembleUtil.shallowCopy(accommodationSite.getCreateUser(), SecureUserDTO.class));
        accommodationSiteDTO.setUpdateUserDTO((SecureUserDTO) assembleUtil.shallowCopy(accommodationSite.getUpdateUser(), SecureUserDTO.class));

        accommodationSiteDTO.setAccommodationSiteTypeDTO((ReferenceTypeDTO) assembleUtil.shallowCopy(accommodationSite.getAccommodationSiteType(), ReferenceTypeDTO.class));
        accommodationSiteDTO.setSiteTypeDTO((ReferenceTypeDTO) assembleUtil.shallowCopy(accommodationSite.getSiteType(), ReferenceTypeDTO.class));
        accommodationSiteDTO.setLocationDTO((LocationDTO) assembleUtil.shallowCopy(accommodationSite.getLocation(), LocationDTO.class));

        return accommodationSiteDTO;
    }

}
