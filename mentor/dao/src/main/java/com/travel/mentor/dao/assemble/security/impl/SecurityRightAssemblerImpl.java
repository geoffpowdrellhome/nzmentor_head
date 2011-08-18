package com.travel.mentor.dao.assemble.security.impl;

import com.travel.mentor.dao.assemble.base.impl.BaseAssemblerImpl;
import com.travel.mentor.dao.assemble.security.SecurityRightAssembler;
import com.travel.mentor.dao.dto.security.SecureUserDTO;
import com.travel.mentor.dao.dto.security.SecurityRightDTO;
import com.travel.mentor.dao.dto.security.SecurityRightTypeDTO;
import com.travel.mentor.domain.security.SecureUser;
import com.travel.mentor.domain.security.SecurityRight;
import com.travel.mentor.domain.security.SecurityRightType;
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
        SecurityRight securityRight = (SecurityRight) shallowCopy(securityRightDTO, SecurityRight.class);

        securityRight.setCreateUser((SecureUser) shallowCopy(securityRightDTO.getCreateUserDTO(), SecureUser.class));
        securityRight.setUpdateUser((SecureUser) shallowCopy(securityRightDTO.getUpdateUserDTO(), SecureUser.class));

        securityRight.setSecurityRightType((SecurityRightType) shallowCopy(securityRightDTO.getSecurityRightTypeDTO(), SecurityRightType.class));

        return securityRight;
    }

    @Override
    public SecurityRightDTO assembleToDTO(SecurityRight securityRight) {
        SecurityRightDTO securityRightDTO = (SecurityRightDTO) shallowCopy(securityRight, SecurityRightDTO.class);

        securityRightDTO.setCreateUserDTO((SecureUserDTO) shallowCopy(securityRight.getCreateUser(), SecureUserDTO.class));
        securityRightDTO.setUpdateUserDTO((SecureUserDTO) shallowCopy(securityRight.getUpdateUser(), SecureUserDTO.class));

        securityRightDTO.setSecurityRightTypeDTO((SecurityRightTypeDTO) shallowCopy(securityRight.getSecurityRightType(), SecurityRightTypeDTO.class));

        return securityRightDTO;
    }

}
