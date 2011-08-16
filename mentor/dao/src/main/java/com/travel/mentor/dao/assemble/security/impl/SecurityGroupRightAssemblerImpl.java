package com.travel.mentor.dao.assemble.security.impl;

import com.travel.mentor.dao.assemble.base.impl.BaseAssemblerImpl;
import com.travel.mentor.dao.assemble.security.SecurityGroupRightAssembler;
import com.travel.mentor.dao.dto.security.SecureUserDTO;
import com.travel.mentor.dao.dto.security.SecurityGroupRightDTO;
import com.travel.mentor.domain.security.SecureUser;
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
        SecurityGroupRight securityGroupRight = (SecurityGroupRight) shallowCopy(securityGroupRightDTO, SecurityGroupRight.class);

        securityGroupRight.setCreateUser((SecureUser) shallowCopy(securityGroupRightDTO.getCreateUserDTO(), SecureUser.class));
        securityGroupRight.setUpdateUser((SecureUser) shallowCopy(securityGroupRightDTO.getUpdateUserDTO(), SecureUser.class));

        return securityGroupRight;
    }

    @Override
    public SecurityGroupRightDTO assembleToDTO(SecurityGroupRight securityGroupRight) {
        SecurityGroupRightDTO securityGroupDTO = (SecurityGroupRightDTO) shallowCopy(securityGroupRight, SecurityGroupRight.class);

        securityGroupDTO.setCreateUserDTO((SecureUserDTO) shallowCopy(securityGroupRight.getCreateUser(), SecureUserDTO.class));
        securityGroupDTO.setUpdateUserDTO((SecureUserDTO) shallowCopy(securityGroupRight.getUpdateUser(), SecureUserDTO.class));

        return securityGroupDTO;
    }

}
