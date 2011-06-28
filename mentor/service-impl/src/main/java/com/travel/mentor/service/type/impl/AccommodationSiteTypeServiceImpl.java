package com.travel.mentor.service.type.impl;

import com.travel.mentor.dao.dto.base.ReferenceTypeDTO;
import com.travel.mentor.service.type.AccommodationSiteTypeService;
import com.travel.mentor.service.type.base.AbstractReferenceTypeService;
import com.travel.mentor.service.type.base.ReferenceTypeService;
import com.travel.mentor.type.impl.AccommodationSiteType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("accommodationSiteTypeService")
@Transactional
public class AccommodationSiteTypeServiceImpl extends AbstractReferenceTypeService implements AccommodationSiteTypeService {

    @Override
    public List<ReferenceTypeDTO> findAll() {
        return referenceTypeDAO.findAll(AccommodationSiteType.FIND_ALL_ACCOMMODATION_SITE_TYPES_NAMED_QUERY);
    }

}
