package com.travel.mentor.service.type.impl;

import com.travel.mentor.dao.dto.base.ReferenceTypeDTO;
import com.travel.mentor.service.type.EventTypeService;
import com.travel.mentor.service.type.base.AbstractReferenceTypeService;
import com.travel.mentor.type.impl.EventType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service("eventTypeService")
@Transactional
public class EventTypeServiceImpl extends AbstractReferenceTypeService implements EventTypeService {

    @Override
    public List<ReferenceTypeDTO> findAll() {
        return referenceTypeDAO.findAll(EventType.FIND_ALL_EVENT_TYPES_NAMED_QUERY);
    }

    @Override
    public ReferenceTypeDTO add(ReferenceTypeDTO referenceTypeDTO) {
        referenceTypeDTO.setEntityClass(EventType.class);
        return referenceTypeDAO.add(referenceTypeDTO);
    }

}
