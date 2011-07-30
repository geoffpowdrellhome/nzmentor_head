package com.travel.mentor.dao.assemble.security.impl;

import com.travel.mentor.dao.assemble.base.impl.BaseAssemblerImpl;
import com.travel.mentor.dao.assemble.security.SecurityGroupRightAssembler;
import com.travel.mentor.dao.dto.security.SecurityGroupRightDTO;
import com.travel.mentor.domain.security.SecurityGroupRight;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SecurityGroupRightAssemblerImpl extends BaseAssemblerImpl implements SecurityGroupRightAssembler {

    @Override
    public List<SecurityGroupRightDTO> assembleToDTOList(List<SecurityGroupRight> securityGroupRightList) {
        List<SecurityGroupRightDTO> securityGroupRightDTOList = new ArrayList<SecurityGroupRightDTO>();
        for (SecurityGroupRight securityGroupRight : securityGroupRightList) {
            securityGroupRightDTOList.add( assembleToDTO(securityGroupRight) );
        }
        return securityGroupRightDTOList;
    }

    @Override
    public SecurityGroupRight assembleToDomainObject(SecurityGroupRightDTO securityGroupRightDTO) {
        return (SecurityGroupRight) shallowCopy(securityGroupRightDTO, SecurityGroupRight.class);
    }

    @Override
    public SecurityGroupRightDTO assembleToDTO(SecurityGroupRight securityGroupRight) {
        return (SecurityGroupRightDTO) shallowCopy(securityGroupRight, SecurityGroupRightDTO.class);
    }

}
