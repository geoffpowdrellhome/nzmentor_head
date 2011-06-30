package com.travel.mentor.service.type.impl;

import com.travel.mentor.dao.dto.base.ReferenceTypeDTO;
import com.travel.mentor.service.type.VehicleHireSiteTypeService;
import com.travel.mentor.service.type.base.AbstractReferenceTypeService;
import com.travel.mentor.type.impl.VehicleHireSiteType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("vehicleHireSiteTypeService")
@Transactional
public class VehicleHireSiteTypeServiceImpl extends AbstractReferenceTypeService implements VehicleHireSiteTypeService {

    @Override
    public List<ReferenceTypeDTO> findAll() {
        return referenceTypeDAO.findAll(VehicleHireSiteType.FIND_ALL_VEHICLE_SITE_TYPES_NAMED_QUERY);
    }

    @Override
    public ReferenceTypeDTO add(ReferenceTypeDTO referenceTypeDTO) {
        referenceTypeDTO.setEntityClass(VehicleHireSiteType.class);
        return referenceTypeDAO.add(referenceTypeDTO);
    }

}
