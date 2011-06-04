package com.travel.mentor.dao.type;

import com.travel.mentor.dao.base.MentorDAOImplTestCase;
import com.travel.mentor.dao.dto.ReferenceTypeDTO;
import com.travel.mentor.type.impl.AccommodationSiteType;
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

}
