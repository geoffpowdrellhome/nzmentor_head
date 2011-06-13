package com.travel.mentor.dao.assemble;

import com.travel.mentor.dao.dto.AccommodationSiteDTO;
import com.travel.mentor.model.impl.AccommodationSite;

import java.util.List;

public interface AccommodationSiteAssembler {

    List<AccommodationSiteDTO> assembleToAccommodationSiteDTOList(List<AccommodationSite> accommodationSiteList);

    AccommodationSite assembleToAccommodationSiteDomainObject(AccommodationSiteDTO accommodationSiteDTO);

    AccommodationSiteDTO assembleToAccommodationSiteDTO(AccommodationSite accommodationSite);

    AccommodationSite instantiateAccommodationSiteDomainObject(AccommodationSiteDTO accommodationSiteDTO);

}
