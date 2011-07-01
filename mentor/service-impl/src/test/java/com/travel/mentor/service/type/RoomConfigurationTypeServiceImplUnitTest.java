package com.travel.mentor.service.type;

import com.travel.mentor.base.AbstractSpringServiceReferenceTypeTestCase;
import org.junit.Before;

import javax.annotation.Resource;

public class RoomConfigurationTypeServiceImplUnitTest extends AbstractSpringServiceReferenceTypeTestCase {

    @Resource(name = "roomConfigurationTypeService")
    protected RoomConfigurationTypeService roomConfigurationTypeService;

    @Before
	public void setUp() {
        super.referenceTypeService = roomConfigurationTypeService;
	}

}
