package com.travel.mentor.dao.security;

import com.travel.mentor.dao.dto.security.SecurityRightDTO;

import java.util.List;

public interface SecurityRightDAO {

    List<SecurityRightDTO> getSecurityRightsLikeRightName(String value);

	List<SecurityRightDTO> getSecurityRightsLikeRightNameAndType(String value, Long typeId);

    List<SecurityRightDTO> getSecurityRightsLikeRightNameAndTypes(String value, List<Long> list);

    List<SecurityRightDTO> findAllSecurityRights();

    List<SecurityRightDTO> findSecurityRightsByType(Long type);

	SecurityRightDTO saveOrUpdate(SecurityRightDTO securityRightDTO);

    void delete(SecurityRightDTO securityRightDTO);

}
