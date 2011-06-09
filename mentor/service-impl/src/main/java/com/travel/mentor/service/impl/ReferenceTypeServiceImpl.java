package com.travel.mentor.service.impl;

import com.travel.mentor.dao.dto.ReferenceTypeDTO;
import com.travel.mentor.dao.type.ReferenceTypeDAO;
import com.travel.mentor.service.ReferenceTypeService;
import com.travel.mentor.type.impl.AccommodationSiteType;
import com.travel.mentor.type.impl.ActivitySiteType;
import com.travel.mentor.type.impl.RoomConfigurationType;
import com.travel.mentor.type.impl.RoomType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("referenceTypeService")
public class ReferenceTypeServiceImpl implements ReferenceTypeService {

    @Resource(name="referenceTypeDAO")
    private ReferenceTypeDAO referenceTypeDAO;

    @Override
    public List<ReferenceTypeDTO> findAllAccommodationSiteTypes() {
        return referenceTypeDAO.findAllReferenceTypes(AccommodationSiteType.FIND_ALL_ACCOMMODATION_SITE_TYPES_NAMED_QUERY);
    }

    @Override
    public List<ReferenceTypeDTO> findAllActivitySiteTypes() {
        return referenceTypeDAO.findAllReferenceTypes(ActivitySiteType.FIND_ALL_ACTIVITY_SITE_TYPES_NAMED_QUERY);
    }

    @Override
    public List<ReferenceTypeDTO> findAllRoomConfigurationTypes() {
        return referenceTypeDAO.findAllReferenceTypes(RoomConfigurationType.FIND_ALL_ROOM_CONFIGURATION_TYPES_NAMED_QUERY);
    }

    @Override
    public List<ReferenceTypeDTO> findAllRoomTypes() {
        return referenceTypeDAO.findAllReferenceTypes(RoomType.FIND_ALL_ROOM_TYPES_NAMED_QUERY);
    }

}
