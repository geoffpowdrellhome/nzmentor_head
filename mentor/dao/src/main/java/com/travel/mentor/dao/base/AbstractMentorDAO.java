package com.travel.mentor.dao.base;

import com.travel.mentor.core.MentorObject;
import com.travel.mentor.dao.assemble.security.SecureUserAssembler;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

/**
 * Created by geoff
 * Base DAO for those DAO's using the 'mentorPU' persistence layer
 */
public abstract class AbstractMentorDAO extends MentorObject {

    @PersistenceContext(type = PersistenceContextType.TRANSACTION, unitName = "mentorPU")
    protected EntityManager em;

    @Resource
    protected SecureUserAssembler secureUserAssembler;

    public void setEm(EntityManager em) {
        this.em = em;
    }

    protected Query enableQueryCache(Query query, String cacheRegion) {
        query.setHint("org.hibernate.cachable", true);
        query.setHint("org.hibernate.cacheRegion", cacheRegion);
        return query;
    }

    @SuppressWarnings("unchecked")
    @PostConstruct
    protected abstract void cacheDomainObjects();

}
