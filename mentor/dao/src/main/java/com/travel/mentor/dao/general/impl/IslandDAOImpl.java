package com.travel.mentor.dao.general.impl;

import com.travel.mentor.dao.assemble.general.IslandAssembler;
import com.travel.mentor.dao.dto.general.IslandDTO;
import com.travel.mentor.dao.general.IslandDAO;
import com.travel.mentor.dao.base.AbstractMentorDAO;
import com.travel.mentor.domain.general.Island;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;
import java.util.List;

@Repository("islandDAO")
@Transactional
public class IslandDAOImpl extends AbstractMentorDAO implements IslandDAO {

    @Resource
    private IslandAssembler islandAssembler;

    @Override
    public List<IslandDTO> findAll() {
        List<Island> islandList = em.createNamedQuery(Island.FIND_ALL_ISLANDS_NAMED_QUERY).getResultList();
        return islandAssembler.assembleToDTOList(islandList);
    }

    @Override
    public IslandDTO find(Long id) {
        Island island = em.find(Island.class, id);
        return islandAssembler.assembleToDTO(island);
    }

    @Override
    protected void cacheDomainObjects() {
        logger.debug(this.getClass().getName() +".cacheDomainObjects()");
        StopWatch watch = new StopWatch();
        watch.start(this.getClass().getName() +".cacheDomainObjects()");
        em.createNamedQuery(Island.FIND_ALL_ISLANDS_NAMED_QUERY).getResultList();
        watch.stop();
        if (logger.isDebugEnabled()) {
            logger.debug(watch.prettyPrint());
            logger.info("Total Time in Seconds "+this.getClass().getName() +".cacheDomainObjects() = " + watch.getTotalTimeSeconds());
        }
    }

}
