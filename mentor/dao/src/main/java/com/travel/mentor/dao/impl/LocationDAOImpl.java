package com.travel.mentor.dao.impl;

import com.travel.mentor.dao.LocationDAO;
import com.travel.mentor.dao.assemble.LocationAssembler;
import com.travel.mentor.dao.base.AbstractMentorDAO;
import com.travel.mentor.dao.dto.impl.LocationDTO;
import com.travel.mentor.model.impl.Location;
import com.travel.mentor.model.impl.User;
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
    public LocationDTO add(LocationDTO locationDTO) {
        Location location = locationAssembler.assembleToDomainObject(locationDTO);

        User sessionUser = em.find(User.class, locationDTO.getUserSessionCookieDTO().getUserDTO().getUsername());
        location.setCreateUser(sessionUser);
        location.setUpdateUser(sessionUser);
        location.setCreateDate(new Timestamp(new Date().getTime()));
        location.setUpdateDate(new Timestamp(new Date().getTime()));

        em.persist(location);

        return locationAssembler.assembleToDTO(location);
    }

    @Override
    public LocationDTO update(LocationDTO locationDTO) {
        Location location = locationAssembler.assembleToDomainObject(locationDTO);

        User sessionUser = em.find(User.class, locationDTO.getUserSessionCookieDTO().getUserDTO().getUsername());
        location.setUpdateUser(sessionUser);
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
