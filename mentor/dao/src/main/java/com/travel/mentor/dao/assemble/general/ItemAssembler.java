package com.travel.mentor.dao.assemble.general;

import com.travel.mentor.dao.dto.general.ItemDTO;
import com.travel.mentor.domain.general.Item;

import java.util.List;

public interface ItemAssembler {

    List<ItemDTO> assembleToDTOList(List<Item> itemList);

    Item assembleToEntityInstance(ItemDTO itemDTO);

    ItemDTO assembleToDTOInstance(Item item);

    Item deepCopy(ItemDTO itemDTO, Item item);

}
