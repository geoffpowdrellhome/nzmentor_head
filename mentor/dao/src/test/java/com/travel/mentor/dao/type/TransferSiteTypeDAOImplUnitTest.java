package com.travel.mentor.dao.type;

import com.travel.mentor.dao.base.AbstractReferenceTypeDAOTestCase;
import com.travel.mentor.dao.base.MentorDAOImplTestCase;
import com.travel.mentor.dao.dto.base.ReferenceTypeDTO;
import com.travel.mentor.type.impl.TransferSiteType;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public class TransferSiteTypeDAOImplUnitTest extends AbstractReferenceTypeDAOTestCase implements MentorDAOImplTestCase {

    @Before
	public void setUp() {
        super.findAllNamedQuery = TransferSiteType.FIND_ALL_TRANSFER_SITE_TYPES_NAMED_QUERY;
	}

    @Test
    public void testAdd() {
        ReferenceTypeDTO referenceTypeDTO = new ReferenceTypeDTO("name", "desc");
        referenceTypeDTO.setEntityClass(TransferSiteType.class);
        referenceTypeDTO.setLoggedInUser( securityDAO.findByUsername(EXISTING_USERNAME_VALUE));

        referenceTypeDTO = referenceTypeDAO.saveOrUpdate(referenceTypeDTO);
        Assert.assertNotNull(referenceTypeDTO);
    }

}
