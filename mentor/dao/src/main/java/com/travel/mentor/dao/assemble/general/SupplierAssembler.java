package com.travel.mentor.dao.assemble.general;

import com.travel.mentor.dao.dto.general.SupplierDTO;
import com.travel.mentor.domain.general.Supplier;

import java.util.List;

public interface SupplierAssembler {

    List<SupplierDTO> assembleToDTOList(List<Supplier> supplierList);

    Supplier assembleToDomainObject(SupplierDTO supplierDTO);

    SupplierDTO assembleToDTO(Supplier supplier);

}
