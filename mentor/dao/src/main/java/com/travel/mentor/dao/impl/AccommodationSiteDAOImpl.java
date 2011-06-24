package com.travel.mentor.dao.impl;

import com.travel.mentor.dao.AccommodationSiteDAO;
import com.travel.mentor.dao.assemble.AccommodationSiteAssembler;
import com.travel.mentor.dao.base.AbstractMentorDAO;
import com.travel.mentor.dao.dto.impl.AccommodationSiteDTO;
import com.travel.mentor.model.impl.AccommodationSite;
import com.travel.mentor.model.impl.Item;
import com.travel.mentor.model.impl.Location;
import com.travel.mentor.type.impl.AccommodationSiteType;
import com.travel.mentor.type.impl.SiteType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    public List<AccommodationSiteDTO> findAllAccommodationSites() {
        List<AccommodationSite> accommodationSiteList = em.createNamedQuery(AccommodationSite.FIND_ALL_ACCOMMODATION_SITES_NAMED_QUERY).getResultList();
        return accommodationSiteAssembler.assembleToAccommodationSiteDTOList(accommodationSiteList);
    }

    @Override
    public void addAccommodationSite(AccommodationSiteDTO accommodationSiteDTO) {
        AccommodationSite accommodationSite = accommodationSiteAssembler.assembleToAccommodationSiteDomainObject(accommodationSiteDTO);

        accommodationSite.setAccommodationSiteType( em.find(AccommodationSiteType.class, accommodationSiteDTO.getAccommodationSiteTypeDTO().getId()) );
        accommodationSite.setSiteType( em.find(SiteType.class, accommodationSiteDTO.getSiteTypeDTO().getId()) );
        accommodationSite.setLocation( em.find(Location.class, accommodationSiteDTO.getLocationDTO().getId()) );


        //accommodationSiteDTO.getUserSessionCookie().getUserId()

        //@TODO REMOVE THIS hardcoding once developed the User functionality
        accommodationSite.setCreateDate(new Timestamp(new Date().getTime()));
        accommodationSite.setCreateUser("boo");
        accommodationSite.setUpdateDate(new Timestamp(new Date().getTime()));
        accommodationSite.setUpdateUser("boo2");

        em.persist(accommodationSite);
    }

    @Override
    public void updateAccommodationSite(AccommodationSiteDTO accommodationSiteDTO) {
        AccommodationSite accommodationSite = accommodationSiteAssembler.assembleToAccommodationSiteDomainObject(accommodationSiteDTO);
        em.merge(accommodationSite);
    }

    @Override
    public void deleteAccommodationSite(AccommodationSiteDTO accommodationSiteDTO) {
        AccommodationSite accommodationSite = em.find(AccommodationSite.class, accommodationSiteDTO.getId());
        em.remove(accommodationSite);
    }

    @Override
    public AccommodationSiteDTO findAccommodationSite(Long id) {
        AccommodationSite accommodationSite = em.find(AccommodationSite.class, id);
        return accommodationSiteAssembler.assembleToAccommodationSiteDTO(accommodationSite);
    }

    //    @SuppressWarnings("unchecked")
//    @PostConstruct
//    public void cacheAccommodationSiteDomainObjects() {
//        logger.debug("cacheAccommodationSiteDomainObjects()");
//        StopWatch watch = new StopWatch();
//        watch.start("cacheAccommodationSiteDomainObjects");
//        em.createNamedQuery(AccommodationSite.FIND_ALL_ACCOMMODATION_SITES_NAMED_QUERY).getResultList();
//        watch.stop();
//        if (logger.isDebugEnabled()) {
//            logger.debug(watch.prettyPrint());
//            logger.info("Total Time in Seconds AccommodationSiteDAOImpl.cacheAccommodationSiteDomainObjects() = " + watch.getTotalTimeSeconds());
//        }
//    }

}
