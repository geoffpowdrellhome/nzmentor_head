package com.travel.mentor.dao.general.impl;

import com.travel.mentor.dao.assemble.general.AccommodationSiteAssembler;
import com.travel.mentor.dao.dto.general.AccommodationSiteDTO;
import com.travel.mentor.dao.general.AccommodationSiteDAO;
import com.travel.mentor.dao.base.AbstractMentorDAO;
import com.travel.mentor.domain.general.AccommodationSite;
import com.travel.mentor.domain.general.Location;
import com.travel.mentor.domain.reference.AccommodationSiteType;
import com.travel.mentor.domain.reference.SiteType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository("accommodationSiteDAO")
@Transactional
public class AccommodationSiteDAOImpl extends AbstractMentorDAO implements AccommodationSiteDAO {

    @Resource
    private AccommodationSiteAssembler accommodationSiteAssembler;

    @Override
    public List<AccommodationSiteDTO> findAll() {
        List<AccommodationSite> accommodationSiteList = em.createNamedQuery(AccommodationSite.FIND_ALL_ACCOMMODATION_SITES_NAMED_QUERY).getResultList();
        return accommodationSiteAssembler.assembleToDTOList(accommodationSiteList);
    }

    @Override
    public AccommodationSiteDTO saveOrUpdate(AccommodationSiteDTO accommodationSiteDTO) {
        AccommodationSite accommodationSite = accommodationSiteAssembler.assembleToDomainObject(accommodationSiteDTO);

        if (accommodationSiteDTO.getId() == null || em.find(AccommodationSite.class, accommodationSiteDTO.getId()) == null) {
            accommodationSite.setCreateUser( secureUserAssembler.assembleToDomainObject(accommodationSiteDTO.getLoggedInUser()) );
            accommodationSite.setCreateDate(new Timestamp(new Date().getTime()));
        }

        accommodationSite.setAccommodationSiteType(em.find(AccommodationSiteType.class, accommodationSiteDTO.getAccommodationSiteTypeDTO().getId()));
        accommodationSite.setSiteType(em.find(SiteType.class, accommodationSiteDTO.getSiteTypeDTO().getId()));
        accommodationSite.setLocation(em.find(Location.class, accommodationSiteDTO.getLocationDTO().getId()));

        accommodationSite.setUpdateUser( secureUserAssembler.assembleToDomainObject(accommodationSiteDTO.getLoggedInUser()) );
        accommodationSite.setUpdateDate(new Timestamp(new Date().getTime()));

        em.merge(accommodationSite);

        return accommodationSiteAssembler.assembleToDTO(accommodationSite);
    }

    @Override
    public void delete(AccommodationSiteDTO accommodationSiteDTO) {
        AccommodationSite accommodationSite = em.find(AccommodationSite.class, accommodationSiteDTO.getId());
        em.remove(accommodationSite);
    }

    @Override
    public AccommodationSiteDTO find(Long id) {
        AccommodationSite accommodationSite = em.find(AccommodationSite.class, id);
        return accommodationSiteAssembler.assembleToDTO(accommodationSite);
    }

    @Override
    protected void cacheDomainObjects() {
        logger.debug(this.getClass().getName() +".cacheDomainObjects()");
        StopWatch watch = new StopWatch();
        watch.start(this.getClass().getName() +".cacheDomainObjects()");
        em.createNamedQuery(AccommodationSite.FIND_ALL_ACCOMMODATION_SITES_NAMED_QUERY).getResultList();
        watch.stop();
        if (logger.isDebugEnabled()) {
            logger.debug(watch.prettyPrint());
            logger.info("Total Time in Seconds "+this.getClass().getName() +".cacheDomainObjects() = " + watch.getTotalTimeSeconds());
        }
    }

}
