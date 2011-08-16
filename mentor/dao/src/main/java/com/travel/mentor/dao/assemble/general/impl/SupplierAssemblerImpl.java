package com.travel.mentor.dao.assemble.general.impl;

import com.travel.mentor.dao.assemble.base.impl.BaseAssemblerImpl;
import com.travel.mentor.dao.assemble.general.SupplierAssembler;
import com.travel.mentor.dao.dto.general.LocationDTO;
import com.travel.mentor.dao.dto.general.SupplierDTO;
import com.travel.mentor.dao.dto.reference.ReferenceTypeDTO;
import com.travel.mentor.dao.dto.security.SecureUserDTO;
import com.travel.mentor.domain.general.Location;
import com.travel.mentor.domain.general.Supplier;
import com.travel.mentor.domain.reference.SupplierType;
import com.travel.mentor.domain.security.SecureUser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SupplierAssemblerImpl extends BaseAssemblerImpl implements SupplierAssembler {

    @Override
    public List<SupplierDTO> assembleToDTOList(List<Supplier> supplierList) {
        List<SupplierDTO> supplierDTOList = new ArrayList<SupplierDTO>();
        for (Supplier supplier : supplierList) {
            supplierDTOList.add( assembleToDTO(supplier) );
        }
        return supplierDTOList;
    }

    @Override
    public Supplier assembleToDomainObject(SupplierDTO supplierDTO) {
        Supplier supplier = (Supplier) shallowCopy(supplierDTO, Supplier.class);

        supplier.setCreateUser((SecureUser) shallowCopy(supplierDTO.getCreateUserDTO(), SecureUser.class));
        supplier.setUpdateUser((SecureUser) shallowCopy(supplierDTO.getUpdateUserDTO(), SecureUser.class));

        supplier.setSupplierType((SupplierType) shallowCopy(supplierDTO.getSupplierTypeDTO(), SupplierType.class));
        supplier.setLocation((Location) shallowCopy(supplierDTO.getLocationDTO(), Location.class));

        return supplier;
    }

    @Override
    public SupplierDTO assembleToDTO(Supplier supplier) {
        SupplierDTO itemDTO = (SupplierDTO) shallowCopy(supplier, SupplierDTO.class);

        itemDTO.setCreateUserDTO((SecureUserDTO) shallowCopy(supplier.getCreateUser(), SecureUserDTO.class));
        itemDTO.setUpdateUserDTO((SecureUserDTO) shallowCopy(supplier.getUpdateUser(), SecureUserDTO.class));

        itemDTO.setSupplierTypeDTO((ReferenceTypeDTO) shallowCopy(supplier.getSupplierType(), ReferenceTypeDTO.class));
        itemDTO.setLocationDTO((LocationDTO) shallowCopy(supplier.getLocation(), LocationDTO.class));

        return itemDTO;
    }
}
