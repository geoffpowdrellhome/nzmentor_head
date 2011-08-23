package com.travel.mentor.dao.base;

import com.travel.mentor.core.MentorObject;
import com.travel.mentor.dao.assemble.security.SecureUserAssembler;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import java.util.List;


/**
 * Created by geoff
 * Base DAO for those DAO's using the 'mentorPU' persistence layer
 */
public abstract class AbstractMentorDAO extends MentorObject {

    @PersistenceContext(type = PersistenceContextType.TRANSACTION, unitName = "mentorPU")
    protected EntityManager em;

    @Resource
    protected SecureUserAssembler secureUserAssembler;

    @Resource(name = "dev")
    protected Boolean dev;

    public void setEm(EntityManager em) {
        this.em = em;
    }

    protected Query enableQueryCache(Query query, String cacheRegion) {
        query.setHint("org.hibernate.cachable", true);
        query.setHint("org.hibernate.cacheRegion", cacheRegion);
        return query;
    }

    @SuppressWarnings("unchecked")
    protected void cacheDomainObjects(List<String> namedQueries) {
        if (dev) return;

        for (String namedQuery : namedQueries) {
            StopWatch watch = new StopWatch();
            watch.start("Starting execution of namedQuery : " + namedQuery);
            em.createNamedQuery(namedQuery).getResultList();
            watch.stop();
            if (logger.isDebugEnabled()) {
                logger.debug(watch.prettyPrint());
                logger.info("Total Time in Seconds for namedQuery : " + namedQuery + watch.getTotalTimeSeconds());
            }
        }
    }

}
