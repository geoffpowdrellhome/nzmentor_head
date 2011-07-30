package com.travel.mentor.dao.assemble.security.impl;

import com.travel.mentor.dao.assemble.base.impl.BaseAssemblerImpl;
import com.travel.mentor.dao.assemble.security.SecurityGroupAssembler;
import com.travel.mentor.dao.dto.security.SecurityGroupDTO;
import com.travel.mentor.domain.security.SecurityGroup;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SecurityGroupAssemblerImpl extends BaseAssemblerImpl implements SecurityGroupAssembler {

    @Override
    public List<SecurityGroupDTO> assembleToDTOList(List<SecurityGroup> securityGroupList) {
        List<SecurityGroupDTO> securityGroupDTOList = new ArrayList<SecurityGroupDTO>();
        for (SecurityGroup securityGroup : securityGroupList) {
            securityGroupDTOList.add( assembleToDTO(securityGroup) );
        }
        return securityGroupDTOList;
    }

    @Override
    public SecurityGroup assembleToDomainObject(SecurityGroupDTO securityGroupDTO) {
        return (SecurityGroup) shallowCopy(securityGroupDTO, SecurityGroup.class);
    }

    @Override
    public SecurityGroupDTO assembleToDTO(SecurityGroup securityGroup) {
        return (SecurityGroupDTO) shallowCopy(securityGroup, SecurityGroupDTO.class);
    }

}
