package com.travel.mentor.dao.reference;

import com.travel.mentor.dao.base.AbstractReferenceTypeDAOTestCase;
import com.travel.mentor.dao.base.MentorDAOImplTestCase;
import com.travel.mentor.dao.dto.reference.ReferenceTypeDTO;
import com.travel.mentor.domain.reference.SiteType;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public class SiteTypeDAOImplUnitTest extends AbstractReferenceTypeDAOTestCase implements MentorDAOImplTestCase {

    @Before
	public void setUp() {
        super.findAllNamedQuery = SiteType.FIND_ALL_SITE_TYPES_NAMED_QUERY;
	}

    @Test
    public void testAdd() {
        ReferenceTypeDTO referenceTypeDTO = new ReferenceTypeDTO("name", "desc");
        referenceTypeDTO.setEntityClass(SiteType.class);
        referenceTypeDTO.setLoggedInUser( secureUserDAO.findByUsername(EXISTING_USERNAME_VALUE));

        referenceTypeDTO = referenceTypeDAO.saveOrUpdate(referenceTypeDTO);
        Assert.assertNotNull(referenceTypeDTO);
    }

}
