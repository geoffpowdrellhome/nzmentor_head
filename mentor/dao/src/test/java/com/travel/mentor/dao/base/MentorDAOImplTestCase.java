package com.travel.mentor.dao.base;

import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;

/**
 * Created by geoff
 * Purpose: 'Mentor' DAO Implementation Test case.
 */
@TransactionConfiguration(transactionManager = "mentorTransactionManager", defaultRollback = true)
public class MentorDAOImplTestCase extends AbstractSpringDAOImplTestCase {

    @Resource(name = "mentorEntityManagerFactory")
    protected EntityManagerFactory entityManager;

    public void setEntityManager(EntityManagerFactory entityManager) {
       this.entityManager = entityManager;
    }

}
