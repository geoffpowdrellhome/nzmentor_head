package com.travel.mentor.service.type.impl;

import com.travel.mentor.dao.dto.base.ReferenceTypeDTO;
import com.travel.mentor.service.type.RoomConfigurationTypeService;
import com.travel.mentor.service.type.base.AbstractReferenceTypeService;
import com.travel.mentor.type.impl.RoomConfigurationType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("roomConfigurationTypeService")
@Transactional
public class RoomConfigurationTypeServiceImpl extends AbstractReferenceTypeService implements RoomConfigurationTypeService {

    @Override
    public List<ReferenceTypeDTO> findAll() {
        return referenceTypeDAO.findAll(RoomConfigurationType.FIND_ALL_ROOM_CONFIGURATION_TYPES_NAMED_QUERY);
    }

    @Override
    public ReferenceTypeDTO add(ReferenceTypeDTO referenceTypeDTO) {
        referenceTypeDTO.setEntityClass(RoomConfigurationType.class);
        return referenceTypeDAO.add(referenceTypeDTO);
    }

}

