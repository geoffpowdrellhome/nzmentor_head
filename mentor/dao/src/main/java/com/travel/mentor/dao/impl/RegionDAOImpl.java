package com.travel.mentor.dao.impl;

import com.travel.mentor.dao.RegionDAO;
import com.travel.mentor.dao.assemble.RegionAssembler;
import com.travel.mentor.dao.base.AbstractMentorDAO;
import com.travel.mentor.dao.dto.RegionDTO;
import com.travel.mentor.model.impl.Region;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Repository("regionDAO")
@Transactional
public class RegionDAOImpl extends AbstractMentorDAO implements RegionDAO {

    @Resource
    private RegionAssembler regionAssembler;

    @Override
    public List<RegionDTO> findAllRegions() {
        List<Region> regionList = em.createNamedQuery(Region.FIND_ALL_REGIONS_NAMED_QUERY).getResultList();
        return regionAssembler.assembleToRegionDTOList(regionList);
    }

    @Override
    public void addRegion(RegionDTO regionDTO) {
        Region region = regionAssembler.assembleToRegionDomainObject(regionDTO);
        em.persist(region);
    }

    @Override
    public void updateRegion(RegionDTO regionDTO) {
        Region region = regionAssembler.assembleToRegionDomainObject(regionDTO);
        em.merge(region);
    }

    @Override
    public void deleteRegion(RegionDTO regionDTO) {
        Region region = em.find(Region.class, regionDTO.getId());
        em.remove(region);
    }
}
