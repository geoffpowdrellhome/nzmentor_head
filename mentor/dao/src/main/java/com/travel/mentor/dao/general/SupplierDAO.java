package com.travel.mentor.dao.general;

import com.travel.mentor.dao.dto.general.SupplierDTO;

import java.util.List;

public interface SupplierDAO {

    List<SupplierDTO> findAll();

    SupplierDTO saveOrUpdate(SupplierDTO supplierDTO);

    void delete(SupplierDTO supplierDTO);

    SupplierDTO find(Long id);

}
