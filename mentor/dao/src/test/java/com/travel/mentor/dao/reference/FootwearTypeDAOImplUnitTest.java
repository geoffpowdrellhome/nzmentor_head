package com.travel.mentor.dao.reference;

import com.travel.mentor.dao.base.AbstractReferenceTypeDAOTestCase;
import com.travel.mentor.dao.base.MentorDAOImplTestCase;
import com.travel.mentor.dao.dto.reference.ReferenceTypeDTO;
import com.travel.mentor.domain.reference.FootwearType;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public class FootwearTypeDAOImplUnitTest extends AbstractReferenceTypeDAOTestCase implements MentorDAOImplTestCase {

    @Before
	public void setUp() {
        super.findAllNamedQuery = FootwearType.FIND_ALL_FOOTWEAR_TYPES_NAMED_QUERY;
	}

    @Test
    public void testAdd() {
        ReferenceTypeDTO referenceTypeDTO = new ReferenceTypeDTO("name", "desc");
        referenceTypeDTO.setEntityClass(FootwearType.class);
        referenceTypeDTO.setLoggedInUser( secureUserDAO.findByUsername(EXISTING_USERNAME_VALUE));

        referenceTypeDTO = referenceTypeDAO.saveOrUpdate(referenceTypeDTO);
        Assert.assertNotNull(referenceTypeDTO);
    }

}
