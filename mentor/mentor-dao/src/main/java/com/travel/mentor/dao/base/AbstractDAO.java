package com.travel.mentor.dao.base;

import com.travel.mentor.core.MentorObject;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Created by powdrelg Date: 2-Mar-2011  *
 * Base DAO
 */
@Transactional
public abstract class AbstractDAO extends MentorObject {
    public static final String DEFAULT_DB_USER = "DEFAULT";
    public static final String DUMMY_DB_USER = "DUMMY";

    protected transient Logger logger = Logger.getLogger(getClass());

    protected EntityManager em;

    protected String findDbUser(boolean test) {
        // during unit test, "SELECT user FROM dual" doesn't work, so use a DUMMY database username instead
        String user = DEFAULT_DB_USER;
        if (test) {
            user = DUMMY_DB_USER;
        } else {
            try {
                Query q = em.createNativeQuery("SELECT user FROM dual");
                user = (String) q.getSingleResult();
            } catch (Exception e) {
                logger.error("Unable to get database username. Defaulting to " + user);
            }
        }
        if (logger.isDebugEnabled()) {
            logger.debug("user = " + user);
        }
        return user;
    }

    protected Query enableQueryCache(Query query, String cacheRegion) {
        query.setHint("org.hibernate.cachable", true);
        query.setHint("org.hibernate.cacheRegion", cacheRegion);
        return query;
    }

}
