package com.travel.mentor.service;

import com.travel.mentor.dao.dto.impl.LocationDTO;

import java.util.List;

public interface LocationService {

    List<LocationDTO> findAll();

    LocationDTO add(LocationDTO locationDTO);

    LocationDTO update(LocationDTO locationDTO);

    void delete(LocationDTO locationDTO);

    LocationDTO find(Long id);

}
