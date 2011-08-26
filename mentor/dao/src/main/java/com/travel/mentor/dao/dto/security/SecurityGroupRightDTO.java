package com.travel.mentor.dao.dto.security;

import com.travel.mentor.dao.dto.base.AbstractAuditedIdDTO;

public class SecurityGroupRightDTO extends AbstractAuditedIdDTO {

    private SecurityRightDTO securityRightDTO;
    private SecurityGroupDTO securityGroupDTO;

    public SecurityGroupRightDTO() {}

    public SecurityRightDTO getSecurityRightDTO() {
        return securityRightDTO;
    }

    public void setSecurityRightDTO(SecurityRightDTO securityRightDTO) {
        this.securityRightDTO = securityRightDTO;
    }

    public SecurityGroupDTO getSecurityGroupDTO() {
        return securityGroupDTO;
    }

    public void setSecurityGroupDTO(SecurityGroupDTO securityGroupDTO) {
        this.securityGroupDTO = securityGroupDTO;
    }

}
