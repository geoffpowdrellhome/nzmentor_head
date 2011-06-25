package com.travel.mentor.dao.assemble;

import com.travel.mentor.dao.dto.impl.ItemDTO;
import com.travel.mentor.model.impl.Item;

import java.util.List;

public interface ItemAssembler {

    List<ItemDTO> assembleToDTOList(List<Item> itemList);

    Item assembleToDomainObject(ItemDTO itemDTO);

    ItemDTO assembleToDTO(Item item);

}
