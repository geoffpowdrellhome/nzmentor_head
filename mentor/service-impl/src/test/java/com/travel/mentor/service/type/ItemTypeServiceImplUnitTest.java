package com.travel.mentor.service.type;

import com.travel.mentor.base.AbstractSpringServiceReferenceTypeTestCase;
import org.junit.Before;

import javax.annotation.Resource;

public class ItemTypeServiceImplUnitTest extends AbstractSpringServiceReferenceTypeTestCase {

    @Resource(name = "itemTypeService")
    protected ItemTypeService itemTypeService;

    @Before
	public void setUp() {
        super.referenceTypeService = itemTypeService;
	}

}
