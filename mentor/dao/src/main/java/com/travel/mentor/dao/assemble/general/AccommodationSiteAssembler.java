package com.travel.mentor.dao.assemble.general;

import com.travel.mentor.dao.dto.general.AccommodationSiteDTO;
import com.travel.mentor.domain.general.AccommodationSite;

import java.util.List;

public interface AccommodationSiteAssembler {

    List<AccommodationSiteDTO> assembleToDTOList(List<AccommodationSite> accommodationSiteList);

    AccommodationSite assembleToEntityInstance(AccommodationSiteDTO accommodationSiteDTO);

    AccommodationSiteDTO assembleToDTOInstance(AccommodationSite accommodationSite);

    AccommodationSite deepCopy(AccommodationSiteDTO accommodationSiteDTO, AccommodationSite accommodationSite);

}
