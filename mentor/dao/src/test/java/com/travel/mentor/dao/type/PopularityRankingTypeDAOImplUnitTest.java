package com.travel.mentor.dao.type;

import com.travel.mentor.dao.base.AbstractReferenceTypeDAOTestCase;
import com.travel.mentor.dao.base.MentorDAOImplTestCase;
import com.travel.mentor.dao.dto.base.ReferenceTypeDTO;
import com.travel.mentor.type.impl.PopularityRankingType;
import junit.framework.Assert;
import org.junit.Test;

public class PopularityRankingTypeDAOImplUnitTest extends AbstractReferenceTypeDAOTestCase implements MentorDAOImplTestCase {

    public PopularityRankingTypeDAOImplUnitTest() {
        super.findAllNamedQuery = PopularityRankingType.FIND_ALL_POPULARITY_RANKING_TYPES_NAMED_QUERY;
    }

    @Test
    public void testAdd() {
        ReferenceTypeDTO referenceTypeDTO = new ReferenceTypeDTO("name", "desc", PopularityRankingType.class);
        referenceTypeDTO.getUserSessionCookieDTO().setUserDTO( userDAO.find(EXISTING_USERNAME_VALUE));

        referenceTypeDTO = referenceTypeDAO.add(referenceTypeDTO);
        Assert.assertNotNull(referenceTypeDTO);
    }

}