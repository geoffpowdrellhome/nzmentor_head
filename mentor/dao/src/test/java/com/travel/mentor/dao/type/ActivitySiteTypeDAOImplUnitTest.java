package com.travel.mentor.dao.type;

import com.travel.mentor.dao.base.AbstractReferenceTypeDAOTestCase;
import com.travel.mentor.dao.dto.base.ReferenceTypeDTO;
import com.travel.mentor.type.impl.AccommodationSiteType;
import com.travel.mentor.type.impl.ActivitySiteType;
import junit.framework.Assert;

import java.util.List;

public class ActivitySiteTypeDAOImplUnitTest extends AbstractReferenceTypeDAOTestCase {

    @Override
    public void testFindAll() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAll(ActivitySiteType.FIND_ALL_ACTIVITY_SITE_TYPES_NAMED_QUERY);
        assertRecordsReturned(referenceTypeDTOList);
    }

    @Override
    public void testAdd() {
        ReferenceTypeDTO referenceTypeDTO = new ReferenceTypeDTO("Dive-Hotel", "Dive-Hotel", AccommodationSiteType.class);
        referenceTypeDTO.getUserSessionCookieDTO().setUserDTO( userDAO.find(EXISTING_USERNAME_VALUE));

        referenceTypeDTO = referenceTypeDAO.add(referenceTypeDTO);
        Assert.assertNotNull(referenceTypeDTO);
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
