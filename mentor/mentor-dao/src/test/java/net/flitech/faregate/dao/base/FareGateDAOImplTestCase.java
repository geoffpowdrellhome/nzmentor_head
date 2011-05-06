package net.flitech.faregate.dao.base;

import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;

/**
 * Created by powdrelg
 * Date: 15/12/2010
 * Purpose: 'FareGate' DAO Implementation Test case.
 */
@TransactionConfiguration(transactionManager = "fareGateTransactionManager", defaultRollback = true)
public class FareGateDAOImplTestCase extends AbstractSpringDAOImplTestCase {

    @Resource(name = "fareGateEntityManagerFactory")
    protected EntityManagerFactory entityManager;

    public void setEntityManager(EntityManagerFactory entityManager) {
       this.entityManager = entityManager;
    }

}
