package com.travel.mentor.dao.assemble.security.impl;

import com.travel.mentor.dao.assemble.base.impl.BaseAssemblerImpl;
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
        SecurityGroupRight securityGroupRight = (SecurityGroupRight) shallowCopy(securityGroupRightDTO, SecurityGroupRight.class);

        securityGroupRight.setCreateUser((SecureUser) shallowCopy(securityGroupRightDTO.getCreateUserDTO(), SecureUser.class));
        securityGroupRight.setUpdateUser((SecureUser) shallowCopy(securityGroupRightDTO.getUpdateUserDTO(), SecureUser.class));

        securityGroupRight.setSecurityGroup((SecurityGroup) shallowCopy(securityGroupRightDTO.getSecurityGroupDTO(), SecurityGroup.class));
        securityGroupRight.setSecurityRight((SecurityRight) shallowCopy(securityGroupRightDTO.getSecurityRightDTO(), SecurityRight.class));

        return securityGroupRight;
    }

    @Override
    public SecurityGroupRightDTO assembleToDTO(SecurityGroupRight securityGroupRight) {
        SecurityGroupRightDTO securityGroupRightDTO = (SecurityGroupRightDTO) shallowCopy(securityGroupRight, SecurityGroupRightDTO.class);

        securityGroupRightDTO.setCreateUserDTO((SecureUserDTO) shallowCopy(securityGroupRight.getCreateUser(), SecureUserDTO.class));
        securityGroupRightDTO.setUpdateUserDTO((SecureUserDTO) shallowCopy(securityGroupRight.getUpdateUser(), SecureUserDTO.class));

        securityGroupRightDTO.setSecurityGroupDTO((SecurityGroupDTO) shallowCopy(securityGroupRight.getSecurityGroup(), SecurityGroupDTO.class));
        securityGroupRightDTO.setSecurityRightDTO((SecurityRightDTO) shallowCopy(securityGroupRight.getSecurityRight(), SecurityRightDTO.class));

        return securityGroupRightDTO;
    }

}
