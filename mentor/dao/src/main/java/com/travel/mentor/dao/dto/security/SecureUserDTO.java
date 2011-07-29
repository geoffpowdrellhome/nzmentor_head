package com.travel.mentor.dao.dto.security;

import com.travel.mentor.dao.dto.base.AbstractAuditedDTO;
import com.travel.mentor.dao.dto.security.SecurityRoleDTO;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SecureUserDTO implements Serializable {

    private String username;
    private String password;
    private String title;
    private String firstname;
    private String lastname;
    private String email;
    private String locale;
    private boolean enabled;
    private boolean accountNonExpired;
    private boolean credentialsNonExpired;
    private boolean accountNonLocked;
//    private SecureUserDTO createUserDTO= new SecureUserDTO();
//    private Timestamp createDate=new Timestamp(new Date().getTime());
//    private SecureUserDTO updateUserDTO= new SecureUserDTO();
//    private Timestamp updateDate=new Timestamp(new Date().getTime());
//    private SecureUserDTO loggedInUser = new SecureUserDTO();

    private List<SecurityRoleDTO> securityRoleDTOList = new ArrayList<SecurityRoleDTO>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public List<SecurityRoleDTO> getSecurityRoleDTOList() {
        return securityRoleDTOList;
    }

    public void setSecurityRoleDTOList(List<SecurityRoleDTO> securityRoleDTOList) {
        this.securityRoleDTOList = securityRoleDTOList;
    }
}
