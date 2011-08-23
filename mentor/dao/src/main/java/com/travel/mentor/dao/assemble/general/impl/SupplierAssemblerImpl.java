package com.travel.mentor.dao.assemble.general.impl;

import com.travel.mentor.core.util.AssembleUtil;
import com.travel.mentor.dao.assemble.base.AbstractAssembler;
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

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class SupplierAssemblerImpl extends AbstractAssembler implements SupplierAssembler {

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
        Supplier supplier = (Supplier) assembleUtil.shallowCopy(supplierDTO, Supplier.class);

        supplier.setCreateUser((SecureUser) assembleUtil.shallowCopy(supplierDTO.getCreateUserDTO(), SecureUser.class));
        supplier.setUpdateUser((SecureUser) assembleUtil.shallowCopy(supplierDTO.getUpdateUserDTO(), SecureUser.class));

        supplier.setSupplierType((SupplierType) assembleUtil.shallowCopy(supplierDTO.getSupplierTypeDTO(), SupplierType.class));
        supplier.setLocation((Location) assembleUtil.shallowCopy(supplierDTO.getLocationDTO(), Location.class));

        return supplier;
    }

    @Override
    public SupplierDTO assembleToDTO(Supplier supplier) {
        SupplierDTO itemDTO = (SupplierDTO) assembleUtil.shallowCopy(supplier, SupplierDTO.class);

        itemDTO.setCreateUserDTO((SecureUserDTO) assembleUtil.shallowCopy(supplier.getCreateUser(), SecureUserDTO.class));
        itemDTO.setUpdateUserDTO((SecureUserDTO) assembleUtil.shallowCopy(supplier.getUpdateUser(), SecureUserDTO.class));

        itemDTO.setSupplierTypeDTO((ReferenceTypeDTO) assembleUtil.shallowCopy(supplier.getSupplierType(), ReferenceTypeDTO.class));
        itemDTO.setLocationDTO((LocationDTO) assembleUtil.shallowCopy(supplier.getLocation(), LocationDTO.class));

        return itemDTO;
    }

}
