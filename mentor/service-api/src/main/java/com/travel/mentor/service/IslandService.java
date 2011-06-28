package com.travel.mentor.service;

import com.travel.mentor.dao.dto.impl.IslandDTO;

import java.util.List;

public interface IslandService {

    List<IslandDTO> findAll();

    IslandDTO find(Long id);

}
