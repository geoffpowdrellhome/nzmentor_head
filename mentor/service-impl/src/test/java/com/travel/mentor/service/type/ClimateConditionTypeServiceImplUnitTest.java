package com.travel.mentor.service.type;

import com.travel.mentor.base.AbstractSpringServiceReferenceTypeTestCase;
import org.junit.Before;

import javax.annotation.Resource;

public class ClimateConditionTypeServiceImplUnitTest extends AbstractSpringServiceReferenceTypeTestCase {

    @Resource(name = "climateConditionTypeService")
    protected ClimateConditionTypeService climateConditionTypeService;

    @Before
	public void setUp() {
        super.referenceTypeService = climateConditionTypeService;
	}

}