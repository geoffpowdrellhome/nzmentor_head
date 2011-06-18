package com.travel.mentor.dao;

import com.travel.mentor.dao.dto.LocationDTO;

import java.util.List;

public interface LocationDAO {

    List<LocationDTO> findAllLocations();

    void addLocation(LocationDTO locationDTO);

    void updateLocation(LocationDTO locationDTO);

    void deleteLocation(LocationDTO locationDTO);

    LocationDTO findLocation(LocationDTO locationDTO);

}
