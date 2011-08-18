package com.travel.mentor.dao.security.impl;

import com.travel.mentor.dao.assemble.security.*;
import com.travel.mentor.dao.base.AbstractMentorDAO;
import com.travel.mentor.dao.dto.security.*;
import com.travel.mentor.dao.security.SecurityDAO;
import com.travel.mentor.domain.security.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.BeanUtils;


@Repository("securityDAO")
@Transactional
public class SecurityDAOImpl extends AbstractMentorDAO implements SecurityDAO {

    @Resource
    private SecureUserAssembler secureUserAssembler;

    @Resource
    private SecurityGroupAssembler securityGroupAssembler;

    @Resource
    private SecurityRoleAssembler securityRoleAssembler;

    @Resource
    private SecurityRightAssembler securityRightAssembler;

    @Resource
    private SecurityRightTypeAssembler securityRightTypeAssembler;

    @Resource
    private SecurityGroupRightAssembler securityGroupRightAssembler;


    @Override
    public SecureUserDTO findByUsername(String username) {
        SecureUser secureUser = em.find(SecureUser.class, username);
        return secureUserAssembler.assembleToDTO(secureUser);
    }

    @Override
    public SecureUserDTO saveOrUpdate(SecureUserDTO secureUserDTO, SecureUserDTO loggedOnUser) {
        SecureUser secureUser = secureUserAssembler.assembleToDomainObject(secureUserDTO);

        if (secureUserDTO.getUsername() == null || em.find(SecureUser.class, secureUserDTO.getUsername()) == null) {
            secureUser.setCreateUser(loggedOnUser.getUsername());
            secureUser.setCreateDate(new Timestamp(new Date().getTime()));
            secureUser.setUpdateUser(loggedOnUser.getUsername());
            secureUser.setUpdateDate(new Timestamp(new Date().getTime()));
            em.persist(secureUser);
        }
        else {
            SecureUser existingSecureUser = em.find(SecureUser.class, secureUserDTO.getUsername());
            BeanUtils.copyProperties(secureUser, existingSecureUser);
            secureUser.setUpdateUser(loggedOnUser.getUsername());
            secureUser.setUpdateDate(new Timestamp(new Date().getTime()));
            em.merge(secureUser);
        }

        return secureUserAssembler.assembleToDTO(secureUser);
    }

    @Override
    public List<SecurityRoleDTO> findAllSecurityRoles() {
        List<SecurityRole> securityRoleList = em.createNamedQuery(SecurityRole.FIND_ALL_SECURITY_ROLES).getResultList();
        return securityRoleAssembler.assembleToDTOList(securityRoleList);
    }

    @Override
    public SecurityRoleDTO saveOrUpdate(SecurityRoleDTO securityRoleDTO) {
        SecurityRole securityRole = securityRoleAssembler.assembleToDomainObject(securityRoleDTO);

        if (securityRoleDTO.getId() == null || em.find(SecurityRole.class, securityRoleDTO.getId()) == null) {
            securityRole.setCreateUser(secureUserAssembler.assembleToDomainObject(securityRoleDTO.getLoggedInUser()));
            securityRole.setCreateDate(new Timestamp(new Date().getTime()));
            securityRole.setUpdateUser(secureUserAssembler.assembleToDomainObject(securityRoleDTO.getLoggedInUser()));
            securityRole.setUpdateDate(new Timestamp(new Date().getTime()));
            em.persist(securityRole);
        }
        else {
            SecurityRole existingSecurityRole = em.find(securityRole.getClass(), securityRoleDTO.getId());
            BeanUtils.copyProperties(securityRole, existingSecurityRole);
            securityRole.setUpdateUser(secureUserAssembler.assembleToDomainObject(securityRoleDTO.getLoggedInUser()));
            securityRole.setUpdateDate(new Timestamp(new Date().getTime()));
            em.merge(securityRole);
        }

        return securityRoleAssembler.assembleToDTO(securityRole);
    }

