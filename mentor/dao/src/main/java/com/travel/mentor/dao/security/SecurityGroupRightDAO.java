package com.travel.mentor.dao.security;

import com.travel.mentor.dao.dto.security.SecurityGroupDTO;
import com.travel.mentor.dao.dto.security.SecurityGroupRightDTO;

import java.util.List;

public interface SecurityGroupRightDAO {

    List<SecurityGroupRightDTO> findAllSecurityGroupRights();

    SecurityGroupRightDTO saveOrUpdate(SecurityGroupRightDTO securityGroupRightDTO);

    void delete(SecurityGroupRightDTO securityGroupRightDTO);

    List<SecurityGroupRightDTO> getSecurityGroupRightsBySecurityGroup(SecurityGroupDTO securityGroupDTO);
}
