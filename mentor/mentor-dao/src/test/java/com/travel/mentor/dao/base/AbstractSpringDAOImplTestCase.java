package com.travel.mentor.dao.base;

import com.travel.mentor.model.impl.User;
import net.sf.ehcache.CacheManager;
import org.apache.log4j.Logger;
import org.hibernate.ejb.EntityManagerFactoryImpl;
import org.junit.runner.RunWith;
import org.springframework.orm.jpa.EntityManagerFactoryInfo;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;

/**
 * Created by powdrelg
 * Date: 15/12/2010
 * Purpose: Base FareGate DAO Test case (mostly replaces SpringFareGateTestCase in the old FareGate trunk).
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:datasource-context.xml"})
@Transactional
public abstract class AbstractSpringDAOImplTestCase {

    protected transient Logger logger = Logger.getLogger(getClass());

    protected User user = new User(0L, "default", "default", true);

    @Resource
    protected CacheManager cacheManager;

    protected void logCacheStatistics(EntityManagerFactory entityManagerFactory) {
        EntityManagerFactoryInfo info = (EntityManagerFactoryInfo) entityManagerFactory;
        EntityManagerFactoryImpl factory = (EntityManagerFactoryImpl) info.getNativeEntityManagerFactory();
        logger.debug("cache statistics=" + factory.getSessionFactory().getStatistics());
    }

}
