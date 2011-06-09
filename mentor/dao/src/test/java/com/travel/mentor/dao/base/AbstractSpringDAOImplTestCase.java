package com.travel.mentor.dao.base;

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
 * Created by geoff
 * Purpose: Base Mentor DAO Test case
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:datasource-context.xml"})
@Transactional
public abstract class AbstractSpringDAOImplTestCase {

    protected transient Logger logger = Logger.getLogger(getClass());

    @Resource
    protected CacheManager cacheManager;

    protected void logCacheStatistics(EntityManagerFactory entityManagerFactory) {
        EntityManagerFactoryInfo info = (EntityManagerFactoryInfo) entityManagerFactory;
        EntityManagerFactoryImpl factory = (EntityManagerFactoryImpl) info.getNativeEntityManagerFactory();
        logger.debug("cache statistics=" + factory.getSessionFactory().getStatistics());
    }

}
