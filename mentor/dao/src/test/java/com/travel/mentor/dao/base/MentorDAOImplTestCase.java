package com.travel.mentor.dao.base;

import com.travel.mentor.dao.UserDAO;
import com.travel.mentor.dao.type.ReferenceTypeDAO;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import junit.framework.Assert;

/**
 * Created by geoff
 * Purpose: 'Mentor' DAO Implementation Test case.
 */
@TransactionConfiguration(transactionManager = "mentorTransactionManager", defaultRollback = false)
public class MentorDAOImplTestCase extends AbstractSpringDAOImplTestCase {

    protected static final Long EXISTING_ID_VALUE=1L;
    protected static final String EXISTING_USERNAME_VALUE="donr";

    @Resource(name = "referenceTypeDAO")
    protected ReferenceTypeDAO referenceTypeDAO;

    @Resource(name = "userDAO")
    protected UserDAO userDAO;

    @Resource(name = "mentorEntityManagerFactory")
    protected EntityManagerFactory entityManager;

    public void setEntityManager(EntityManagerFactory entityManager) {
       this.entityManager = entityManager;
    }

    protected void doExpectingRecordsAssert(List list) {
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() != 0);
    }

}
