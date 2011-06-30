package com.travel.mentor.dao.type;

import com.travel.mentor.dao.base.AbstractReferenceTypeDAOTestCase;
import com.travel.mentor.dao.base.MentorDAOImplTestCase;
import com.travel.mentor.dao.dto.base.ReferenceTypeDTO;
import com.travel.mentor.type.impl.VehicleHireSiteType;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public class VehicleHireSiteTypeDAOImplUnitTest extends AbstractReferenceTypeDAOTestCase implements MentorDAOImplTestCase {

    @Before
	public void setUp() {
        super.findAllNamedQuery = VehicleHireSiteType.FIND_ALL_VEHICLE_SITE_TYPES_NAMED_QUERY;
	}

    @Test
    public void testAdd() {
        ReferenceTypeDTO referenceTypeDTO = new ReferenceTypeDTO("name", "desc");
        referenceTypeDTO.setEntityClass(VehicleHireSiteType.class);
        referenceTypeDTO.getUserSessionCookieDTO().setUserDTO( userDAO.find(EXISTING_USERNAME_VALUE));

        referenceTypeDTO = referenceTypeDAO.add(referenceTypeDTO);
        Assert.assertNotNull(referenceTypeDTO);
    }

}
