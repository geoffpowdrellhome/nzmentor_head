package com.travel.mentor.dao.type.impl;

import com.travel.mentor.dao.assemble.ReferenceTypeAssembler;
import com.travel.mentor.dao.base.AbstractMentorDAO;
import com.travel.mentor.dao.dto.base.ReferenceTypeDTO;
import com.travel.mentor.dao.type.ReferenceTypeDAO;
import com.travel.mentor.model.base.AbstractAuditedNameDescEntity;
import com.travel.mentor.type.impl.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.util.List;

@Repository("referenceTypeDAO")
@Transactional
public class ReferenceTypeDAOImpl extends AbstractMentorDAO implements ReferenceTypeDAO {

    @Resource
    private ReferenceTypeAssembler referenceTypeAssembler;

    @Override
    public List<ReferenceTypeDTO> findAll(String findAllNamedQuery) {
        Assert.notNull(findAllNamedQuery);
        List<AbstractAuditedNameDescEntity> referenceTypeDTOList = em.createNamedQuery(findAllNamedQuery).getResultList();
        return referenceTypeAssembler.assembleToDTOList(referenceTypeDTOList);
    }

    @Override
    public ReferenceTypeDTO add(ReferenceTypeDTO referenceTypeDTO) {
        AbstractAuditedNameDescEntity abstractAuditedNameDescEntity = referenceTypeAssembler.assembleToDomainObject(referenceTypeDTO);
        em.persist(abstractAuditedNameDescEntity);
        return referenceTypeAssembler.assembleToDTO(abstractAuditedNameDescEntity);
    }

    @Override
    public ReferenceTypeDTO update(ReferenceTypeDTO referenceTypeDTO) {
        AbstractAuditedNameDescEntity abstractAuditedNameDescEntity = referenceTypeAssembler.assembleToDomainObject(referenceTypeDTO);
        em.merge(abstractAuditedNameDescEntity);
        return referenceTypeAssembler.assembleToDTO(abstractAuditedNameDescEntity);
    }

    @Override
    public void delete(ReferenceTypeDTO referenceTypeDTO) {
        AbstractAuditedNameDescEntity abstractAuditedNameDescEntity = (AbstractAuditedNameDescEntity) BeanUtils.instantiateClass(referenceTypeDTO.getEntityClass());
        abstractAuditedNameDescEntity = em.find(abstractAuditedNameDescEntity.getClass(), referenceTypeDTO.getId());
        em.remove(abstractAuditedNameDescEntity);
    }

    @Override
    public ReferenceTypeDTO find(ReferenceTypeDTO referenceTypeDTO) {
        AbstractAuditedNameDescEntity abstractAuditedNameDescEntity = (AbstractAuditedNameDescEntity) BeanUtils.instantiateClass(referenceTypeDTO.getEntityClass());
        abstractAuditedNameDescEntity = em.find(abstractAuditedNameDescEntity.getClass(), referenceTypeDTO.getId());
        return referenceTypeAssembler.assembleToDTO(abstractAuditedNameDescEntity);
    }

    @Override
    protected void cacheDomainObjects() {
        cacheAccommodationSiteTypeDomainObjects();
        cacheActivitySiteTypeDomainObjects();
        cacheClimateConditionTypeDomainObjects();
        cacheClimateWindfactorTypeDomainObjects();
        cacheClothingTypeDomainObjects();
        cacheEventTypeDomainObjects();
        cacheFootwearTypeDomainObjects();
        cacheHeadwearTypeDomainObjects();
        cacheItemTypeDomainObjects();
        cacheLocationTypeDomainObjects();
        cachePopularityRankingTypeDomainObjects();
        cacheRoomTypeDomainObjects();
        cacheRoomConfigurationTypeDomainObjects();
        cacheSiteTypeDomainObjects();
        cacheSupplierTypeDomainObjects();
        cacheTransferSiteTypeDomainObjects();
        cacheVehicleHireSiteTypeDomainObjects();
    }


    /**
     *  private methods - caching of all types of reference type domain objects
     */
    private void cacheAccommodationSiteTypeDomainObjects() {
        logger.debug("cacheAccommodationSiteTypeDomainObjects()");
        StopWatch watch = new StopWatch();
        watch.start("cacheAccommodationSiteTypeDomainObjects");
        em.createNamedQuery(AccommodationSiteType.FIND_ALL_ACCOMMODATION_SITE_TYPES_NAMED_QUERY).getResultList();
        watch.stop();
        if (logger.isDebugEnabled()) {
            logger.debug(watch.prettyPrint());
            logger.info("Total Time in Seconds ReferenceTypeDAOImpl.cacheAccommodationSiteTypeDomainObjects() = " + watch.getTotalTimeSeconds());
        }
    }

    private void cacheActivitySiteTypeDomainObjects() {
        logger.debug("cacheActivitySiteTypeDomainObjects()");
        StopWatch watch = new StopWatch();
        watch.start("cacheActivitySiteTypeDomainObjects");
        em.createNamedQuery(ActivitySiteType.FIND_ALL_ACTIVITY_SITE_TYPES_NAMED_QUERY).getResultList();
        watch.stop();
        if (logger.isDebugEnabled()) {
            logger.debug(watch.prettyPrint());
            logger.info("Total Time in Seconds ReferenceTypeDAOImpl.cacheActivitySiteTypeDomainObjects() = " + watch.getTotalTimeSeconds());
        }
    }

