package com.travel.mentor.dao.reference.impl;

import com.travel.mentor.dao.assemble.reference.ReferenceTypeAssembler;
import com.travel.mentor.dao.base.AbstractMentorDAO;
import com.travel.mentor.dao.dto.reference.ReferenceTypeDTO;
import com.travel.mentor.dao.reference.ReferenceTypeDAO;
import com.travel.mentor.domain.base.AbstractAuditedIdNameDescEntity;
import com.travel.mentor.domain.reference.*;
import net.sf.ehcache.CacheManager;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Repository("referenceTypeDAO")
@Transactional
public class ReferenceTypeDAOImpl extends AbstractMentorDAO implements ReferenceTypeDAO {

    @Resource
    private ReferenceTypeAssembler referenceTypeAssembler;

    @Resource
    private CacheManager cacheManager;


    @Override
    public List<ReferenceTypeDTO> findAll(String findAllNamedQuery) {
        Assert.notNull(findAllNamedQuery);
        List<AbstractAuditedIdNameDescEntity> referenceTypeDTOList = em.createNamedQuery(findAllNamedQuery).setHint("org.hibernate.cacheable", true).getResultList();
        return referenceTypeAssembler.assembleToDTOList(referenceTypeDTOList);
    }


    @Override
    public ReferenceTypeDTO saveOrUpdate(ReferenceTypeDTO referenceTypeDTO) {

        AbstractAuditedIdNameDescEntity abstractAuditedNameDescEntity = referenceTypeAssembler.assembleToDomainObject(referenceTypeDTO);

        if (referenceTypeDTO.getId() == null || em.find(abstractAuditedNameDescEntity.getClass(), referenceTypeDTO.getId()) == null) {
            abstractAuditedNameDescEntity.setCreateUser(secureUserAssembler.assembleToDomainObject(referenceTypeDTO.getLoggedInUser()));
            abstractAuditedNameDescEntity.setCreateDate(new Timestamp(new Date().getTime()));
            abstractAuditedNameDescEntity.setUpdateUser(secureUserAssembler.assembleToDomainObject(referenceTypeDTO.getLoggedInUser()));
            abstractAuditedNameDescEntity.setUpdateDate(new Timestamp(new Date().getTime()));
            em.persist(abstractAuditedNameDescEntity);
        } else {
            AbstractAuditedIdNameDescEntity existingEntityRecord = em.find(abstractAuditedNameDescEntity.getClass(), referenceTypeDTO.getId());
            BeanUtils.copyProperties(abstractAuditedNameDescEntity, existingEntityRecord);
            abstractAuditedNameDescEntity.setUpdateUser(secureUserAssembler.assembleToDomainObject(referenceTypeDTO.getLoggedInUser()));
            abstractAuditedNameDescEntity.setUpdateDate(new Timestamp(new Date().getTime()));

            em.merge(existingEntityRecord);
        }

        return referenceTypeAssembler.assembleToDTO(abstractAuditedNameDescEntity);
    }

    @Override
    public void delete(ReferenceTypeDTO referenceTypeDTO) {
        AbstractAuditedIdNameDescEntity abstractAuditedNameDescEntity = (AbstractAuditedIdNameDescEntity) BeanUtils.instantiateClass(referenceTypeDTO.getEntityClass());
        abstractAuditedNameDescEntity = em.find(abstractAuditedNameDescEntity.getClass(), referenceTypeDTO.getId());
        em.remove(abstractAuditedNameDescEntity);
        em.flush();
        em.getEntityManagerFactory().getCache().evict(abstractAuditedNameDescEntity.getClass(), abstractAuditedNameDescEntity.getId());
    }

    @Override
    public ReferenceTypeDTO find(ReferenceTypeDTO referenceTypeDTO) {
        AbstractAuditedIdNameDescEntity abstractAuditedNameDescEntity = (AbstractAuditedIdNameDescEntity) BeanUtils.instantiateClass(referenceTypeDTO.getEntityClass());
        abstractAuditedNameDescEntity = em.find(abstractAuditedNameDescEntity.getClass(), referenceTypeDTO.getId());
        return referenceTypeAssembler.assembleToDTO(abstractAuditedNameDescEntity);
    }

    @PostConstruct
    protected void cacheDomainObjects() {
        List<String> namedQueries = new ArrayList<String>();

        namedQueries.add(AccommodationSiteType.FIND_ALL_ACCOMMODATION_SITE_TYPES_NAMED_QUERY);
        namedQueries.add(ActivitySiteType.FIND_ALL_ACTIVITY_SITE_TYPES_NAMED_QUERY);
        namedQueries.add(ClimateConditionType.FIND_ALL_CLIMATE_CONDITION_TYPES_NAMED_QUERY);
        namedQueries.add(ClimateWindfactorType.FIND_ALL_CLIMATE_WINDFACTOR_TYPES_NAMED_QUERY);
        namedQueries.add(ClothingType.FIND_ALL_CLOTHING_TYPES_NAMED_QUERY);
        namedQueries.add(EventType.FIND_ALL_EVENT_TYPES_NAMED_QUERY);
        namedQueries.add(FootwearType.FIND_ALL_FOOTWEAR_TYPES_NAMED_QUERY);
        namedQueries.add(HeadwearType.FIND_ALL_HEADWEAR_TYPES_NAMED_QUERY);
        namedQueries.add(ItemType.FIND_ALL_ITEM_TYPES_NAMED_QUERY);
        namedQueries.add(LocationType.FIND_ALL_LOCATION_TYPES_NAMED_QUERY);
        namedQueries.add(PopularityRankingType.FIND_ALL_POPULARITY_RANKING_TYPES_NAMED_QUERY);
        namedQueries.add(RoomType.FIND_ALL_ROOM_TYPES_NAMED_QUERY);
        namedQueries.add(RoomConfigurationType.FIND_ALL_ROOM_CONFIGURATION_TYPES_NAMED_QUERY);
        namedQueries.add(SiteType.FIND_ALL_SITE_TYPES_NAMED_QUERY);
        namedQueries.add(SupplierType.FIND_ALL_SITE_TYPES_NAMED_QUERY);
        namedQueries.add(TransferSiteType.FIND_ALL_TRANSFER_SITE_TYPES_NAMED_QUERY);
        namedQueries.add(VehicleHireSiteType.FIND_ALL_VEHICLE_SITE_TYPES_NAMED_QUERY);

        super.cacheDomainObjects(namedQueries);
    }

}
