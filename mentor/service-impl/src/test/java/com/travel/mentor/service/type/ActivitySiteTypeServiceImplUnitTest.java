package com.travel.mentor.service.type;

import com.travel.mentor.base.AbstractSpringServiceReferenceTypeTestCase;
import org.junit.Before;

import javax.annotation.Resource;

public class ActivitySiteTypeServiceImplUnitTest extends AbstractSpringServiceReferenceTypeTestCase {

    @Resource(name = "activitySiteTypeService")
    private ActivitySiteTypeService activitySiteTypeService;

    @Before
	public void setUp() {
        super.referenceTypeService = activitySiteTypeService;
	}

}
