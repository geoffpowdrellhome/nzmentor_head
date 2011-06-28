package com.travel.mentor.dao.base;

import com.travel.mentor.dao.type.ReferenceTypeDAO;
import org.junit.Test;

import javax.annotation.Resource;

public abstract class AbstractReferenceTypeDAOTestCase extends AbstractMentorDAOImplTestCase {

    @Resource(name = "referenceTypeDAO")
    protected ReferenceTypeDAO referenceTypeDAO;

    @Test
    public abstract void testFindAll();

    @Test
    public abstract void testAdd();

    @Test
    public abstract void testUpdate();

    @Test
    public abstract void testDelete();

}
