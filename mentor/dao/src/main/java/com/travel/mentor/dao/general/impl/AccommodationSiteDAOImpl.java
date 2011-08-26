package com.travel.mentor.dao.general.impl;

import com.travel.mentor.dao.assemble.general.AccommodationSiteAssembler;
import com.travel.mentor.dao.base.AbstractMentorDAO;
import com.travel.mentor.dao.dto.general.AccommodationSiteDTO;
import com.travel.mentor.dao.general.AccommodationSiteDAO;
import com.travel.mentor.domain.general.AccommodationSite;
import com.travel.mentor.domain.general.Location;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
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
        AccommodationSite accommodationSite;
        if (accommodationSiteDTO.getId() == null || em.find(Location.class, accommodationSiteDTO.getId()) == null) {
            accommodationSite = accommodationSiteAssembler.assembleToEntityInstance(accommodationSiteDTO);
            accommodationSite.setCreateUser( secureUserAssembler.assembleToEntityInstance(accommodationSiteDTO.getLoggedInUser()) );
            accommodationSite.setCreateDate(new Timestamp(new Date().getTime()));
        }
        else {
            accommodationSite = em.find(AccommodationSite.class, accommodationSiteDTO.getId());
            accommodationSiteAssembler.deepCopy(accommodationSiteDTO, accommodationSite);
        }

        accommodationSite.setUpdateUser( secureUserAssembler.assembleToEntityInstance(accommodationSiteDTO.getLoggedInUser()) );
        accommodationSite.setUpdateDate(new Timestamp(new Date().getTime()));
        em.merge(accommodationSite);

        return accommodationSiteAssembler.assembleToDTOInstance(accommodationSite);
    }

    @Override
    public void delete(AccommodationSiteDTO accommodationSiteDTO) {
        AccommodationSite accommodationSite = em.find(AccommodationSite.class, accommodationSiteDTO.getId());
        em.remove(accommodationSite);
        em.getEntityManagerFactory().getCache().evict(accommodationSite.getClass(), accommodationSite.getId());
    }

    @Override
    public AccommodationSiteDTO find(Long id) {
        return accommodationSiteAssembler.assembleToDTOInstance(em.find(AccommodationSite.class, id));
    }

    @PostConstruct
    protected void cacheDomainObjects() {
        List<String> namedQueries = new ArrayList<String>();
        namedQueries.add(AccommodationSite.FIND_ALL_ACCOMMODATION_SITES_NAMED_QUERY);
        super.cacheDomainObjects(namedQueries);
    }

}
