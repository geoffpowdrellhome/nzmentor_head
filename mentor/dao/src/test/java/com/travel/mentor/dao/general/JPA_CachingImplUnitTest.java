package com.travel.mentor.dao.general;

import com.travel.mentor.dao.base.AbstractMentorDAOImplTestCase;
import com.travel.mentor.dao.dto.reference.ReferenceTypeDTO;
import com.travel.mentor.dao.reference.ReferenceTypeDAO;
import com.travel.mentor.domain.reference.RoomConfigurationType;
import com.travel.mentor.domain.reference.RoomType;
import junit.framework.Assert;
import net.sf.ehcache.Cache;
import org.hibernate.cache.EhCache;
import org.hibernate.ejb.EntityManagerFactoryImpl;
import org.junit.Before;
import org.springframework.beans.BeanUtils;
import org.junit.Test;
import org.springframework.orm.jpa.EntityManagerFactoryInfo;

import javax.annotation.Resource;

import java.util.List;

public class JPA_CachingImplUnitTest extends AbstractMentorDAOImplTestCase {

    @Resource(name = "cacheRefresherDAO")
    private CacheRefresherDAO cacheRefresher;

    @Resource(name = "referenceTypeDAO")
    private ReferenceTypeDAO referenceTypeDAO;

    private EntityManagerFactoryImpl factory;

    private static final Long VALID_ROOM_TYPE_ID = 2L;
    private static final Long INVALID_ROOM_TYPE_ID = 6L;

    @Before
    public void doSetup() {
        EntityManagerFactoryInfo info = (EntityManagerFactoryInfo) entityManager;
        factory = (EntityManagerFactoryImpl) info.getNativeEntityManagerFactory();
        logger.debug("cache statistics=" + factory.getSessionFactory().getStatistics());
    }

    @Test
    public void testRoomType_Caching() {

//        printEntityStatistics("com.travel.mentor.domain.reference.RoomType");
//
//        Assert.assertTrue(entityManager.getCache().contains(RoomType.class, VALID_ROOM_TYPE_ID));
//        Assert.assertFalse(entityManager.getCache().contains(RoomType.class, INVALID_ROOM_TYPE_ID));
//
//
//        System.out.println("\n - Hitting the database with a findAll()");
//        List<ReferenceTypeDTO> roomTypeList = referenceTypeDAO.findAll(RoomType.FIND_ALL_ROOM_TYPES_NAMED_QUERY);
//
//        Assert.assertTrue( entityManager.getCache().contains(RoomType.class, 1L) );
//        Assert.assertTrue( entityManager.getCache().contains(RoomType.class, 2L) );
//        Assert.assertTrue( entityManager.getCache().contains(RoomType.class, 3L) );
//        Assert.assertTrue( entityManager.getCache().contains(RoomType.class, 4L) );
//
//        ReferenceTypeDTO referenceTypeDTO = roomTypeList.get(0);
//        System.out.println("\n - Hitting the database with a read()");
//        referenceTypeDTO = referenceTypeDAO.find(referenceTypeDTO);
//        referenceTypeDTO.setLoggedInUser(referenceTypeDTO.getCreateUserDTO());
//
//
//        referenceTypeDTO.setDescription("changed desc");
//        System.out.println("\n - Hitting the database with a saveOrUpdate()");
//        referenceTypeDTO = referenceTypeDAO.saveOrUpdate(referenceTypeDTO);
//
//
//        System.out.println("\n - Hitting the database with a read() after doing the saveOrUpdate()");
//        ReferenceTypeDTO referenceTypeDTORead = referenceTypeDAO.find(referenceTypeDTO);
//        referenceTypeDTO.setLoggedInUser(referenceTypeDTO.getCreateUserDTO());
//
//        Assert.assertTrue(referenceTypeDTORead.getDescription().equalsIgnoreCase(referenceTypeDTO.getDescription()));
//        Assert.assertTrue( entityManager.getCache().contains(RoomType.class, 1L) );
//        printEntityStatistics("com.travel.mentor.domain.reference.RoomType");
//
//
//        System.out.println("\n - Hitting the database with a findAll()");
//        roomTypeList = referenceTypeDAO.findAll(RoomType.FIND_ALL_ROOM_TYPES_NAMED_QUERY);
//        printEntityStatistics("com.travel.mentor.domain.reference.RoomType");
//
//        System.out.println("\n - Hitting the database with a read()");
//        referenceTypeDTO = referenceTypeDAO.find(roomTypeList.get(0));
//        printEntityStatistics("com.travel.mentor.domain.reference.RoomType");
//
//        Long deletedRoomTypeId = referenceTypeDTO.getId();
//
//        System.out.println("\n - Hitting the database with a delete()");
//        referenceTypeDAO.delete(referenceTypeDTO);
//        printEntityStatistics("com.travel.mentor.domain.reference.RoomType");
//
//
//        System.out.println("\n - Hitting the database with a findAll() after the deleting of an item, checking that hibernate is called again");
//        roomTypeList = referenceTypeDAO.findAll(RoomType.FIND_ALL_ROOM_TYPES_NAMED_QUERY);
//        printEntityStatistics("com.travel.mentor.domain.reference.RoomType");
//
//        System.out.println("\n - Hitting the database with another findAll() to check that hibernate is not called again");
//        roomTypeList = referenceTypeDAO.findAll(RoomType.FIND_ALL_ROOM_TYPES_NAMED_QUERY);
//        printEntityStatistics("com.travel.mentor.domain.reference.RoomType");

        //Assert.assertFalse( entityManager.getCache().contains(RoomType.class, deletedRoomTypeId) );
    }

    private void printEntityStatistics(String entity) {
        System.out.println("\n"+entity+ " : "+factory.getSessionFactory().getStatistics().getEntityStatistics(entity));
    }

}
