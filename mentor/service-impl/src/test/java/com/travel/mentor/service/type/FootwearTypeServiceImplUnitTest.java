package com.travel.mentor.service.type;

import com.travel.mentor.base.AbstractSpringServiceReferenceTypeTestCase;
import org.junit.Before;

import javax.annotation.Resource;

public class FootwearTypeServiceImplUnitTest extends AbstractSpringServiceReferenceTypeTestCase {

    @Resource(name = "footwearTypeService")
    protected FootwearTypeService footwearTypeService;

    @Before
	public void setUp() {
        super.referenceTypeService = footwearTypeService;
	}

}