    private void cacheClimateConditionTypeDomainObjects() {
        logger.debug("cacheClimateConditionTypeDomainObjects()");
        StopWatch watch = new StopWatch();
        watch.start("cacheClimateConditionTypeDomainObjects");
        em.createNamedQuery(ClimateConditionType.FIND_ALL_CLIMATE_CONDITION_TYPES_NAMED_QUERY).getResultList();
        watch.stop();
        if (logger.isDebugEnabled()) {
            logger.debug(watch.prettyPrint());
            logger.info("Total Time in Seconds ReferenceTypeDAOImpl.cacheClimateConditionTypeDomainObjects() = " + watch.getTotalTimeSeconds());
        }
    }

    private void cacheClimateWindfactorTypeDomainObjects() {
        logger.debug("cacheClimateWindfactorTypeDomainObjects()");
        StopWatch watch = new StopWatch();
        watch.start("cacheClimateWindfactorTypeDomainObjects");
        em.createNamedQuery(ClimateWindfactorType.FIND_ALL_CLIMATE_WINDFACTOR_TYPES_NAMED_QUERY).getResultList();
        watch.stop();
        if (logger.isDebugEnabled()) {
            logger.debug(watch.prettyPrint());
            logger.info("Total Time in Seconds ReferenceTypeDAOImpl.cacheClimateWindfactorTypeDomainObjects() = " + watch.getTotalTimeSeconds());
        }
    }

    private void cacheClothingTypeDomainObjects() {
        logger.debug("cacheClothingTypeDomainObjects()");
        StopWatch watch = new StopWatch();
        watch.start("cacheClothingTypeDomainObjects");
        em.createNamedQuery(ClothingType.FIND_ALL_CLOTHING_TYPES_NAMED_QUERY).getResultList();
        watch.stop();
        if (logger.isDebugEnabled()) {
            logger.debug(watch.prettyPrint());
            logger.info("Total Time in Seconds ReferenceTypeDAOImpl.cacheClothingTypeDomainObjects() = " + watch.getTotalTimeSeconds());
        }
    }

    private void cacheEventTypeDomainObjects() {
        logger.debug("cacheEventTypeDomainObjects()");
        StopWatch watch = new StopWatch();
        watch.start("cacheEventTypeDomainObjects");
        em.createNamedQuery(EventType.FIND_ALL_EVENT_TYPES_NAMED_QUERY).getResultList();
        watch.stop();
        if (logger.isDebugEnabled()) {
            logger.debug(watch.prettyPrint());
            logger.info("Total Time in Seconds ReferenceTypeDAOImpl.cacheEventTypeDomainObjects() = " + watch.getTotalTimeSeconds());
        }
    }

    private void cacheFootwearTypeDomainObjects() {
        logger.debug("cacheFootwearTypeDomainObjects()");
        StopWatch watch = new StopWatch();
        watch.start("cacheFootwearTypeDomainObjects");
        em.createNamedQuery(FootwearType.FIND_ALL_FOOTWEAR_TYPES_NAMED_QUERY).getResultList();
        watch.stop();
        if (logger.isDebugEnabled()) {
            logger.debug(watch.prettyPrint());
            logger.info("Total Time in Seconds ReferenceTypeDAOImpl.cacheFootwearTypeDomainObjects() = " + watch.getTotalTimeSeconds());
        }
    }

    private void cacheHeadwearTypeDomainObjects() {
        logger.debug("cacheHeadwearTypeDomainObjects()");
        StopWatch watch = new StopWatch();
        watch.start("cacheHeadwearTypeDomainObjects");
        em.createNamedQuery(HeadwearType.FIND_ALL_HEADWEAR_TYPES_NAMED_QUERY).getResultList();
        watch.stop();
        if (logger.isDebugEnabled()) {
            logger.debug(watch.prettyPrint());
            logger.info("Total Time in Seconds ReferenceTypeDAOImpl.cacheHeadwearTypeDomainObjects() = " + watch.getTotalTimeSeconds());
        }
    }

    private void cacheItemTypeDomainObjects() {
        logger.debug("cacheItemTypeDomainObjects()");
        StopWatch watch = new StopWatch();
        watch.start("cacheItemTypeDomainObjects");
        em.createNamedQuery(ItemType.FIND_ALL_ITEM_TYPES_NAMED_QUERY).getResultList();
        watch.stop();
        if (logger.isDebugEnabled()) {
            logger.debug(watch.prettyPrint());
            logger.info("Total Time in Seconds ReferenceTypeDAOImpl.cacheItemTypeDomainObjects() = " + watch.getTotalTimeSeconds());
        }
    }

