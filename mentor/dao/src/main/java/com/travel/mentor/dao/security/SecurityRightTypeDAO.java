package com.travel.mentor.dao.security;

import com.travel.mentor.dao.dto.security.SecurityRightDTO;
import com.travel.mentor.dao.dto.security.SecurityRightTypeDTO;

import java.util.List;

public interface SecurityRightTypeDAO {

    List<SecurityRightTypeDTO> findSecurityRightsTypes();

    List<SecurityRightDTO> findSecurityRightsByType(Long type);

	List<SecurityRightTypeDTO> findAllSecurityRightTypes();

	SecurityRightTypeDTO findSecurityRightTypeById(Long id);

    SecurityRightTypeDTO saveOrUpdate(SecurityRightTypeDTO securityRightTypeDTO);

    void delete(SecurityRightTypeDTO securityRightTypeDTO);

}
