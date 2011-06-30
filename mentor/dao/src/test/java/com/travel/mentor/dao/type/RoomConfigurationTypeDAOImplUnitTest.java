package com.travel.mentor.dao.type;

import com.travel.mentor.dao.base.AbstractReferenceTypeDAOTestCase;
import com.travel.mentor.dao.base.MentorDAOImplTestCase;
import com.travel.mentor.dao.dto.base.ReferenceTypeDTO;
import com.travel.mentor.type.impl.RoomConfigurationType;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public class RoomConfigurationTypeDAOImplUnitTest extends AbstractReferenceTypeDAOTestCase implements MentorDAOImplTestCase {

    @Before
	public void setUp() {
        super.findAllNamedQuery = RoomConfigurationType.FIND_ALL_ROOM_CONFIGURATION_TYPES_NAMED_QUERY;
	}

    @Test
    public void testAdd() {
        ReferenceTypeDTO referenceTypeDTO = new ReferenceTypeDTO("name", "desc");
        referenceTypeDTO.setEntityClass(RoomConfigurationType.class);
        referenceTypeDTO.getUserSessionCookieDTO().setUserDTO( userDAO.find(EXISTING_USERNAME_VALUE));

        referenceTypeDTO = referenceTypeDAO.add(referenceTypeDTO);
        Assert.assertNotNull(referenceTypeDTO);
    }

}
