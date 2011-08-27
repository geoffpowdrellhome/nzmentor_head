package com.travel.mentor.dao.assemble.security.impl;

import com.travel.mentor.dao.assemble.base.AbstractAssembler;
import com.travel.mentor.dao.assemble.security.SecurityGroupRightAssembler;
import com.travel.mentor.dao.dto.security.SecureUserDTO;
import com.travel.mentor.dao.dto.security.SecurityGroupDTO;
import com.travel.mentor.dao.dto.security.SecurityGroupRightDTO;
import com.travel.mentor.dao.dto.security.SecurityRightDTO;
import com.travel.mentor.domain.security.SecureUser;
import com.travel.mentor.domain.security.SecurityGroup;
import com.travel.mentor.domain.security.SecurityGroupRight;
import com.travel.mentor.domain.security.SecurityRight;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SecurityGroupRightAssemblerImpl extends AbstractAssembler implements SecurityGroupRightAssembler {

    @Override
    public List<SecurityGroupRightDTO> assembleToDTOList(List<SecurityGroupRight> securityGroupRightList) {
        List<SecurityGroupRightDTO> securityGroupRightDTOList = new ArrayList<SecurityGroupRightDTO>();
        for (SecurityGroupRight securityGroupRight : securityGroupRightList) {
            securityGroupRightDTOList.add(assembleToDTOInstance(securityGroupRight));
        }
        return securityGroupRightDTOList;
    }

    @Override
    public SecurityGroupRight assembleToEntityInstance(SecurityGroupRightDTO securityGroupRightDTO) {
        SecurityGroupRight securityGroupRight = (SecurityGroupRight) assembleUtil.copyPropertiesToInstantiatedClass(securityGroupRightDTO, SecurityGroupRight.class);

        securityGroupRight.setCreateUser((SecureUser) assembleUtil.copyPropertiesToInstantiatedClass(securityGroupRightDTO.getCreateUserDTO(), SecureUser.class));
        securityGroupRight.setUpdateUser((SecureUser) assembleUtil.copyPropertiesToInstantiatedClass(securityGroupRightDTO.getUpdateUserDTO(), SecureUser.class));
        securityGroupRight.setSecurityGroup((SecurityGroup) assembleUtil.copyPropertiesToInstantiatedClass(securityGroupRightDTO.getSecurityGroupDTO(), SecurityGroup.class));
        securityGroupRight.setSecurityRight((SecurityRight) assembleUtil.copyPropertiesToInstantiatedClass(securityGroupRightDTO.getSecurityRightDTO(), SecurityRight.class));

        return securityGroupRight;
    }

    @Override
    public SecurityGroupRightDTO assembleToDTOInstance(SecurityGroupRight securityGroupRight) {
        SecurityGroupRightDTO securityGroupRightDTO = (SecurityGroupRightDTO) assembleUtil.copyPropertiesToInstantiatedClass(securityGroupRight, SecurityGroupRightDTO.class);

        securityGroupRightDTO.setCreateUserDTO((SecureUserDTO) assembleUtil.copyPropertiesToInstantiatedClass(securityGroupRight.getCreateUser(), SecureUserDTO.class));
        securityGroupRightDTO.setUpdateUserDTO((SecureUserDTO) assembleUtil.copyPropertiesToInstantiatedClass(securityGroupRight.getUpdateUser(), SecureUserDTO.class));
        securityGroupRightDTO.setSecurityGroupDTO((SecurityGroupDTO) assembleUtil.copyPropertiesToInstantiatedClass(securityGroupRight.getSecurityGroup(), SecurityGroupDTO.class));
        securityGroupRightDTO.setSecurityRightDTO((SecurityRightDTO) assembleUtil.copyPropertiesToInstantiatedClass(securityGroupRight.getSecurityRight(), SecurityRightDTO.class));

        return securityGroupRightDTO;
    }

    @Override
    public SecurityGroupRight deepCopy(SecurityGroupRightDTO securityGroupRightDTO, SecurityGroupRight securityGroupRight) {
        String[] ignoreProperties = {"id"};
        BeanUtils.copyProperties(securityGroupRightDTO, securityGroupRight, ignoreProperties);

        BeanUtils.copyProperties(securityGroupRightDTO.getCreateUserDTO(), securityGroupRight.getCreateUser());
        BeanUtils.copyProperties(securityGroupRightDTO.getUpdateUserDTO(), securityGroupRight.getUpdateUser());
        BeanUtils.copyProperties(securityGroupRightDTO.getSecurityRightDTO(), securityGroupRight.getSecurityRight());
        BeanUtils.copyProperties(securityGroupRightDTO.getSecurityGroupDTO(), securityGroupRight.getSecurityGroup());

        return securityGroupRight;
    }

}
