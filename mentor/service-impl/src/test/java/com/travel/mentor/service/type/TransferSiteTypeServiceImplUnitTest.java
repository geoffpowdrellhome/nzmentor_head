package com.travel.mentor.service.type;

import com.travel.mentor.base.AbstractSpringServiceReferenceTypeTestCase;
import org.junit.Before;

import javax.annotation.Resource;

public class TransferSiteTypeServiceImplUnitTest extends AbstractSpringServiceReferenceTypeTestCase {

    @Resource(name = "transferSiteTypeService")
    protected TransferSiteTypeService transferSiteTypeService;

    @Before
	public void setUp() {
        super.referenceTypeService = transferSiteTypeService;
	}

}
