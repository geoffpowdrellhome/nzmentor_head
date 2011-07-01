package com.travel.mentor.service.type;

import com.travel.mentor.base.AbstractSpringServiceReferenceTypeTestCase;
import org.junit.Before;

import javax.annotation.Resource;

public class RoomTypeServiceImplUnitTest extends AbstractSpringServiceReferenceTypeTestCase {

    @Resource(name = "accommodationSiteTypeService")
    protected AccommodationSiteTypeService accommodationSiteTypeService;

    @Before
	public void setUp() {
        super.referenceTypeService = accommodationSiteTypeService;
	}

}
