package com.travel.mentor.dao;

import com.travel.mentor.dao.dto.impl.AccommodationSiteDTO;

import java.util.List;

public interface AccommodationSiteDAO {

    List<AccommodationSiteDTO> findAllAccommodationSites();

    void addAccommodationSite(AccommodationSiteDTO accommodationSiteDTO);

    void updateAccommodationSite(AccommodationSiteDTO accommodationSiteDTO);

    void deleteAccommodationSite(AccommodationSiteDTO accommodationSiteDTO);

    AccommodationSiteDTO findAccommodationSite(Long id);

}
