package com.travel.mentor.dao.security;

import com.travel.mentor.dao.dto.security.SecurityRightDTO;
import com.travel.mentor.dao.dto.security.SecurityRightTypeDTO;

import java.util.List;

public interface SecurityRightTypeDAO {

    List<SecurityRightDTO> findSecurityRightsByType(Long type);

	List<SecurityRightTypeDTO> findAllSecurityRightTypes();

	SecurityRightTypeDTO getSecurityRightTypeById(Long id);

}
