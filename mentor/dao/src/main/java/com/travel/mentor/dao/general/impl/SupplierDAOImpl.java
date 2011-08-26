package com.travel.mentor.dao.general.impl;

import com.travel.mentor.dao.assemble.general.SupplierAssembler;
import com.travel.mentor.dao.base.AbstractMentorDAO;
import com.travel.mentor.dao.dto.general.SupplierDTO;
import com.travel.mentor.dao.general.SupplierDAO;
import com.travel.mentor.domain.general.Supplier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
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
        Supplier supplier;
        if (supplierDTO.getId() == null || em.find(Supplier.class, supplierDTO.getId()) == null) {
            supplier = supplierAssembler.assembleToEntityInstance(supplierDTO);
            supplier.setCreateUser( secureUserAssembler.assembleToEntityInstance(supplierDTO.getLoggedInUser()) );
            supplier.setCreateDate(new Timestamp(new Date().getTime()));
        }
        else {
            supplier = em.find(Supplier.class, supplierDTO.getId());
            supplierAssembler.deepCopy(supplierDTO, supplier);
        }

        supplier.setUpdateUser( secureUserAssembler.assembleToEntityInstance(supplierDTO.getLoggedInUser()) );
        supplier.setUpdateDate(new Timestamp(new Date().getTime()));
        em.merge(supplier);

        return supplierAssembler.assembleToDTOInstance(supplier);
    }

    @Override
    public void delete(SupplierDTO supplierDTO) {
        Supplier supplier = em.find(Supplier.class, supplierDTO.getId());
        em.remove(supplier);
        em.getEntityManagerFactory().getCache().evict(supplier.getClass(), supplier.getId());
    }

    @Override
    public SupplierDTO find(Long id) {
        return supplierAssembler.assembleToDTOInstance(em.find(Supplier.class, id));
    }

    @PostConstruct
    protected void cacheDomainObjects() {
        List<String> namedQueries = new ArrayList<String>();
        namedQueries.add(Supplier.FIND_ALL_SUPPLIERS_NAMED_QUERY);
        super.cacheDomainObjects(namedQueries);
    }

}
