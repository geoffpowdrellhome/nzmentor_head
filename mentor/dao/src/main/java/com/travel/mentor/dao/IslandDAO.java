package com.travel.mentor.dao;

import com.travel.mentor.dao.dto.IslandDTO;

import java.util.List;

public interface IslandDAO {

    List<IslandDTO> findAllIslands();

}
