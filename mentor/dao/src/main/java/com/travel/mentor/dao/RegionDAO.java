package com.travel.mentor.dao;

import com.travel.mentor.dao.dto.impl.RegionDTO;

import java.util.List;

public interface RegionDAO {

    List<RegionDTO> findAll();

    RegionDTO add(RegionDTO regionDTO);

    RegionDTO update(RegionDTO regionDTO);

    void delete(RegionDTO regionDTO);

    RegionDTO find(Long id);

}
