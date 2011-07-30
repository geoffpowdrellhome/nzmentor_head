package com.travel.mentor.dao.general;

import com.travel.mentor.dao.dto.general.AccommodationSiteDTO;

import java.util.List;

public interface AccommodationSiteDAO {

    List<AccommodationSiteDTO> findAll();

    AccommodationSiteDTO saveOrUpdate(AccommodationSiteDTO accommodationSiteDTO);

    void delete(AccommodationSiteDTO accommodationSiteDTO);

    AccommodationSiteDTO find(Long id);

}
