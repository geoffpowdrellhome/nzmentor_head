package com.travel.mentor.service.type;

import com.travel.mentor.base.AbstractSpringServiceReferenceTypeTestCase;
import org.junit.Before;

import javax.annotation.Resource;

public class LocationTypeServiceImplUnitTest extends AbstractSpringServiceReferenceTypeTestCase {

    @Resource(name = "locationTypeService")
    protected LocationTypeService locationTypeService;

    @Before
	public void setUp() {
        super.referenceTypeService = locationTypeService;
	}

}
