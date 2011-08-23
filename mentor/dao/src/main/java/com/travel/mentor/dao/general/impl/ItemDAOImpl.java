package com.travel.mentor.dao.general.impl;

import com.travel.mentor.dao.assemble.general.ItemAssembler;
import com.travel.mentor.dao.dto.general.ItemDTO;
import com.travel.mentor.dao.general.ItemDAO;
import com.travel.mentor.dao.base.AbstractMentorDAO;
import com.travel.mentor.domain.general.Item;
import com.travel.mentor.domain.general.Supplier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;
import org.springframework.beans.BeanUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository("itemDAO")
@Transactional
public class ItemDAOImpl extends AbstractMentorDAO implements ItemDAO {

    @Resource
    private ItemAssembler itemAssembler;

    @Override
    public List<ItemDTO> findAll() {
        List<Item> itemList = em.createNamedQuery(Item.FIND_ALL_ITEMS_NAMED_QUERY).getResultList();
        return itemAssembler.assembleToDTOList(itemList);
    }

    @Override
    public ItemDTO saveOrUpdate(ItemDTO itemDTO) {
        Item item = itemAssembler.assembleToDomainObject(itemDTO);

        if (itemDTO.getId() == null || em.find(Item.class, itemDTO.getId()) == null) {
            item.setCreateUser( secureUserAssembler.assembleToDomainObject(itemDTO.getLoggedInUser()) );
            item.setCreateDate(new Timestamp(new Date().getTime()));
            item.setUpdateUser( secureUserAssembler.assembleToDomainObject(itemDTO.getLoggedInUser()) );
            item.setUpdateDate(new Timestamp(new Date().getTime()));
            em.persist(item);
        }
        else {
            Item existingItem = em.find(item.getClass(), itemDTO.getId());
            BeanUtils.copyProperties(item, existingItem);
            item.setUpdateUser( secureUserAssembler.assembleToDomainObject(itemDTO.getLoggedInUser()) );
            item.setUpdateDate(new Timestamp(new Date().getTime()));
            em.merge(item);
        }

        return itemAssembler.assembleToDTO(item);
    }

    @Override
    public void delete(ItemDTO itemDTO) {
        Item item = em.find(Item.class, itemDTO.getId());
        em.remove(item);
        em.flush();
        em.getEntityManagerFactory().getCache().evict(item.getClass(), item.getId());
    }

    @Override
    public ItemDTO find(Long id) {
        Item item = em.find(Item.class, id);
        return itemAssembler.assembleToDTO(item);
    }

    @PostConstruct
    protected void cacheDomainObjects() {
        List<String> namedQueries = new ArrayList<String>();
        namedQueries.add(Supplier.FIND_ALL_SUPPLIERS_NAMED_QUERY);
        super.cacheDomainObjects(namedQueries);
    }

}
