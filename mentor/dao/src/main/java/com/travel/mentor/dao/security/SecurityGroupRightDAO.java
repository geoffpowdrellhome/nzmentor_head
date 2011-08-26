package com.travel.mentor.dao.security;

import com.travel.mentor.dao.dto.security.SecurityGroupDTO;
import com.travel.mentor.dao.dto.security.SecurityGroupRightDTO;

import java.util.List;

public interface SecurityGroupRightDAO {

    SecurityGroupRightDTO find(Long id);

    List<SecurityGroupRightDTO> findAllSecurityGroupRights();

    List<SecurityGroupRightDTO> findSecurityGroupRightsBySecurityGroup(SecurityGroupDTO securityGroupDTO);

    SecurityGroupRightDTO saveOrUpdate(SecurityGroupRightDTO securityGroupRightDTO);

    void delete(SecurityGroupRightDTO securityGroupRightDTO);

}
