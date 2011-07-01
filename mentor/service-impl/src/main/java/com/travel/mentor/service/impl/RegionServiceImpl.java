package com.travel.mentor.service.impl;

import com.travel.mentor.dao.RegionDAO;
import com.travel.mentor.dao.dto.impl.RegionDTO;
import com.travel.mentor.service.RegionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("regionService")
@Transactional
public class RegionServiceImpl implements RegionService {

    @Resource(name="regionDAO")
    private RegionDAO regionDAO;

    @Override
    public List<RegionDTO> findAll() {
        return regionDAO.findAll();
    }

    @Override
    public RegionDTO add(RegionDTO regionDTO) {
        return regionDAO.add(regionDTO);
    }

    @Override
    public RegionDTO update(RegionDTO regionDTO) {
        return regionDAO.update(regionDTO);
    }

    @Override
    public void delete(RegionDTO regionDTO) {
        regionDAO.delete(regionDTO);
    }

    @Override
    public RegionDTO find(Long id) {
        return regionDAO.find(id);
    }

}
