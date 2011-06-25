package com.travel.mentor.dao.impl;

import com.travel.mentor.dao.RegionDAO;
import com.travel.mentor.dao.assemble.RegionAssembler;
import com.travel.mentor.dao.base.AbstractMentorDAO;
import com.travel.mentor.dao.dto.impl.RegionDTO;
import com.travel.mentor.model.impl.Region;
import com.travel.mentor.model.impl.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

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
    public RegionDTO add(RegionDTO regionDTO) {
        Region region = regionAssembler.assembleToDomainObject(regionDTO);

        User sessionUser = em.find(User.class, regionDTO.getUserSessionCookieDTO().getUserDTO().getUsername());
        region.setCreateUser(sessionUser);
        region.setUpdateUser(sessionUser);
        region.setCreateDate(new Timestamp(new Date().getTime()));
        region.setUpdateDate(new Timestamp(new Date().getTime()));

        em.persist(region);

        return regionAssembler.assembleToDTO(region);
    }

    @Override
    public RegionDTO update(RegionDTO regionDTO) {
        Region region = regionAssembler.assembleToDomainObject(regionDTO);

        User sessionUser = em.find(User.class, regionDTO.getUserSessionCookieDTO().getUserDTO().getUsername());
        region.setUpdateUser(sessionUser);
        region.setUpdateDate(new Timestamp(new Date().getTime()));

        em.merge(region);

        return regionAssembler.assembleToDTO(region);
    }

    @Override
    public void delete(RegionDTO regionDTO) {
        Region region = em.find(Region.class, regionDTO.getId());
        em.remove(region);
    }

    @Override
    public RegionDTO find(Long id) {
        Region region = em.find(Region.class, id);
        return regionAssembler.assembleToDTO(region);
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