    @Override
    public void delete(SecurityRoleDTO securityRoleDTO) {
        SecurityRole securityRole = em.find(SecurityRole.class, securityRoleDTO.getId());
        em.remove(securityRole);
        em.flush();
        em.getEntityManagerFactory().getCache().evict(securityRole.getClass(), securityRole.getId());
    }

    @Override
    public List<SecurityGroupDTO> findAllSecurityGroups() {
        List<SecurityGroup> securityGroupList = em.createNamedQuery(SecurityGroup.FIND_ALL_SECURITY_GROUPS).getResultList();
        return securityGroupAssembler.assembleToDTOList(securityGroupList);
    }

    @Override
    public SecurityGroupDTO saveOrUpdate(SecurityGroupDTO securityGroupDTO) {
        SecurityGroup securityGroup = securityGroupAssembler.assembleToDomainObject(securityGroupDTO);

        if (securityGroupDTO.getId() == null || em.find(SecurityGroup.class, securityGroupDTO.getId()) == null) {
            securityGroup.setCreateUser(secureUserAssembler.assembleToDomainObject(securityGroupDTO.getLoggedInUser()));
            securityGroup.setCreateDate(new Timestamp(new Date().getTime()));
            securityGroup.setUpdateUser(secureUserAssembler.assembleToDomainObject(securityGroupDTO.getLoggedInUser()));
            securityGroup.setUpdateDate(new Timestamp(new Date().getTime()));
            em.persist(securityGroup);
        }
        else {
            SecurityGroup existingSecurityGroup = em.find(securityGroup.getClass(), securityGroupDTO.getId());
            BeanUtils.copyProperties(securityGroup, existingSecurityGroup);
            securityGroup.setUpdateUser(secureUserAssembler.assembleToDomainObject(securityGroupDTO.getLoggedInUser()));
            securityGroup.setUpdateDate(new Timestamp(new Date().getTime()));
            em.merge(securityGroup);
        }

        return securityGroupAssembler.assembleToDTO(securityGroup);
    }

    @Override
    public void delete(SecurityGroupDTO securityGroupDTO) {
        SecurityGroup securityGroup = em.find(SecurityGroup.class, securityGroupDTO.getId());
        em.remove(securityGroup);
        em.flush();
        em.getEntityManagerFactory().getCache().evict(securityGroup.getClass(), securityGroup.getId());
    }

    @Override
    public void delete(SecurityRightDTO securityRightDTO) {
        SecurityRight securityRight = em.find(SecurityRight.class, securityRightDTO.getId());
        em.remove(securityRight);
        em.flush();
        em.getEntityManagerFactory().getCache().evict(securityRight.getClass(), securityRight.getId());
    }

    @Override
    public SecurityRightDTO saveOrUpdate(SecurityRightDTO securityRightDTO) {
        SecurityRight securityRight = securityRightAssembler.assembleToDomainObject(securityRightDTO);

        if (securityRightDTO.getId() == null || em.find(SecurityRight.class, securityRightDTO.getId()) == null) {
            securityRight.setCreateUser(secureUserAssembler.assembleToDomainObject(securityRightDTO.getLoggedInUser()));
            securityRight.setCreateDate(new Timestamp(new Date().getTime()));
            securityRight.setUpdateUser(secureUserAssembler.assembleToDomainObject(securityRightDTO.getLoggedInUser()));
            securityRight.setUpdateDate(new Timestamp(new Date().getTime()));
            em.persist(securityRight);
        }
        else {
            SecurityRight existingSecurityRight = em.find(securityRight.getClass(), securityRightDTO.getId());
            BeanUtils.copyProperties(securityRight, existingSecurityRight);
            securityRight.setUpdateUser(secureUserAssembler.assembleToDomainObject(securityRightDTO.getLoggedInUser()));
            securityRight.setUpdateDate(new Timestamp(new Date().getTime()));
            em.merge(securityRight);
        }

        return securityRightAssembler.assembleToDTO(securityRight);
    }

    @Override
    public List<SecurityRightDTO> findSecurityRightsByType(Long typeId) {
        Query query = em.createNamedQuery(SecurityRight.FIND_SECURITY_RIGHTS_BY_TYPE);
        query.setParameter("securityrighttypeid", typeId);
        List<SecurityRight> securityRightList = query.getResultList();

        return securityRightAssembler.assembleToDTOList(securityRightList);
    }

