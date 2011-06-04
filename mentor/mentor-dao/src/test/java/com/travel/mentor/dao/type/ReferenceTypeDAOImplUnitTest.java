package com.travel.mentor.dao.type;

import com.travel.mentor.dao.base.MentorDAOImplTestCase;
import com.travel.mentor.dao.dto.ReferenceTypeDTO;
import com.travel.mentor.type.impl.AccommodationSiteType;
import com.travel.mentor.type.impl.ActivitySiteType;
import com.travel.mentor.type.impl.RoomConfigurationType;
import com.travel.mentor.type.impl.RoomType;
import junit.framework.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class ReferenceTypeDAOImplUnitTest extends MentorDAOImplTestCase {

    @Resource(name = "referenceTypeDAO")
    private ReferenceTypeDAO referenceTypeDAO;


    private void doPostRetrievalAssertsExpectingReferenceTypes(List<ReferenceTypeDTO> referenceTypeDTOList) {
        Assert.assertNotNull(referenceTypeDTOList);
        Assert.assertTrue(referenceTypeDTOList.size() != 0);
    }

    @Test
    public void testSucceedsFindingAccommodationSiteTypes() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAllReferenceTypes(AccommodationSiteType.FIND_ALL_ACCOMMODATION_SITE_TYPES_NAMED_QUERY);
        doPostRetrievalAssertsExpectingReferenceTypes(referenceTypeDTOList);
    }

    @Test
    public void testSucceedsFindingRoomTypes() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAllReferenceTypes(RoomType.FIND_ALL_ROOM_TYPES_NAMED_QUERY);
        doPostRetrievalAssertsExpectingReferenceTypes(referenceTypeDTOList);
    }

    @Test
    public void testSucceedsFindingRoomConfigurationTypes() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAllReferenceTypes(RoomConfigurationType.FIND_ALL_ROOM_CONFIGURATION_TYPES_NAMED_QUERY);
        doPostRetrievalAssertsExpectingReferenceTypes(referenceTypeDTOList);
    }

    @Test
    public void testSucceedsFindingActivitySiteTypes() {
        List<ReferenceTypeDTO> referenceTypeDTOList = referenceTypeDAO.findAllReferenceTypes(ActivitySiteType.FIND_ALL_ACTIVITY_SITE_TYPES_NAMED_QUERY);
        doPostRetrievalAssertsExpectingReferenceTypes(referenceTypeDTOList);
    }


}
