package com.travel.mentor.dao.impl;

import com.travel.mentor.dao.ItemDAO;
import com.travel.mentor.dao.assemble.ItemAssembler;
import com.travel.mentor.dao.base.AbstractMentorDAO;
import com.travel.mentor.dao.dto.impl.ItemDTO;
import com.travel.mentor.model.impl.Item;
import com.travel.mentor.model.impl.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.sql.Timestamp;
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
    public ItemDTO add(ItemDTO itemDTO) {
        Item item = itemAssembler.assembleToDomainObject(itemDTO);

        User sessionUser = em.find(User.class, itemDTO.getUserSessionCookieDTO().getUserDTO().getUsername());
        item.setCreateUser(sessionUser);
        item.setUpdateUser(sessionUser);
        item.setCreateDate(new Timestamp(new Date().getTime()));
        item.setUpdateDate(new Timestamp(new Date().getTime()));

        em.persist(item);

        return itemAssembler.assembleToDTO(item);
    }

    @Override
    public ItemDTO update(ItemDTO itemDTO) {
        Item item = itemAssembler.assembleToDomainObject(itemDTO);

        User sessionUser = em.find(User.class, itemDTO.getUserSessionCookieDTO().getUserDTO().getUsername());
        item.setUpdateUser(sessionUser);
        item.setUpdateDate(new Timestamp(new Date().getTime()));

        em.merge(item);

        return itemAssembler.assembleToDTO(item);
    }

    @Override
    public void delete(ItemDTO itemDTO) {
        Item item = em.find(Item.class, itemDTO.getId());
        em.remove(item);
    }

    @Override
    public ItemDTO find(Long id) {
        Item item = em.find(Item.class, id);
        return itemAssembler.assembleToDTO(item);
    }

    @Override
    protected void cacheDomainObjects() {
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
