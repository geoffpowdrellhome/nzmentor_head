package com.travel.mentor.dao.general.impl;

import com.travel.mentor.dao.assemble.general.RegionAssembler;
import com.travel.mentor.dao.base.AbstractMentorDAO;
import com.travel.mentor.dao.dto.general.RegionDTO;
import com.travel.mentor.dao.general.RegionDAO;
import com.travel.mentor.domain.general.Region;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository("regionDAO")
@Transactional
public class RegionDAOImpl extends AbstractMentorDAO implements RegionDAO {

    @Resource
    private RegionAssembler regionAssembler;

    @Override
    public List<RegionDTO> findAll() {
        List<Region> regionList = em.createNamedQuery(Region.FIND_ALL_REGIONS_NAMED_QUERY).getResultList();
        return regionAssembler.assembleToDTOList(regionList);
    }

    @Override
    public RegionDTO saveOrUpdate(RegionDTO regionDTO) {
        Region region = regionAssembler.assembleToDomainObject(regionDTO);

        if (regionDTO.getId() == null || em.find(Region.class, regionDTO.getId()) == null) {
            region.setCreateUser(secureUserAssembler.assembleToDomainObject(regionDTO.getLoggedInUser()));
            region.setCreateDate(new Timestamp(new Date().getTime()));
            region.setUpdateUser(secureUserAssembler.assembleToDomainObject(regionDTO.getLoggedInUser()));
            region.setUpdateDate(new Timestamp(new Date().getTime()));
            em.persist(region);
        } else {
            Region existingRegion = em.find(region.getClass(), regionDTO.getId());
            BeanUtils.copyProperties(region, existingRegion);
            region.setUpdateUser(secureUserAssembler.assembleToDomainObject(regionDTO.getLoggedInUser()));
            region.setUpdateDate(new Timestamp(new Date().getTime()));
            em.merge(region);
        }

        return regionAssembler.assembleToDTO(region);
    }

    @Override
    public void delete(RegionDTO regionDTO) {
        Region region = em.find(Region.class, regionDTO.getId());
        em.remove(region);
        em.flush();
        em.getEntityManagerFactory().getCache().evict(region.getClass(), region.getId());
    }

    @Override
    public RegionDTO find(Long id) {
        Region region = em.find(Region.class, id);
        return regionAssembler.assembleToDTO(region);
    }

    @Override
    protected void cacheDomainObjects() {
        logger.debug(this.getClass().getName() + ".cacheDomainObjects()");
        StopWatch watch = new StopWatch();
        watch.start(this.getClass().getName() + ".cacheDomainObjects()");
        em.createNamedQuery(Region.FIND_ALL_REGIONS_NAMED_QUERY).getResultList();
        watch.stop();
        if (logger.isDebugEnabled()) {
            logger.debug(watch.prettyPrint());
            logger.info("Total Time in Seconds " + this.getClass().getName() + ".cacheDomainObjects() = " + watch.getTotalTimeSeconds());
        }
    }

}
