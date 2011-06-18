package com.travel.mentor.dao.assemble.impl;

import com.travel.mentor.dao.assemble.AccommodationSiteAssembler;
import com.travel.mentor.dao.dto.AccommodationSiteDTO;
import com.travel.mentor.dao.dto.ReferenceTypeDTO;
import com.travel.mentor.model.impl.AccommodationSite;
import com.travel.mentor.type.impl.AccommodationSiteType;
import com.travel.mentor.type.impl.RoomConfigurationType;
import com.travel.mentor.type.impl.RoomType;
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
        accommodationSite.setRoomConfigurationType((RoomConfigurationType) shallowCopy(accommodationSiteDTO.getRoomConfigurationTypeDTO(), RoomConfigurationType.class));
        accommodationSite.setRoomType((RoomType) shallowCopy(accommodationSiteDTO.getRoomTypeDTO(), RoomType.class));
        return accommodationSite;
    }

    @Override
    public AccommodationSiteDTO assembleToAccommodationSiteDTO(AccommodationSite accommodationSite) {
        AccommodationSiteDTO accommodationSiteDTO = (AccommodationSiteDTO) shallowCopy(accommodationSite, AccommodationSiteDTO.class);
        accommodationSiteDTO.setAccommodationSiteTypeDTO((ReferenceTypeDTO) shallowCopy(accommodationSite.getAccommodationSiteType(), ReferenceTypeDTO.class));
        accommodationSiteDTO.setRoomConfigurationTypeDTO((ReferenceTypeDTO) shallowCopy(accommodationSite.getRoomConfigurationType(), ReferenceTypeDTO.class));
        accommodationSiteDTO.setRoomTypeDTO((ReferenceTypeDTO) shallowCopy(accommodationSite.getRoomType(), ReferenceTypeDTO.class));
        return accommodationSiteDTO;
    }

}
