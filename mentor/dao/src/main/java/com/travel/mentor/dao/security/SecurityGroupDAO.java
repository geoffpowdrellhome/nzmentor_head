package com.travel.mentor.dao.security;

import com.travel.mentor.dao.dto.security.SecurityGroupDTO;

import java.util.List;

public interface SecurityGroupDAO {

    SecurityGroupDTO find(Long id);

    List<SecurityGroupDTO> findAllSecurityGroups();

    List<SecurityGroupDTO> findSecurityGroupsByLikeGroupName(String groupName);

    SecurityGroupDTO saveOrUpdate(SecurityGroupDTO securityGroupDTO);

	void delete(SecurityGroupDTO securityGroupDTO);

}
