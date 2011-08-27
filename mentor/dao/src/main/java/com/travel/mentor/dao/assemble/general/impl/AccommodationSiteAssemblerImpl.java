package com.travel.mentor.dao.assemble.general.impl;

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
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccommodationSiteAssemblerImpl extends AbstractAssembler implements AccommodationSiteAssembler {

    @Override
    public List<AccommodationSiteDTO> assembleToDTOList(List<AccommodationSite> accommodationSiteList) {
        List<AccommodationSiteDTO> accommodationSiteDTOList = new ArrayList<AccommodationSiteDTO>();
        for (AccommodationSite accommodationSite : accommodationSiteList) {
            accommodationSiteDTOList.add( assembleToDTOInstance(accommodationSite) );
        }
        return accommodationSiteDTOList;
    }

    @Override
    public AccommodationSite assembleToEntityInstance(AccommodationSiteDTO accommodationSiteDTO) {
        AccommodationSite accommodationSite = (AccommodationSite) assembleUtil.copyPropertiesToInstantiatedClass(accommodationSiteDTO, AccommodationSite.class);

        accommodationSite.setCreateUser((SecureUser) assembleUtil.copyPropertiesToInstantiatedClass(accommodationSiteDTO.getCreateUserDTO(), SecureUser.class));
        accommodationSite.setUpdateUser((SecureUser) assembleUtil.copyPropertiesToInstantiatedClass(accommodationSiteDTO.getUpdateUserDTO(), SecureUser.class));
        accommodationSite.setAccommodationSiteType((AccommodationSiteType) assembleUtil.copyPropertiesToInstantiatedClass(accommodationSiteDTO.getAccommodationSiteTypeDTO(), AccommodationSiteType.class));
        accommodationSite.setSiteType((SiteType) assembleUtil.copyPropertiesToInstantiatedClass(accommodationSiteDTO.getAccommodationSiteTypeDTO(), SiteType.class));
        accommodationSite.setLocation((Location) assembleUtil.copyPropertiesToInstantiatedClass(accommodationSiteDTO.getLocationDTO(), Location.class));

        return accommodationSite;
    }

    @Override
    public AccommodationSiteDTO assembleToDTOInstance(AccommodationSite accommodationSite) {
        AccommodationSiteDTO accommodationSiteDTO = (AccommodationSiteDTO) assembleUtil.copyPropertiesToInstantiatedClass(accommodationSite, AccommodationSiteDTO.class);

        accommodationSiteDTO.setCreateUserDTO((SecureUserDTO) assembleUtil.copyPropertiesToInstantiatedClass(accommodationSite.getCreateUser(), SecureUserDTO.class));
        accommodationSiteDTO.setUpdateUserDTO((SecureUserDTO) assembleUtil.copyPropertiesToInstantiatedClass(accommodationSite.getUpdateUser(), SecureUserDTO.class));
        accommodationSiteDTO.setAccommodationSiteTypeDTO((ReferenceTypeDTO) assembleUtil.copyPropertiesToInstantiatedClass(accommodationSite.getAccommodationSiteType(), ReferenceTypeDTO.class));
        accommodationSiteDTO.setSiteTypeDTO((ReferenceTypeDTO) assembleUtil.copyPropertiesToInstantiatedClass(accommodationSite.getSiteType(), ReferenceTypeDTO.class));
        accommodationSiteDTO.setLocationDTO((LocationDTO) assembleUtil.copyPropertiesToInstantiatedClass(accommodationSite.getLocation(), LocationDTO.class));

        return accommodationSiteDTO;
    }

    @Override
    public AccommodationSite deepCopy(AccommodationSiteDTO accommodationSiteDTO, AccommodationSite accommodationSite) {
        String[] ignoreProperties = {"id"};
        BeanUtils.copyProperties(accommodationSiteDTO, accommodationSite, ignoreProperties);

        BeanUtils.copyProperties(accommodationSiteDTO.getCreateUserDTO(), accommodationSite.getCreateUser());
        BeanUtils.copyProperties(accommodationSiteDTO.getUpdateUserDTO(), accommodationSite.getUpdateUser());
        BeanUtils.copyProperties(accommodationSiteDTO.getAccommodationSiteTypeDTO(), accommodationSite.getAccommodationSiteType());
        BeanUtils.copyProperties(accommodationSiteDTO.getLocationDTO(), accommodationSite.getLocation());

        return accommodationSite;
    }

}
