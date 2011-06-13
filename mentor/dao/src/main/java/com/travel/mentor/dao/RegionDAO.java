package com.travel.mentor.dao;

import com.travel.mentor.dao.dto.RegionDTO;

import java.util.List;

public interface RegionDAO {

    List<RegionDTO> findAllRegions();

    void addRegion(RegionDTO regionDTO);

    void updateRegion(RegionDTO regionDTO);

    void deleteRegion(RegionDTO regionDTO);

}
