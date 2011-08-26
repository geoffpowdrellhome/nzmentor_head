package com.travel.mentor.dao.assemble.general;

import com.travel.mentor.dao.dto.general.SupplierDTO;
import com.travel.mentor.domain.general.Supplier;

import java.util.List;

public interface SupplierAssembler {

    List<SupplierDTO> assembleToDTOList(List<Supplier> supplierList);

    Supplier assembleToEntityInstance(SupplierDTO supplierDTO);

    SupplierDTO assembleToDTOInstance(Supplier supplier);

    Supplier deepCopy(SupplierDTO supplierDTO, Supplier supplier);

}
