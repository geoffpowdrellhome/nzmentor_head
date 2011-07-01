package com.travel.mentor.service.type;

import com.travel.mentor.base.AbstractSpringServiceReferenceTypeTestCase;
import org.junit.Before;

import javax.annotation.Resource;

public class EventTypeServiceImplUnitTest extends AbstractSpringServiceReferenceTypeTestCase {

    @Resource(name = "eventTypeService")
    protected EventTypeService eventTypeService;

    @Before
	public void setUp() {
        super.referenceTypeService = eventTypeService;
	}

}