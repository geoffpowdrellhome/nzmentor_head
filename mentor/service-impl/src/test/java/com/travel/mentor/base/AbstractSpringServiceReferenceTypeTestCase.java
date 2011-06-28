package com.travel.mentor.base;

import org.junit.Test;

public abstract class AbstractSpringServiceReferenceTypeTestCase extends AbstractSpringServiceImplTestCase {

    @Test
    public abstract void testFindAll();

    @Test
    public abstract void testAdd();

    @Test
    public abstract void testUpdate();

    @Test
    public abstract void testDelete();

}
