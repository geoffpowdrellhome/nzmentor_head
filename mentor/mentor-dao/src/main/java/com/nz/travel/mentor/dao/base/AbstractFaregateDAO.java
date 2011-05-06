package com.nz.travel.mentor.dao.base;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 * Created by powdrelg Date: 2-Mar-2011  *
 * Base DAO for those DAO's using the 'fareGatePU' persistence layer
 */
public abstract class AbstractFaregateDAO extends AbstractDAO {

    @PersistenceContext(type = PersistenceContextType.TRANSACTION, unitName = "fareGatePU")
    protected EntityManager em;

    public void setEm(EntityManager em) {
        this.em = em;
    }

}
