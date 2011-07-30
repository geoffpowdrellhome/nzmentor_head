package com.travel.mentor.dao.general.impl;

import com.travel.mentor.dao.assemble.general.LocationAssembler;
import com.travel.mentor.dao.dto.general.LocationDTO;
import com.travel.mentor.dao.general.LocationDAO;
import com.travel.mentor.dao.base.AbstractMentorDAO;
import com.travel.mentor.domain.general.Location;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository("locationDAO")
@Transactional
public class LocationDAOImpl extends AbstractMentorDAO implements LocationDAO {

    @Resource
    private LocationAssembler locationAssembler;

    @Override
    public List<LocationDTO> findAll() {
        List<Location> locationList = em.createNamedQuery(Location.FIND_ALL_LOCATIONS_NAMED_QUERY).getResultList();
        return locationAssembler.assembleToDTOList(locationList);
    }

    @Override
    public LocationDTO saveOrUpdate(LocationDTO locationDTO) {
        Location location = locationAssembler.assembleToDomainObject(locationDTO);

        if (locationDTO.getId() == null || em.find(Location.class, locationDTO.getId()) == null) {
            location.setCreateUser( secureUserAssembler.assembleToDomainObject(locationDTO.getLoggedInUser()) );
            location.setCreateDate(new Timestamp(new Date().getTime()));
        }

        location.setUpdateUser( secureUserAssembler.assembleToDomainObject(locationDTO.getLoggedInUser()) );
        location.setUpdateDate(new Timestamp(new Date().getTime()));

        em.merge(location);

        return locationAssembler.assembleToDTO(location);
    }

    @Override
    public void delete(LocationDTO locationDTO) {
        Location location = em.find(Location.class, locationDTO.getId());
        em.remove(location);
    }

    @Override
    public LocationDTO find(Long id) {
        Location location = em.find(Location.class, id);
        return locationAssembler.assembleToDTO(location);
    }

    @Override
    protected void cacheDomainObjects() {
        logger.debug(this.getClass().getName() +".cacheDomainObjects()");
        StopWatch watch = new StopWatch();
        watch.start(this.getClass().getName() +".cacheDomainObjects()");
        em.createNamedQuery(Location.FIND_ALL_LOCATIONS_NAMED_QUERY).getResultList();
        watch.stop();
        if (logger.isDebugEnabled()) {
            logger.debug(watch.prettyPrint());
            logger.info("Total Time in Seconds "+this.getClass().getName() +".cacheDomainObjects() = " + watch.getTotalTimeSeconds());
        }
    }

}
