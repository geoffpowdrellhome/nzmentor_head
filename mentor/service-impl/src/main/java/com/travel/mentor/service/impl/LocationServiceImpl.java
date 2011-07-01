package com.travel.mentor.service.impl;

import com.travel.mentor.dao.LocationDAO;
import com.travel.mentor.dao.dto.impl.LocationDTO;
import com.travel.mentor.service.LocationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("locationService")
@Transactional
public class LocationServiceImpl implements LocationService {

    @Resource(name="locationDAO")
    private LocationDAO locationDAO;

    @Override
    public List<LocationDTO> findAll() {
        return locationDAO.findAll();
    }

    @Override
    public LocationDTO add(LocationDTO locationDTO) {
        return locationDAO.add(locationDTO);
    }

    @Override
    public LocationDTO update(LocationDTO locationDTO) {
        return locationDAO.update(locationDTO);
    }

    @Override
    public void delete(LocationDTO locationDTO) {
        locationDAO.delete(locationDTO);
    }

    @Override
    public LocationDTO find(Long id) {
        return locationDAO.find(id);
    }

}
