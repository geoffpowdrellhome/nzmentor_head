package com.travel.mentor.dao.impl;

import com.travel.mentor.dao.LocationDAO;
import com.travel.mentor.dao.assemble.LocationAssembler;
import com.travel.mentor.dao.base.AbstractMentorDAO;
import com.travel.mentor.dao.dto.impl.LocationDTO;
import com.travel.mentor.model.impl.Location;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

@Repository("locationDAO")
@Transactional
public class LocationDAOImpl extends AbstractMentorDAO implements LocationDAO {

    @Resource
    private LocationAssembler locationAssembler;

    @Override
    public List<LocationDTO> findAllLocations() {
        List<Location> locationList = em.createNamedQuery(Location.FIND_ALL_LOCATIONS_NAMED_QUERY).getResultList();
        return locationAssembler.assembleToLocationDTOList(locationList);
    }

    @Override
    public void addLocation(LocationDTO locationDTO) {
        Location location = locationAssembler.assembleToLocationDomainObject(locationDTO);
        em.persist(location);
    }

    @Override
    public void updateLocation(LocationDTO locationDTO) {
        Location location = locationAssembler.assembleToLocationDomainObject(locationDTO);
        em.merge(location);
    }

    @Override
    public void deleteLocation(LocationDTO locationDTO) {
        Location location = em.find(Location.class, locationDTO.getId());
        em.remove(location);
    }

    @Override
    public LocationDTO findLocation(LocationDTO locationDTO) {
        Location location = em.find(Location.class, locationDTO.getId());
        return locationAssembler.assembleToLocationDTO(location);
    }

    @SuppressWarnings("unchecked")
    @PostConstruct
    public void cacheLocationDomainObjects() {
        logger.debug("cacheLocationDomainObjects()");
        StopWatch watch = new StopWatch();
        watch.start("cacheLocationDomainObjects");
        em.createNamedQuery(Location.FIND_ALL_LOCATIONS_NAMED_QUERY).getResultList();
        watch.stop();
        if (logger.isDebugEnabled()) {
            logger.debug(watch.prettyPrint());
            logger.info("Total Time in Seconds LocationDAOImpl.cacheLocationDomainObjects() = " + watch.getTotalTimeSeconds());
        }
    }

}
