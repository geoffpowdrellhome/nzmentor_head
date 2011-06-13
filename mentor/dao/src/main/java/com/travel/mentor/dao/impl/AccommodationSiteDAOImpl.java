package com.travel.mentor.dao.impl;

import com.travel.mentor.dao.AccommodationSiteDAO;
import com.travel.mentor.dao.assemble.AccommodationSiteAssembler;
import com.travel.mentor.dao.base.AbstractMentorDAO;
import com.travel.mentor.dao.dto.AccommodationSiteDTO;
import com.travel.mentor.model.impl.AccommodationSite;
import com.travel.mentor.model.impl.Location;
import com.travel.mentor.type.impl.AccommodationSiteType;
import com.travel.mentor.type.impl.RoomConfigurationType;
import com.travel.mentor.type.impl.RoomType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
        accommodationSite.setRoomConfigurationType( em.find(RoomConfigurationType.class, accommodationSiteDTO.getRoomConfigurationTypeDTO().getId()) );
        accommodationSite.setRoomType( em.find(RoomType.class, accommodationSiteDTO.getRoomTypeDTO().getId()) );
        accommodationSite.setLocation( em.find(Location.class, accommodationSiteDTO.getLocationDTO().getId()) );
        accommodationSite.setId(5L);

        em.persist(accommodationSite);
    }

    @Override
    public void updateAccommodationSite(AccommodationSiteDTO accommodationSiteDTO) {
        AccommodationSite accommodationSite = accommodationSiteAssembler.assembleToAccommodationSiteDomainObject(accommodationSiteDTO);
        em.merge(accommodationSite);
    }

    @Override
    public void deleteAccommodationSite(AccommodationSiteDTO accommodationSiteDTO) {
        //AccommodationSite accommodationSite = accommodationSiteAssembler.instantiateReferenceTypeDomainObject(accommodationSiteDTO);
        AccommodationSite accommodationSite = em.find(AccommodationSite.class, accommodationSiteDTO.getId());
        em.remove(accommodationSite);
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
