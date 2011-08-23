package com.travel.mentor.dao.assemble.security.impl;

import com.travel.mentor.dao.assemble.base.AbstractAssembler;
import com.travel.mentor.dao.assemble.security.SecurityGroupAssembler;
import com.travel.mentor.dao.dto.security.SecureUserDTO;
import com.travel.mentor.dao.dto.security.SecurityGroupDTO;
import com.travel.mentor.domain.security.SecureUser;
import com.travel.mentor.domain.security.SecurityGroup;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SecurityGroupAssemblerImpl extends AbstractAssembler implements SecurityGroupAssembler {

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
        SecurityGroup securityGroup = (SecurityGroup) assembleUtil.shallowCopy(securityGroupDTO, SecurityGroup.class);

        securityGroup.setCreateUser((SecureUser) assembleUtil.shallowCopy(securityGroupDTO.getCreateUserDTO(), SecureUser.class));
        securityGroup.setUpdateUser((SecureUser) assembleUtil.shallowCopy(securityGroupDTO.getUpdateUserDTO(), SecureUser.class));

        return securityGroup;
    }

    @Override
    public SecurityGroupDTO assembleToDTO(SecurityGroup securityGroup) {
        SecurityGroupDTO securityGroupDTO = (SecurityGroupDTO) assembleUtil.shallowCopy(securityGroup, SecurityGroupDTO.class);

        securityGroupDTO.setCreateUserDTO((SecureUserDTO) assembleUtil.shallowCopy(securityGroup.getCreateUser(), SecureUserDTO.class));
        securityGroupDTO.setUpdateUserDTO((SecureUserDTO) assembleUtil.shallowCopy(securityGroup.getUpdateUser(), SecureUserDTO.class));

        return securityGroupDTO;
    }

}