    @Override
    public List<SecurityRightDTO> findAllSecurityRights() {
        List<SecurityRight> securityRightList = em.createNamedQuery(SecurityRight.FIND_ALL_SECURITY_RIGHTS).getResultList();
        return securityRightAssembler.assembleToDTOList(securityRightList);
    }

    @Override
    public List<SecurityRightTypeDTO> findAllSecurityRightTypes() {
        List<SecurityRightType> securityRightTypeList = em.createNamedQuery(SecurityRightType.FIND_ALL_SECURITY_RIGHT_TYPES_NAMED_QUERY).getResultList();
        return securityRightTypeAssembler.assembleToDTOList(securityRightTypeList);
    }

    @Override
    public SecurityRightTypeDTO getSecurityRightTypeById(Long id) {
        SecurityRightType securityRightType = em.find(SecurityRightType.class, id);
        return securityRightTypeAssembler.assembleToDTO(securityRightType);
    }

    @Override
    public List<SecurityRightDTO> getSecurityRightsLikeRightName(String value) {
        Query query = em.createNamedQuery(SecurityRight.FIND_SECURITY_RIGHTS_BY_LIKE_RIGHT_NAME);
        query.setParameter("rightname", "%" + value + "%");
        List<SecurityRight> securityRightList = query.getResultList();

        return securityRightAssembler.assembleToDTOList(securityRightList);
    }

    @Override
    public List<SecurityRightDTO> getSecurityRightsLikeRightNameAndType(String value, Long typeId) {
        Query query = em.createNamedQuery(SecurityRight.FIND_SECURITY_RIGHTS_BY_LIKE_RIGHT_NAME_AND_TYPE);
        query.setParameter("rightname", "%" + value + "%");
        query.setParameter("securityrighttypeid", typeId);
        List<SecurityRight> securityRightList = query.getResultList();

        return securityRightAssembler.assembleToDTOList(securityRightList);
    }

    @Override
    public List<SecurityGroupDTO> getSecurityGroupsLikeGroupName(String value) {
        Query query = em.createNamedQuery(SecurityGroup.FIND_SECURITY_GROUPS_BY_LIKE_GROUP_NAME);
        query.setParameter("groupname", "%" + value + "%");
        List<SecurityGroup> securityGroupList = query.getResultList();

        return securityGroupAssembler.assembleToDTOList(securityGroupList);
    }

    @Override
    public List<SecurityRoleDTO> getSecurityRolesLikeRoleName(String value) {
        Query query = em.createNamedQuery(SecurityRole.FIND_SECURITY_ROLES_BY_LIKE_ROLE_NAME);
        query.setParameter("rolename", "%" + value + "%");
        List<SecurityRole> securityRoleList = query.getResultList();

        return securityRoleAssembler.assembleToDTOList(securityRoleList);
    }

    @Override
    public List<SecurityGroupRightDTO> getSecurityGroupRightsBySecurityGroup(SecurityGroupDTO securityGroupDTO) {
        Query query = em.createNamedQuery(SecurityGroupRight.FIND_ALL_SECURITY_GROUP_RIGHTS_BY_SECURITY_GROUP);
        query.setParameter("securitygroupid", securityGroupDTO.getId());
        List<SecurityGroupRight> securityGroupRightList = query.getResultList();

        return securityGroupRightAssembler.assembleToDTOList(securityGroupRightList);
    }

    @Override
    public List<SecurityRightDTO> getSecurityRightsLikeRightNameAndTypes(String value, List<Long> list) {

        Query query = em.createNamedQuery(SecurityRight.FIND_SECURITY_RIGHTS_BY_LIKE_RIGHT_NAME_AND_TYPE);

        List<SecurityRightDTO> securityRightDTOList = new ArrayList<SecurityRightDTO>();

        for (Long typeId : list) {
            query.setParameter("rightname", "%" + value + "%");
            query.setParameter("securityrighttypeid", typeId);
            List<SecurityRight> securityRightList = query.getResultList();

            securityRightDTOList.addAll(securityRightAssembler.assembleToDTOList(securityRightList));
        }

        return securityRightDTOList;
    }


