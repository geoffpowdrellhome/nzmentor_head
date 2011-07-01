package com.travel.mentor.service.type;

import com.travel.mentor.base.AbstractSpringServiceReferenceTypeTestCase;
import org.junit.Before;

import javax.annotation.Resource;

public class VehicleHireSiteTypeServiceImplUnitTest extends AbstractSpringServiceReferenceTypeTestCase {

    @Resource(name = "vehicleHireSiteTypeService")
    protected VehicleHireSiteTypeService vehicleHireSiteTypeService;

    @Before
	public void setUp() {
        super.referenceTypeService = vehicleHireSiteTypeService;
	}

}
