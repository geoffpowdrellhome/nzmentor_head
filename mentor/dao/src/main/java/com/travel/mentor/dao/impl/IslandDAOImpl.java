package com.travel.mentor.dao.impl;

import com.travel.mentor.dao.IslandDAO;
import com.travel.mentor.dao.assemble.IslandAssembler;
import com.travel.mentor.dao.base.AbstractMentorDAO;
import com.travel.mentor.dao.dto.IslandDTO;
import com.travel.mentor.model.impl.Island;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

}
