package com.travel.mentor.dao.impl;

import com.travel.mentor.dao.RegionDAO;
import com.travel.mentor.dao.assemble.RegionAssembler;
import com.travel.mentor.dao.base.AbstractMentorDAO;
import com.travel.mentor.dao.dto.impl.RegionDTO;
import com.travel.mentor.model.impl.Region;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.util.List;

@Repository("regionDAO")
@Transactional
public class RegionDAOImpl extends AbstractMentorDAO implements RegionDAO {

    @Resource
    private RegionAssembler regionAssembler;

    @Override
    public List<RegionDTO> findAll() {
        List<Region> regionList = em.createNamedQuery(Region.FIND_ALL_REGIONS_NAMED_QUERY).getResultList();
        return regionAssembler.assembleToRegionDTOList(regionList);
    }

    @Override
    public RegionDTO add(RegionDTO regionDTO) {
        Region region = regionAssembler.assembleToRegionDomainObject(regionDTO);
        em.persist(region);
        return regionAssembler.assembleToRegionDTO(region);
    }

    @Override
    public RegionDTO update(RegionDTO regionDTO) {
        Region region = regionAssembler.assembleToRegionDomainObject(regionDTO);
        em.merge(region);
        return regionAssembler.assembleToRegionDTO(region);
    }

    @Override
    public void delete(RegionDTO regionDTO) {
        Region region = em.find(Region.class, regionDTO.getId());
        em.remove(region);
    }

    @Override
    public RegionDTO find(Long id) {
        Region region = em.find(Region.class, id);
        return regionAssembler.assembleToRegionDTO(region);
    }

    @Override
    protected void cacheDomainObjects() {
        logger.debug("cacheRegionDomainObjects()");
        StopWatch watch = new StopWatch();
        watch.start("cacheRegionDomainObjects");
        em.createNamedQuery(Region.FIND_ALL_REGIONS_NAMED_QUERY).getResultList();
        watch.stop();
        if (logger.isDebugEnabled()) {
            logger.debug(watch.prettyPrint());
            logger.info("Total Time in Seconds RegionDAOImpl.cacheRegionDomainObjects() = " + watch.getTotalTimeSeconds());
        }
    }

}
