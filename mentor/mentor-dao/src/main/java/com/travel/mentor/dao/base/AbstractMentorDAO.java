package com.travel.mentor.dao.base;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import java.io.Serializable;

/**
 * Created by geoff
 * Base DAO for those DAO's using the 'mentorPU' persistence layer
 */
public abstract class AbstractMentorDAO implements Serializable {

    @PersistenceContext(type = PersistenceContextType.TRANSACTION, unitName = "mentorPU")
    protected EntityManager em;

    public void setEm(EntityManager em) {
        this.em = em;
    }

    protected Query enableQueryCache(Query query, String cacheRegion) {
        query.setHint("org.hibernate.cachable", true);
        query.setHint("org.hibernate.cacheRegion", cacheRegion);
        return query;
    }

}
