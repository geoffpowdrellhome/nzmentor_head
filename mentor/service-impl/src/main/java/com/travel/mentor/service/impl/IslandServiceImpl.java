package com.travel.mentor.service.impl;

import com.travel.mentor.dao.IslandDAO;
import com.travel.mentor.dao.dto.impl.IslandDTO;
import com.travel.mentor.service.IslandService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("islandService")
@Transactional
public class IslandServiceImpl implements IslandService {

    @Resource(name="islandDAO")
    private IslandDAO islandDAO;

    @Override
    public List<IslandDTO> findAll() {
        return islandDAO.findAll();
    }

    @Override
    public IslandDTO find(Long id) {
        return islandDAO.find(id);
    }

}
