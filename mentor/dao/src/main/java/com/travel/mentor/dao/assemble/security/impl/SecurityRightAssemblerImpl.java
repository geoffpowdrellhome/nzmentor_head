package com.travel.mentor.dao.assemble.security.impl;

import com.travel.mentor.dao.assemble.base.impl.BaseAssemblerImpl;
import com.travel.mentor.dao.assemble.security.SecurityRightAssembler;
import com.travel.mentor.dao.dto.security.SecurityRightDTO;
import com.travel.mentor.domain.security.SecurityRight;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SecurityRightAssemblerImpl extends BaseAssemblerImpl implements SecurityRightAssembler {

    @Override
    public List<SecurityRightDTO> assembleToDTOList(List<SecurityRight> securityRightList) {
        List<SecurityRightDTO> securityRightDTOList = new ArrayList<SecurityRightDTO>();
        for (SecurityRight securityRight : securityRightList) {
            securityRightDTOList.add( assembleToDTO(securityRight) );
        }
        return securityRightDTOList;
    }

    @Override
    public SecurityRight assembleToDomainObject(SecurityRightDTO securityRightDTO) {
        return (SecurityRight) shallowCopy(securityRightDTO, SecurityRight.class);
    }

    @Override
    public SecurityRightDTO assembleToDTO(SecurityRight securityRight) {
        return (SecurityRightDTO) shallowCopy(securityRight, SecurityRightDTO.class);
    }

}
