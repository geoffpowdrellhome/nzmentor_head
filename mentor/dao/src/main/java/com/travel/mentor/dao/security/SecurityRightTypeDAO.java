package com.travel.mentor.dao.security;

import com.travel.mentor.dao.dto.security.SecurityRightTypeDTO;

import java.util.List;

public interface SecurityRightTypeDAO {

	List<SecurityRightTypeDTO> findAllSecurityRightTypes();

    SecurityRightTypeDTO find(Long id);

    SecurityRightTypeDTO saveOrUpdate(SecurityRightTypeDTO securityRightTypeDTO);

    void delete(SecurityRightTypeDTO securityRightTypeDTO);

}
