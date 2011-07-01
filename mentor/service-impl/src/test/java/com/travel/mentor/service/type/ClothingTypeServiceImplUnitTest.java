package com.travel.mentor.service.type;

import com.travel.mentor.base.AbstractSpringServiceReferenceTypeTestCase;
import org.junit.Before;

import javax.annotation.Resource;

public class ClothingTypeServiceImplUnitTest extends AbstractSpringServiceReferenceTypeTestCase {

    @Resource(name = "clothingTypeService")
    protected ClothingTypeService clothingTypeService;

    @Before
	public void setUp() {
        super.referenceTypeService = clothingTypeService;
	}

}