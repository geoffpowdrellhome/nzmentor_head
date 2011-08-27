package com.travel.mentor.dao.assemble.security.impl;

import com.travel.mentor.dao.assemble.base.AbstractAssembler;
import com.travel.mentor.dao.assemble.security.SecurityGroupAssembler;
import com.travel.mentor.dao.dto.security.SecureUserDTO;
import com.travel.mentor.dao.dto.security.SecurityGroupDTO;
import com.travel.mentor.domain.security.SecureUser;
import com.travel.mentor.domain.security.SecurityGroup;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SecurityGroupAssemblerImpl extends AbstractAssembler implements SecurityGroupAssembler {

    @Override
    public List<SecurityGroupDTO> assembleToDTOList(List<SecurityGroup> securityGroupList) {
        List<SecurityGroupDTO> securityGroupDTOList = new ArrayList<SecurityGroupDTO>();
        for (SecurityGroup securityGroup : securityGroupList) {
            securityGroupDTOList.add(assembleToDTOInstance(securityGroup));
        }
        return securityGroupDTOList;
    }

    @Override
    public SecurityGroup assembleToEntityInstance(SecurityGroupDTO securityGroupDTO) {
        SecurityGroup securityGroup = (SecurityGroup) assembleUtil.copyPropertiesToInstantiatedClass(securityGroupDTO, SecurityGroup.class);

        securityGroup.setCreateUser((SecureUser) assembleUtil.copyPropertiesToInstantiatedClass(securityGroupDTO.getCreateUserDTO(), SecureUser.class));
        securityGroup.setUpdateUser((SecureUser) assembleUtil.copyPropertiesToInstantiatedClass(securityGroupDTO.getUpdateUserDTO(), SecureUser.class));

        return securityGroup;
    }

    @Override
    public SecurityGroupDTO assembleToDTOInstance(SecurityGroup securityGroup) {
        SecurityGroupDTO securityGroupDTO = (SecurityGroupDTO) assembleUtil.copyPropertiesToInstantiatedClass(securityGroup, SecurityGroupDTO.class);

        securityGroupDTO.setCreateUserDTO((SecureUserDTO) assembleUtil.copyPropertiesToInstantiatedClass(securityGroup.getCreateUser(), SecureUserDTO.class));
        securityGroupDTO.setUpdateUserDTO((SecureUserDTO) assembleUtil.copyPropertiesToInstantiatedClass(securityGroup.getUpdateUser(), SecureUserDTO.class));

        return securityGroupDTO;
    }

    @Override
    public SecurityGroup deepCopy(SecurityGroupDTO securityGroupDTO, SecurityGroup securityGroup) {
        String[] ignoreProperties = {"id"};
        BeanUtils.copyProperties(securityGroupDTO, securityGroup, ignoreProperties);

        BeanUtils.copyProperties(securityGroupDTO.getCreateUserDTO(), securityGroup.getCreateUser());
        BeanUtils.copyProperties(securityGroupDTO.getUpdateUserDTO(), securityGroup.getUpdateUser());

        return securityGroup;
    }
}
