package com.travel.mentor.dao;

import com.travel.mentor.dao.dto.impl.ItemDTO;

import java.util.List;

public interface ItemDAO {

    List<ItemDTO> findAllItems();

    void addItem(ItemDTO itemDTO);

    void updateItem(ItemDTO itemDTO);

    void deleteItem(ItemDTO itemDTO);

    ItemDTO findItem(Long id);

}
