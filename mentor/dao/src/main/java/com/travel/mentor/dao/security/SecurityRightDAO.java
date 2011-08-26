package com.travel.mentor.dao.security;

import com.travel.mentor.dao.dto.security.SecurityRightDTO;

import java.util.List;

public interface SecurityRightDAO {

    List<SecurityRightDTO> findSecurityRightsByLikeRightName(String rightName);

	List<SecurityRightDTO> findSecurityRightsByLikeRightNameAndType(String rightName, Long typeId);

    List<SecurityRightDTO> findSecurityRightsByLikeRightNameAndTypes(String rightName, List<Long> typeList);

    List<SecurityRightDTO> findAllSecurityRights();

    List<SecurityRightDTO> findSecurityRightsByType(Long typeId);

	SecurityRightDTO saveOrUpdate(SecurityRightDTO securityRightDTO);

    void delete(SecurityRightDTO securityRightDTO);

}
