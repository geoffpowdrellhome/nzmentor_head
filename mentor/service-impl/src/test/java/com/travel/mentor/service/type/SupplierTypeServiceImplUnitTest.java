package com.travel.mentor.service.type;

import com.travel.mentor.base.AbstractSpringServiceReferenceTypeTestCase;
import org.junit.Before;

import javax.annotation.Resource;

public class SupplierTypeServiceImplUnitTest extends AbstractSpringServiceReferenceTypeTestCase {

    @Resource(name = "supplierTypeService")
    protected SupplierTypeService supplierTypeService;

    @Before
	public void setUp() {
        super.referenceTypeService = supplierTypeService;
	}

}
