package com.travel.mentor.dao.general.impl;

import com.travel.mentor.dao.assemble.general.IslandAssembler;
import com.travel.mentor.dao.base.AbstractMentorDAO;
import com.travel.mentor.dao.dto.general.IslandDTO;
import com.travel.mentor.dao.general.IslandDAO;
import com.travel.mentor.domain.general.Island;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
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
        return islandAssembler.assembleToDTOInstance( em.find(Island.class, id) );
    }

    @PostConstruct
    protected void cacheDomainObjects() {
        List<String> namedQueries = new ArrayList<String>();
        namedQueries.add(Island.FIND_ALL_ISLANDS_NAMED_QUERY);
        super.cacheDomainObjects(namedQueries);
    }

}
