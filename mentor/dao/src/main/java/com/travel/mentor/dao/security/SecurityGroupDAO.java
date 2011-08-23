package com.travel.mentor.dao.security;

import com.travel.mentor.dao.dto.security.SecurityGroupDTO;

import java.util.List;

public interface SecurityGroupDAO {

    List<SecurityGroupDTO> findAllSecurityGroups();

	SecurityGroupDTO saveOrUpdate(SecurityGroupDTO securityGroupDTO);

	void delete(SecurityGroupDTO securityGroupDTO);

    List<SecurityGroupDTO> getSecurityGroupsLikeGroupName(String value);

}
