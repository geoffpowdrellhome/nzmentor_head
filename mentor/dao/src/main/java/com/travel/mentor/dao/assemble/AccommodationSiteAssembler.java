package com.travel.mentor.dao.assemble;

import com.travel.mentor.dao.dto.impl.AccommodationSiteDTO;
import com.travel.mentor.model.impl.AccommodationSite;

import java.util.List;

public interface AccommodationSiteAssembler {

    List<AccommodationSiteDTO> assembleToDTOList(List<AccommodationSite> accommodationSiteList);

    AccommodationSite assembleToDomainObject(AccommodationSiteDTO accommodationSiteDTO);

    AccommodationSiteDTO assembleToDTO(AccommodationSite accommodationSite);

}
