package com.travel.mentor.dao.assemble.impl;

import com.travel.mentor.dao.assemble.AccommodationSiteAssembler;
import com.travel.mentor.dao.dto.base.ReferenceTypeDTO;
import com.travel.mentor.dao.dto.impl.AccommodationSiteDTO;
import com.travel.mentor.model.impl.AccommodationSite;
import com.travel.mentor.type.impl.AccommodationSiteType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccommodationSiteAssemblerImpl extends BaseAssemblerImpl implements AccommodationSiteAssembler {

    @Override
    public List<AccommodationSiteDTO> assembleToAccommodationSiteDTOList(List<AccommodationSite> accommodationSiteList) {
        List<AccommodationSiteDTO> accommodationSiteDTOList = new ArrayList<AccommodationSiteDTO>();
        for (AccommodationSite accommodationSite : accommodationSiteList) {
            accommodationSiteDTOList.add( assembleToAccommodationSiteDTO(accommodationSite) );
        }
        return accommodationSiteDTOList;
    }

    @Override
    public AccommodationSite assembleToAccommodationSiteDomainObject(AccommodationSiteDTO accommodationSiteDTO) {
        AccommodationSite accommodationSite = (AccommodationSite) shallowCopy(accommodationSiteDTO, AccommodationSite.class);
        accommodationSite.setAccommodationSiteType((AccommodationSiteType) shallowCopy(accommodationSiteDTO.getAccommodationSiteTypeDTO(), AccommodationSiteType.class));
        return accommodationSite;
    }

    @Override
    public AccommodationSiteDTO assembleToAccommodationSiteDTO(AccommodationSite accommodationSite) {
        AccommodationSiteDTO accommodationSiteDTO = (AccommodationSiteDTO) shallowCopy(accommodationSite, AccommodationSiteDTO.class);
        accommodationSiteDTO.setAccommodationSiteTypeDTO((ReferenceTypeDTO) shallowCopy(accommodationSite.getAccommodationSiteType(), ReferenceTypeDTO.class));
        return accommodationSiteDTO;
    }

}
