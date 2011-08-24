package com.travel.mentor.dao.general.impl;

import com.travel.mentor.dao.assemble.general.LocationAssembler;
import com.travel.mentor.dao.base.AbstractMentorDAO;
import com.travel.mentor.dao.dto.general.LocationDTO;
import com.travel.mentor.dao.general.LocationDAO;
import com.travel.mentor.domain.general.Location;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
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
            location.setCreateUser( secureUserAssembler.assembleToEntityInstance(locationDTO.getLoggedInUser()) );
            location.setCreateDate(new Timestamp(new Date().getTime()));
            location.setUpdateUser( secureUserAssembler.assembleToEntityInstance(locationDTO.getLoggedInUser()) );
            location.setUpdateDate(new Timestamp(new Date().getTime()));
            em.persist(location);
        }
        else {
            Location existingLocation = em.find(location.getClass(), locationDTO.getId());
            BeanUtils.copyProperties(location, existingLocation);
            location.setUpdateUser( secureUserAssembler.assembleToEntityInstance(locationDTO.getLoggedInUser()) );
            location.setUpdateDate(new Timestamp(new Date().getTime()));
            em.merge(location);
        }

        return locationAssembler.assembleToDTO(location);
    }

    @Override
    public void delete(LocationDTO locationDTO) {
        Location location = em.find(Location.class, locationDTO.getId());
        em.remove(location);
        em.flush();
        em.getEntityManagerFactory().getCache().evict(location.getClass(), location.getId());
    }

    @Override
    public LocationDTO find(Long id) {
        Location location = em.find(Location.class, id);
        return locationAssembler.assembleToDTO(location);
    }

    @PostConstruct
    protected void cacheDomainObjects() {
        List<String> namedQueries = new ArrayList<String>();
        namedQueries.add(Location.FIND_ALL_LOCATIONS_NAMED_QUERY);
        super.cacheDomainObjects(namedQueries);
    }

}
