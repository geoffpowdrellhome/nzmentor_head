package com.travel.mentor.dao.general;

import com.travel.mentor.dao.dto.general.LocationDTO;

import java.util.List;

public interface LocationDAO {

    List<LocationDTO> findAll();

    LocationDTO saveOrUpdate(LocationDTO locationDTO);

    void delete(LocationDTO locationDTO);

    LocationDTO find(Long id);

}
