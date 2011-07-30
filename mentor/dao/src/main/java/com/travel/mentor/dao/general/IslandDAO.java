package com.travel.mentor.dao.general;

import com.travel.mentor.dao.dto.general.IslandDTO;

import java.util.List;

public interface IslandDAO {

    List<IslandDTO> findAll();

    IslandDTO find(Long id);

}
