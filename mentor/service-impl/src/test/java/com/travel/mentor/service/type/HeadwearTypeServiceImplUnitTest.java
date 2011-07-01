package com.travel.mentor.service.type;

import com.travel.mentor.base.AbstractSpringServiceReferenceTypeTestCase;
import org.junit.Before;

import javax.annotation.Resource;

public class HeadwearTypeServiceImplUnitTest extends AbstractSpringServiceReferenceTypeTestCase {

    @Resource(name = "headwearTypeService")
    protected HeadwearTypeService headwearTypeService;

    @Before
	public void setUp() {
        super.referenceTypeService = headwearTypeService;
	}

}
