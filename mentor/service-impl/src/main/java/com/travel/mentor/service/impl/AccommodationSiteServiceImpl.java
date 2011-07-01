package com.travel.mentor.service.impl;

import com.travel.mentor.dao.AccommodationSiteDAO;
import com.travel.mentor.dao.dto.impl.AccommodationSiteDTO;
import com.travel.mentor.service.AccommodationSiteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("accommodationSiteService")
@Transactional
public class AccommodationSiteServiceImpl implements AccommodationSiteService {

    @Resource(name="accommodationSiteDAO")
    private AccommodationSiteDAO accommodationSiteDAO;

    @Override
    public List<AccommodationSiteDTO> findAll() {
        return accommodationSiteDAO.findAll();
    }

    @Override
    public AccommodationSiteDTO add(AccommodationSiteDTO accommodationSiteDTO) {
        return accommodationSiteDAO.add(accommodationSiteDTO);
    }

    @Override
    public AccommodationSiteDTO update(AccommodationSiteDTO accommodationSiteDTO) {
        return accommodationSiteDAO.update(accommodationSiteDTO);
    }

    @Override
    public void delete(AccommodationSiteDTO accommodationSiteDTO) {
        accommodationSiteDAO.delete(accommodationSiteDTO);
    }

    @Override
    public AccommodationSiteDTO find(Long id) {
        return accommodationSiteDAO.find(id);
    }

}
