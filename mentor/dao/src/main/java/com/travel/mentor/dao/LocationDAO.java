package com.travel.mentor.dao;

import com.travel.mentor.dao.dto.impl.LocationDTO;

import java.util.List;

public interface LocationDAO {

    List<LocationDTO> findAll();

    LocationDTO saveOrUpdate(LocationDTO locationDTO);

    void delete(LocationDTO locationDTO);

    LocationDTO find(Long id);

}
