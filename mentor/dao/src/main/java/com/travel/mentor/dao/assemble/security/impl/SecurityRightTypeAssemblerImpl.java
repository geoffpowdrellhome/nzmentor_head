package com.travel.mentor.dao.assemble.security.impl;

import com.travel.mentor.dao.assemble.base.impl.BaseAssemblerImpl;
import com.travel.mentor.dao.assemble.security.SecurityRightTypeAssembler;
import com.travel.mentor.dao.dto.security.SecureUserDTO;
import com.travel.mentor.dao.dto.security.SecurityRightTypeDTO;
import com.travel.mentor.domain.security.SecureUser;
import com.travel.mentor.domain.security.SecurityRightType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SecurityRightTypeAssemblerImpl extends BaseAssemblerImpl implements SecurityRightTypeAssembler {

    @Override
    public List<SecurityRightTypeDTO> assembleToDTOList(List<SecurityRightType> securityRightTypeList) {
        List<SecurityRightTypeDTO> securityRightTypeDTOList = new ArrayList<SecurityRightTypeDTO>();
        for (SecurityRightType securityRightType : securityRightTypeList) {
            securityRightTypeDTOList.add( assembleToDTO(securityRightType) );
        }
        return securityRightTypeDTOList;
    }

    @Override
    public SecurityRightType assembleToDomainObject(SecurityRightTypeDTO securityRightTypeDTO) {
        SecurityRightType securityRight = (SecurityRightType) shallowCopy(securityRightTypeDTO, SecurityRightType.class);

        securityRight.setCreateUser((SecureUser) shallowCopy(securityRightTypeDTO.getCreateUserDTO(), SecureUser.class));
        securityRight.setUpdateUser((SecureUser) shallowCopy(securityRightTypeDTO.getUpdateUserDTO(), SecureUser.class));

        return securityRight;
    }

    @Override
    public SecurityRightTypeDTO assembleToDTO(SecurityRightType securityRightType) {
        SecurityRightTypeDTO securityRightTypeDTO = (SecurityRightTypeDTO) shallowCopy(securityRightType, SecurityRightTypeDTO.class);

        securityRightTypeDTO.setCreateUserDTO((SecureUserDTO) shallowCopy(securityRightType.getCreateUser(), SecureUserDTO.class));
        securityRightTypeDTO.setUpdateUserDTO((SecureUserDTO) shallowCopy(securityRightType.getUpdateUser(), SecureUserDTO.class));

        return securityRightTypeDTO;
    }

}
