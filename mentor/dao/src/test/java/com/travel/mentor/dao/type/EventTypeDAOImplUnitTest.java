package com.travel.mentor.dao.type;

import com.travel.mentor.dao.base.AbstractReferenceTypeDAOTestCase;
import com.travel.mentor.dao.dto.base.ReferenceTypeDTO;
import com.travel.mentor.type.impl.EventType;

import java.util.List;

public class EventTypeDAOImplUnitTest extends AbstractReferenceTypeDAOTestCase {

    @Override
    public void testFindAll() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAll(EventType.FIND_ALL_EVENT_TYPES_NAMED_QUERY);
        assertRecordsReturned(referenceTypeDTOList);
    }

    @Override
    public void testAdd() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void testUpdate() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void testDelete() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

}
