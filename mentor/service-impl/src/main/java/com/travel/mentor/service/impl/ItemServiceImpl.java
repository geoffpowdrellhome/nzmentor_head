package com.travel.mentor.service.impl;

import com.travel.mentor.dao.ItemDAO;
import com.travel.mentor.dao.dto.impl.ItemDTO;
import com.travel.mentor.service.ItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("itemService")
@Transactional
public class ItemServiceImpl implements ItemService {

    @Resource(name="itemDAO")
    private ItemDAO itemDAO;

    @Override
    public List<ItemDTO> findAll() {
        return itemDAO.findAll();
    }

    @Override
    public ItemDTO add(ItemDTO itemDTO) {
        return itemDAO.add(itemDTO);
    }

    @Override
    public ItemDTO update(ItemDTO itemDTO) {
        return itemDAO.update(itemDTO);
    }

    @Override
    public void delete(ItemDTO itemDTO) {
        itemDAO.delete(itemDTO);
    }

    @Override
    public ItemDTO find(Long id) {
        return itemDAO.find(id);
    }

}
