package com.travel.mentor.dao;

import com.travel.mentor.dao.dto.ItemDTO;

import java.util.List;

public interface ItemDAO {

    List<ItemDTO> findAllItems();

    void addItem(ItemDTO itemDTO);

    void updateItem(ItemDTO itemDTO);

    void deleteItem(ItemDTO itemDTO);

    ItemDTO findItem(ItemDTO itemDTO);

}
