package com.travel.mentor.dao;

import com.travel.mentor.dao.dto.impl.ItemDTO;

import java.util.List;

public interface ItemDAO {

    List<ItemDTO> findAll();

    ItemDTO saveOrUpdate(ItemDTO itemDTO);

    void delete(ItemDTO itemDTO);

    ItemDTO find(Long id);

}
