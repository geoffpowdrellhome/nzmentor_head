package com.travel.mentor.dao.base;

import com.travel.mentor.dao.assemble.security.SecureUserAssembler;
import com.travel.mentor.dao.reference.ReferenceTypeDAO;
import com.travel.mentor.dao.security.SecurityDAO;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import junit.framework.Assert;

/**
 * Created by geoff
 * Purpose: 'Mentor' DAO Implementation Test case.
 */
@TransactionConfiguration(transactionManager = "mentorTransactionManager", defaultRollback = true)
public class AbstractMentorDAOImplTestCase extends AbstractSpringDAOImplTestCase {

    protected static final Long EXISTING_ID_VALUE=1L;
    protected static final String EXISTING_USERNAME_VALUE="user1";

    @Resource(name = "referenceTypeDAO")
    protected ReferenceTypeDAO referenceTypeDAO;

    @Resource(name = "securityDAO")
    protected SecurityDAO securityDAO;

    @Resource
    protected SecureUserAssembler secureUserAssembler;

    @Resource(name = "mentorEntityManagerFactory")
    protected EntityManagerFactory entityManager;

    public void setEntityManager(EntityManagerFactory entityManager) {
       this.entityManager = entityManager;
    }

    protected void assertRecordsReturned(List list) {
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() != 0);
    }

}
