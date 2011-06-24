package com.travel.mentor.dao.impl;

import com.travel.mentor.dao.ItemDAO;
import com.travel.mentor.dao.assemble.ItemAssembler;
import com.travel.mentor.dao.base.AbstractMentorDAO;
import com.travel.mentor.dao.dto.impl.ItemDTO;
import com.travel.mentor.model.impl.Item;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

@Repository("itemDAO")
@Transactional
public class ItemDAOImpl extends AbstractMentorDAO implements ItemDAO {

    @Resource
    private ItemAssembler itemAssembler;

    @Override
    public List<ItemDTO> findAllItems() {
        List<Item> itemList = em.createNamedQuery(Item.FIND_ALL_ITEMS_NAMED_QUERY).getResultList();
        return itemAssembler.assembleToItemDTOList(itemList);
    }

    @Override
    public void addItem(ItemDTO itemDTO) {
        Item item = itemAssembler.assembleToItemDomainObject(itemDTO);
        em.persist(item);
    }

    @Override
    public void updateItem(ItemDTO itemDTO) {
        Item item = itemAssembler.assembleToItemDomainObject(itemDTO);
        em.merge(item);
    }

    @Override
    public void deleteItem(ItemDTO itemDTO) {
        Item item = em.find(Item.class, itemDTO.getId());
        em.remove(item);
    }

    @Override
    public ItemDTO findItem(Long id) {
        Item item = em.find(Item.class, id);
        return itemAssembler.assembleToItemDTO(item);
    }

    @SuppressWarnings("unchecked")
    @PostConstruct
    public void cacheItemDomainObjects() {
        logger.debug("cacheItemDomainObjects()");
        StopWatch watch = new StopWatch();
        watch.start("cacheItemDomainObjects");
        em.createNamedQuery(Item.FIND_ALL_ITEMS_NAMED_QUERY).getResultList();
        watch.stop();
        if (logger.isDebugEnabled()) {
            logger.debug(watch.prettyPrint());
            logger.info("Total Time in Seconds ItemDAOImpl.cacheIslandDomainObjects() = " + watch.getTotalTimeSeconds());
        }
    }


}
