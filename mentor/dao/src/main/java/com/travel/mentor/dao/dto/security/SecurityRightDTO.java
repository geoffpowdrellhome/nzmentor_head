package com.travel.mentor.dao.dto.security;

import com.travel.mentor.dao.dto.base.AbstractAuditedIdNameDescDTO;

public class SecurityRightDTO extends AbstractAuditedIdNameDescDTO {

    private SecurityRightTypeDTO securityRightTypeDTO;

    public SecurityRightDTO() {}

    public SecurityRightTypeDTO getSecurityRightTypeDTO() {
        return securityRightTypeDTO;
    }

    public void setSecurityRightTypeDTO(SecurityRightTypeDTO securityRightTypeDTO) {
        this.securityRightTypeDTO = securityRightTypeDTO;
    }

}
