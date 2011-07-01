package com.travel.mentor.service.type;

import com.travel.mentor.base.AbstractSpringServiceReferenceTypeTestCase;
import org.junit.Before;

import javax.annotation.Resource;

public class ClimateWindfactorTypeServiceImplUnitTest extends AbstractSpringServiceReferenceTypeTestCase {

    @Resource(name = "climateWindfactorTypeService")
    protected ClimateWindfactorTypeService climateWindfactorTypeService;

    @Before
	public void setUp() {
        super.referenceTypeService = climateWindfactorTypeService;
	}

}
