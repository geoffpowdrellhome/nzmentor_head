package com.travel.mentor.service.type;

import com.travel.mentor.base.AbstractSpringServiceReferenceTypeTestCase;
import org.junit.Before;

import javax.annotation.Resource;

public class SiteTypeServiceImplUnitTest extends AbstractSpringServiceReferenceTypeTestCase {

    @Resource(name = "siteTypeService")
    protected SiteTypeService siteTypeService;

    @Before
	public void setUp() {
        super.referenceTypeService = siteTypeService;
	}

}
