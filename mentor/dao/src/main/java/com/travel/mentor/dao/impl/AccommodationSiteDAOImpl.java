package com.travel.mentor.dao.impl;

import com.travel.mentor.dao.AccommodationSiteDAO;
import com.travel.mentor.dao.assemble.AccommodationSiteAssembler;
import com.travel.mentor.dao.base.AbstractMentorDAO;
import com.travel.mentor.dao.dto.impl.AccommodationSiteDTO;
import com.travel.mentor.model.impl.AccommodationSite;
import com.travel.mentor.model.impl.Location;
import com.travel.mentor.model.impl.User;
import com.travel.mentor.type.impl.AccommodationSiteType;
import com.travel.mentor.type.impl.SiteType;
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
    public AccommodationSiteDTO add(AccommodationSiteDTO accommodationSiteDTO) {
        AccommodationSite accommodationSite = accommodationSiteAssembler.assembleToDomainObject(accommodationSiteDTO);

        accommodationSite.setAccommodationSiteType(em.find(AccommodationSiteType.class, accommodationSiteDTO.getAccommodationSiteTypeDTO().getId()));
        accommodationSite.setSiteType(em.find(SiteType.class, accommodationSiteDTO.getSiteTypeDTO().getId()));
        accommodationSite.setLocation(em.find(Location.class, accommodationSiteDTO.getLocationDTO().getId()));

        User sessionUser = em.find(User.class, accommodationSiteDTO.getUserSessionCookieDTO().getUserDTO().getUsername());
        accommodationSite.setCreateUser(sessionUser);
        accommodationSite.setUpdateUser(sessionUser);
        accommodationSite.setCreateDate(new Timestamp(new Date().getTime()));
        accommodationSite.setUpdateDate(new Timestamp(new Date().getTime()));

        em.persist(accommodationSite);

        return accommodationSiteAssembler.assembleToDTO(accommodationSite);
    }


    @Override
    public AccommodationSiteDTO update(AccommodationSiteDTO accommodationSiteDTO) {
        AccommodationSite accommodationSite = accommodationSiteAssembler.assembleToDomainObject(accommodationSiteDTO);

        User sessionUser = em.find(User.class, accommodationSiteDTO.getUserSessionCookieDTO().getUserDTO().getUsername());
        accommodationSite.setUpdateUser(sessionUser);
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
        logger.debug("cacheAccommodationSiteDomainObjects()");
        StopWatch watch = new StopWatch();
        watch.start("cacheAccommodationSiteDomainObjects");
        em.createNamedQuery(AccommodationSite.FIND_ALL_ACCOMMODATION_SITES_NAMED_QUERY).getResultList();
        watch.stop();
        if (logger.isDebugEnabled()) {
            logger.debug(watch.prettyPrint());
            logger.info("Total Time in Seconds AccommodationSiteDAOImpl.cacheAccommodationSiteDomainObjects() = " + watch.getTotalTimeSeconds());
        }
    }

}
