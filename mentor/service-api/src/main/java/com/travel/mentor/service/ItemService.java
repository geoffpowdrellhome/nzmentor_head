package com.travel.mentor.service;

import com.travel.mentor.dao.dto.impl.ItemDTO;

import java.util.List;

public interface ItemService {

    List<ItemDTO> findAll();

    ItemDTO add(ItemDTO itemDTO);

    ItemDTO update(ItemDTO itemDTO);

    void delete(ItemDTO itemDTO);

    ItemDTO find(Long id);

}
