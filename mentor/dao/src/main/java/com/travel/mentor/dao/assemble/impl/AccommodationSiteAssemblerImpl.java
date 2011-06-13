package com.travel.mentor.dao.assemble.impl;

import com.travel.mentor.dao.assemble.AccommodationSiteAssembler;
import com.travel.mentor.dao.dto.AccommodationSiteDTO;
import com.travel.mentor.model.impl.AccommodationSite;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccommodationSiteAssemblerImpl implements AccommodationSiteAssembler {

    @Override
    public List<AccommodationSiteDTO> assembleToAccommodationSiteDTOList(List<AccommodationSite> accommodationSiteList) {
        List<AccommodationSiteDTO> accommodationSiteDTOList = new ArrayList<AccommodationSiteDTO>();
        for (AccommodationSite accommodationSite : accommodationSiteList) {
            accommodationSiteDTOList.add(assembleToAccommodationSiteDTO(accommodationSite));
        }

        return accommodationSiteDTOList;
    }

    @Override
    public AccommodationSite assembleToAccommodationSiteDomainObject(AccommodationSiteDTO accommodationSiteDTO) {
        AccommodationSite accommodationSite = new AccommodationSite();

        accommodationSite.setId(accommodationSiteDTO.getId());
        accommodationSite.setName(accommodationSiteDTO.getName());
        accommodationSite.setDescription(accommodationSiteDTO.getDescription());

        return accommodationSite;
    }

    @Override
    public AccommodationSiteDTO assembleToAccommodationSiteDTO(AccommodationSite accommodationSite) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public AccommodationSite instantiateAccommodationSiteDomainObject(AccommodationSiteDTO accommodationSiteDTO) {
        AccommodationSite accommodationSite = new AccommodationSite();

        accommodationSite.setId(accommodationSiteDTO.getId());
        accommodationSite.setDescription(accommodationSiteDTO.getDescription());
        accommodationSite.setName(accommodationSiteDTO.getName());

        return accommodationSite;
    }

}
