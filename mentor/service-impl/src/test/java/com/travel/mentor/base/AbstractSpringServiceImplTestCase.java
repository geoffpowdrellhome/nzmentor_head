package com.travel.mentor.base;

import com.travel.mentor.service.UserService;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:service-context.xml"})
@TransactionConfiguration(transactionManager = "mentorTransactionManager", defaultRollback = true)
@Transactional
public abstract class AbstractSpringServiceImplTestCase {

    protected static final String EXISTING_USERNAME_VALUE="donr";
    protected static final String EXISTING_PASSWORD_VALUE="mtalford";

    protected static final Long EXISTING_ID_VALUE=1L;

    @Resource(name = "userService")
    protected UserService userService;

    @Before
    public void setUp() {
    }

    protected void assertRecordsReturned(List list) {
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() != 0);
    }

}
