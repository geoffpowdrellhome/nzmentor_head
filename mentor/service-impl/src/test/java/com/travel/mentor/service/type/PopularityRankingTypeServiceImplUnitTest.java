package com.travel.mentor.service.type;

import com.travel.mentor.base.AbstractSpringServiceReferenceTypeTestCase;
import org.junit.Before;

import javax.annotation.Resource;

public class PopularityRankingTypeServiceImplUnitTest extends AbstractSpringServiceReferenceTypeTestCase {

    @Resource(name = "popularityRankingTypeService")
    protected PopularityRankingTypeService popularityRankingTypeService;

    @Before
	public void setUp() {
        super.referenceTypeService = popularityRankingTypeService;
	}

}
