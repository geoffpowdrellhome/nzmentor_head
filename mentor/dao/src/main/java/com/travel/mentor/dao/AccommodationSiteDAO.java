package com.travel.mentor.dao;

import com.travel.mentor.dao.dto.impl.AccommodationSiteDTO;

import java.util.List;

public interface AccommodationSiteDAO {

    List<AccommodationSiteDTO> findAll();

    AccommodationSiteDTO add(AccommodationSiteDTO accommodationSiteDTO);

    AccommodationSiteDTO update(AccommodationSiteDTO accommodationSiteDTO);

    void delete(AccommodationSiteDTO accommodationSiteDTO);

    AccommodationSiteDTO find(Long id);

}
