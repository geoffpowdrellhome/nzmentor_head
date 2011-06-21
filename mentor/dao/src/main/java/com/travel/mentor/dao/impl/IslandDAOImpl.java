package com.travel.mentor.dao.impl;

import com.travel.mentor.dao.IslandDAO;
import com.travel.mentor.dao.assemble.IslandAssembler;
import com.travel.mentor.dao.base.AbstractMentorDAO;
import com.travel.mentor.dao.dto.impl.IslandDTO;
import com.travel.mentor.model.impl.Island;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

@Repository("islandDAO")
@Transactional
public class IslandDAOImpl extends AbstractMentorDAO implements IslandDAO {

    @Resource
    private IslandAssembler islandAssembler;

    @Override
    public List<IslandDTO> findAllIslands() {
        List<Island> islandList = em.createNamedQuery(Island.FIND_ALL_ISLANDS_NAMED_QUERY).getResultList();
        return islandAssembler.assembleToIslandDTOList(islandList);
    }

    @SuppressWarnings("unchecked")
    @PostConstruct
    public void cacheIslandDomainObjects() {
        logger.debug("cacheIslandDomainObjects()");
        StopWatch watch = new StopWatch();
        watch.start("cacheIslandDomainObjects");
        em.createNamedQuery(Island.FIND_ALL_ISLANDS_NAMED_QUERY).getResultList();
        watch.stop();
        if (logger.isDebugEnabled()) {
            logger.debug(watch.prettyPrint());
            logger.info("Total Time in Seconds IslandDAOImpl.cacheIslandDomainObjects() = " + watch.getTotalTimeSeconds());
        }
    }

}
