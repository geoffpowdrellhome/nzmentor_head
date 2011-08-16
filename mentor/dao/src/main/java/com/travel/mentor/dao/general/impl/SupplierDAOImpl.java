package com.travel.mentor.dao.general.impl;


import com.travel.mentor.dao.assemble.general.SupplierAssembler;
import com.travel.mentor.dao.base.AbstractMentorDAO;
import com.travel.mentor.dao.dto.general.SupplierDTO;
import com.travel.mentor.dao.general.SupplierDAO;
import com.travel.mentor.domain.general.Supplier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


import com.travel.mentor.dao.assemble.general.ItemAssembler;
import com.travel.mentor.dao.dto.general.ItemDTO;
import com.travel.mentor.dao.general.ItemDAO;
import com.travel.mentor.dao.base.AbstractMentorDAO;
import com.travel.mentor.domain.general.Item;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository("supplierDAO")
@Transactional
public class SupplierDAOImpl extends AbstractMentorDAO implements SupplierDAO {

    @Resource
    private SupplierAssembler supplierAssembler;

    @Override
    public List<SupplierDTO> findAll() {
        List<Supplier> supplierList = em.createNamedQuery(Supplier.FIND_ALL_SUPPLIERS_NAMED_QUERY).getResultList();
        return supplierAssembler.assembleToDTOList(supplierList);
    }

    @Override
    public SupplierDTO saveOrUpdate(SupplierDTO supplierDTO) {
        Supplier item = supplierAssembler.assembleToDomainObject(supplierDTO);

        if (supplierDTO.getId() == null || em.find(Item.class, supplierDTO.getId()) == null) {
            item.setCreateUser( secureUserAssembler.assembleToDomainObject(supplierDTO.getLoggedInUser()) );
            item.setCreateDate(new Timestamp(new Date().getTime()));
            item.setUpdateUser( secureUserAssembler.assembleToDomainObject(supplierDTO.getLoggedInUser()) );
            item.setUpdateDate(new Timestamp(new Date().getTime()));
            em.persist(item);
        }
        else {
            Supplier existingItem = em.find(item.getClass(), supplierDTO.getId());
            BeanUtils.copyProperties(item, existingItem);
            item.setUpdateUser( secureUserAssembler.assembleToDomainObject(supplierDTO.getLoggedInUser()) );
            item.setUpdateDate(new Timestamp(new Date().getTime()));
            em.merge(item);
        }

        return supplierAssembler.assembleToDTO(item);
    }

    @Override
    public void delete(SupplierDTO supplierDTO) {
        Supplier supplier = em.find(Supplier.class, supplierDTO.getId());
        em.remove(supplier);
        em.flush();
        em.getEntityManagerFactory().getCache().evict(supplier.getClass(), supplier.getId());
    }

    @Override
    public SupplierDTO find(Long id) {
        Supplier supplier = em.find(Supplier.class, id);
        return supplierAssembler.assembleToDTO(supplier);
    }

    @Override
    protected void cacheDomainObjects() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
