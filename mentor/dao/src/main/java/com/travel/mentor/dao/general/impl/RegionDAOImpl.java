package com.travel.mentor.dao.general.impl;

import com.travel.mentor.dao.assemble.general.RegionAssembler;
import com.travel.mentor.dao.base.AbstractMentorDAO;
import com.travel.mentor.dao.dto.general.RegionDTO;
import com.travel.mentor.dao.general.RegionDAO;
import com.travel.mentor.domain.general.Region;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
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
        Region region;
        if (regionDTO.getId() == null || em.find(Region.class, regionDTO.getId()) == null) {
            region = regionAssembler.assembleToEntityInstance(regionDTO);
            region.setCreateUser( secureUserAssembler.assembleToEntityInstance(regionDTO.getLoggedInUser()) );
            region.setCreateDate(new Timestamp(new Date().getTime()));
        }
        else {
            region = em.find(Region.class, regionDTO.getId());
            regionAssembler.deepCopy(regionDTO, region);
        }

        region.setUpdateUser( secureUserAssembler.assembleToEntityInstance(regionDTO.getLoggedInUser()) );
        region.setUpdateDate(new Timestamp(new Date().getTime()));
        em.merge(region);

        return regionAssembler.assembleToDTOInstance(region);
    }

    @Override
    public void delete(RegionDTO regionDTO) {
        Region region = em.find(Region.class, regionDTO.getId());
        em.remove(region);
        em.getEntityManagerFactory().getCache().evict(region.getClass(), region.getId());
    }

    @Override
    public RegionDTO find(Long id) {
        Region region = em.find(Region.class, id);
        return regionAssembler.assembleToDTOInstance(region);
    }

    @PostConstruct
    protected void cacheDomainObjects() {
        List<String> namedQueries = new ArrayList<String>();
        namedQueries.add(Region.FIND_ALL_REGIONS_NAMED_QUERY);
        super.cacheDomainObjects(namedQueries);
    }

}