    @Override
    protected void cacheDomainObjects() {
        cacheSecureUsers();
        cacheSecureGroups();
        cacheSecureGroupRights();
        cacheSecurityRights();
        cacheSecurityRightTypes();
        cacheSecurityRoles();
    }

    private void cacheSecureUsers() {
        StopWatch watch = new StopWatch();
        watch.start("SecurityDAOImpl.cacheSecureUsers()");
        em.createNamedQuery(SecureUser.FIND_ALL_SECURE_USERS).setHint("org.hibernate.cacheable", true).getResultList();
        watch.stop();
        if (logger.isDebugEnabled()) {
            logger.debug(watch.prettyPrint());
            logger.info("Total Time in Seconds SecurityDAOImpl.cacheSecureUsers() = " + watch.getTotalTimeSeconds());
        }
    }

    private void cacheSecureGroups() {
        StopWatch watch = new StopWatch();
        watch.start("SecurityDAOImpl.cacheSecureGroups()");
        em.createNamedQuery(SecurityGroup.FIND_ALL_SECURITY_GROUPS).setHint("org.hibernate.cacheable", true).getResultList();
        watch.stop();
        if (logger.isDebugEnabled()) {
            logger.debug(watch.prettyPrint());
            logger.info("Total Time in Seconds SecurityDAOImpl.cacheSecureGroups() = " + watch.getTotalTimeSeconds());
        }
    }

    private void cacheSecureGroupRights() {
        StopWatch watch = new StopWatch();
        watch.start("SecurityDAOImpl.cacheSecureGroupRights()");
        em.createNamedQuery(SecurityGroupRight.FIND_ALL_SECURITY_GROUP_RIGHTS).setHint("org.hibernate.cacheable", true).getResultList();
        watch.stop();
        if (logger.isDebugEnabled()) {
            logger.debug(watch.prettyPrint());
            logger.info("Total Time in Seconds SecurityDAOImpl.cacheSecureGroupRights() = " + watch.getTotalTimeSeconds());
        }
    }

    private void cacheSecurityRights() {
        StopWatch watch = new StopWatch();
        watch.start("SecurityDAOImpl.cacheSecurityRights()");
        em.createNamedQuery(SecurityRight.FIND_ALL_SECURITY_RIGHTS).setHint("org.hibernate.cacheable", true).getResultList();
        watch.stop();
        if (logger.isDebugEnabled()) {
            logger.debug(watch.prettyPrint());
            logger.info("Total Time in Seconds SecurityDAOImpl.cacheSecurityRights() = " + watch.getTotalTimeSeconds());
        }
    }

    private void cacheSecurityRightTypes() {
        StopWatch watch = new StopWatch();
        watch.start("SecurityDAOImpl.cacheSecurityRightTypes()");
        em.createNamedQuery(SecurityRightType.FIND_ALL_SECURITY_RIGHT_TYPES_NAMED_QUERY).setHint("org.hibernate.cacheable", true).getResultList();
        watch.stop();
        if (logger.isDebugEnabled()) {
            logger.debug(watch.prettyPrint());
            logger.info("Total Time in Seconds SecurityDAOImpl.cacheSecurityRightTypes() = " + watch.getTotalTimeSeconds());
        }
    }

    private void cacheSecurityRoles() {
        StopWatch watch = new StopWatch();
        watch.start("SecurityDAOImpl.cacheSecurityRoles()");
        em.createNamedQuery(SecurityRole.FIND_ALL_SECURITY_ROLES).setHint("org.hibernate.cacheable", true).getResultList();
        watch.stop();
        if (logger.isDebugEnabled()) {
            logger.debug(watch.prettyPrint());
            logger.info("Total Time in Seconds SecurityDAOImpl.cacheSecurityRoles() = " + watch.getTotalTimeSeconds());
        }
    }


}