    private void cacheLocationTypeDomainObjects() {
        logger.debug("cacheLocationTypeDomainObjects()");
        StopWatch watch = new StopWatch();
        watch.start("cacheLocationTypeDomainObjects");
        em.createNamedQuery(LocationType.FIND_ALL_LOCATION_TYPES_NAMED_QUERY).getResultList();
        watch.stop();
        if (logger.isDebugEnabled()) {
            logger.debug(watch.prettyPrint());
            logger.info("Total Time in Seconds ReferenceTypeDAOImpl.cacheLocationTypeDomainObjects() = " + watch.getTotalTimeSeconds());
        }
    }

    private void cachePopularityRankingTypeDomainObjects() {
        logger.debug("cachePopularityRankingTypeDomainObjects()");
        StopWatch watch = new StopWatch();
        watch.start("cachePopularityRankingTypeDomainObjects");
        em.createNamedQuery(PopularityRankingType.FIND_ALL_POPULARITY_RANKING_TYPES_NAMED_QUERY).getResultList();
        watch.stop();
        if (logger.isDebugEnabled()) {
            logger.debug(watch.prettyPrint());
            logger.info("Total Time in Seconds ReferenceTypeDAOImpl.cachePopularityRankingTypeDomainObjects() = " + watch.getTotalTimeSeconds());
        }
    }

    private void cacheRoomTypeDomainObjects() {
        logger.debug("cacheRoomTypeDomainObjects()");
        StopWatch watch = new StopWatch();
        watch.start("cacheRoomTypeDomainObjects");
        em.createNamedQuery(RoomType.FIND_ALL_ROOM_TYPES_NAMED_QUERY).getResultList();
        watch.stop();
        if (logger.isDebugEnabled()) {
            logger.debug(watch.prettyPrint());
            logger.info("Total Time in Seconds ReferenceTypeDAOImpl.cacheRoomTypeDomainObjects() = " + watch.getTotalTimeSeconds());
        }
    }

    private void cacheRoomConfigurationTypeDomainObjects() {
        logger.debug("cacheRoomConfigurationTypeDomainObjects()");
        StopWatch watch = new StopWatch();
        watch.start("cacheRoomConfigurationTypeDomainObjects");
        em.createNamedQuery(RoomConfigurationType.FIND_ALL_ROOM_CONFIGURATION_TYPES_NAMED_QUERY).getResultList();
        watch.stop();
        if (logger.isDebugEnabled()) {
            logger.debug(watch.prettyPrint());
            logger.info("Total Time in Seconds ReferenceTypeDAOImpl.cacheRoomConfigurationTypeDomainObjects() = " + watch.getTotalTimeSeconds());
        }
    }

    private void cacheSiteTypeDomainObjects() {
        logger.debug("cacheSiteTypeDomainObjects()");
        StopWatch watch = new StopWatch();
        watch.start("cacheSiteTypeDomainObjects");
        em.createNamedQuery(SiteType.FIND_ALL_SITE_TYPES_NAMED_QUERY).getResultList();
        watch.stop();
        if (logger.isDebugEnabled()) {
            logger.debug(watch.prettyPrint());
            logger.info("Total Time in Seconds ReferenceTypeDAOImpl.cacheSiteTypeDomainObjects() = " + watch.getTotalTimeSeconds());
        }
    }

    private void cacheSupplierTypeDomainObjects() {
        logger.debug("cacheSupplierTypeDomainObjects()");
        StopWatch watch = new StopWatch();
        watch.start("cacheSupplierTypeDomainObjects");
        em.createNamedQuery(SupplierType.FIND_ALL_SITE_TYPES_NAMED_QUERY).getResultList();
        watch.stop();
        if (logger.isDebugEnabled()) {
            logger.debug(watch.prettyPrint());
            logger.info("Total Time in Seconds ReferenceTypeDAOImpl.cacheSupplierTypeDomainObjects() = " + watch.getTotalTimeSeconds());
        }
    }

    private void cacheTransferSiteTypeDomainObjects() {
        logger.debug("cacheTransferSiteTypeDomainObjects()");
        StopWatch watch = new StopWatch();
        watch.start("cacheTransferSiteTypeDomainObjects");
        em.createNamedQuery(TransferSiteType.FIND_ALL_TRANSFER_SITE_TYPES_NAMED_QUERY).getResultList();
        watch.stop();
        if (logger.isDebugEnabled()) {
            logger.debug(watch.prettyPrint());
            logger.info("Total Time in Seconds ReferenceTypeDAOImpl.cacheTransferSiteTypeDomainObjects() = " + watch.getTotalTimeSeconds());
        }
    }

    private void cacheVehicleHireSiteTypeDomainObjects() {
        logger.debug("cacheVehicleHireSiteTypeDomainObjects()");
        StopWatch watch = new StopWatch();
        watch.start("cacheVehicleHireSiteTypeDomainObjects");
        em.createNamedQuery(VehicleHireSiteType.FIND_ALL_VEHICLE_SITE_TYPES_NAMED_QUERY).getResultList();
        watch.stop();
        if (logger.isDebugEnabled()) {
            logger.debug(watch.prettyPrint());
            logger.info("Total Time in Seconds ReferenceTypeDAOImpl.cacheVehicleHireSiteTypeDomainObjects() = " + watch.getTotalTimeSeconds());
        }
    }

}
