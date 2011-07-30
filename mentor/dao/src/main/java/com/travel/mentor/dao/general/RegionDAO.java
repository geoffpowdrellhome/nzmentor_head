package com.travel.mentor.dao.general;

import com.travel.mentor.dao.dto.general.RegionDTO;

import java.util.List;

public interface RegionDAO {

    List<RegionDTO> findAll();

    RegionDTO saveOrUpdate(RegionDTO regionDTO);

    void delete(RegionDTO regionDTO);

    RegionDTO find(Long id);

}
