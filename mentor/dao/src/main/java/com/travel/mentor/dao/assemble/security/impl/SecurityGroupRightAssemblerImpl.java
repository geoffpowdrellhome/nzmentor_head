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
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SecurityGroupRightAssemblerImpl extends AbstractAssembler implements SecurityGroupRightAssembler {

    @Override
    public List<SecurityGroupRightDTO> assembleToDTOList(List<SecurityGroupRight> securityGroupRightList) {
        List<SecurityGroupRightDTO> securityGroupRightDTOList = new ArrayList<SecurityGroupRightDTO>();
        for (SecurityGroupRight securityGroupRight : securityGroupRightList) {
            securityGroupRightDTOList.add( assembleToDTOInstance(securityGroupRight) );
        }
        return securityGroupRightDTOList;
    }

    @Override
    public SecurityGroupRight assembleToEntityInstance(SecurityGroupRightDTO securityGroupRightDTO) {
        SecurityGroupRight securityGroupRight = (SecurityGroupRight) assembleUtil.shallowCopy(securityGroupRightDTO, SecurityGroupRight.class);

        securityGroupRight.setCreateUser((SecureUser) assembleUtil.shallowCopy(securityGroupRightDTO.getCreateUserDTO(), SecureUser.class));
        securityGroupRight.setUpdateUser((SecureUser) assembleUtil.shallowCopy(securityGroupRightDTO.getUpdateUserDTO(), SecureUser.class));

        securityGroupRight.setSecurityGroup((SecurityGroup) assembleUtil.shallowCopy(securityGroupRightDTO.getSecurityGroupDTO(), SecurityGroup.class));
        securityGroupRight.setSecurityRight((SecurityRight) assembleUtil.shallowCopy(securityGroupRightDTO.getSecurityRightDTO(), SecurityRight.class));

        return securityGroupRight;
    }

    @Override
    public SecurityGroupRightDTO assembleToDTOInstance(SecurityGroupRight securityGroupRight) {
        SecurityGroupRightDTO securityGroupRightDTO = (SecurityGroupRightDTO) assembleUtil.shallowCopy(securityGroupRight, SecurityGroupRightDTO.class);

        securityGroupRightDTO.setCreateUserDTO((SecureUserDTO) assembleUtil.shallowCopy(securityGroupRight.getCreateUser(), SecureUserDTO.class));
        securityGroupRightDTO.setUpdateUserDTO((SecureUserDTO) assembleUtil.shallowCopy(securityGroupRight.getUpdateUser(), SecureUserDTO.class));

        securityGroupRightDTO.setSecurityGroupDTO((SecurityGroupDTO) assembleUtil.shallowCopy(securityGroupRight.getSecurityGroup(), SecurityGroupDTO.class));
        securityGroupRightDTO.setSecurityRightDTO((SecurityRightDTO) assembleUtil.shallowCopy(securityGroupRight.getSecurityRight(), SecurityRightDTO.class));

        return securityGroupRightDTO;
    }

    @Override
    public SecurityGroupRight deepCopy(SecurityGroupRightDTO securityGroupRightDTO, SecurityGroupRight securityGroupRight) {
        String[] ignoreProperties = {"id"};
        assembleUtil.shallowCopy(securityGroupRightDTO, securityGroupRight, ignoreProperties);

        assembleUtil.shallowCopy(securityGroupRightDTO.getCreateUserDTO(), securityGroupRight.getCreateUser());
        assembleUtil.shallowCopy(securityGroupRightDTO.getUpdateUserDTO(), securityGroupRight.getUpdateUser());
        assembleUtil.shallowCopy(securityGroupRightDTO.getSecurityRightDTO(), securityGroupRight.getSecurityRight());
        assembleUtil.shallowCopy(securityGroupRightDTO.getSecurityGroupDTO(), securityGroupRight.getSecurityGroup());

        return securityGroupRight;
    }

}
