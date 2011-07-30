package com.travel.mentor.dao.dto.general;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDTO implements Serializable {

    protected String username;
    protected String firstname;
    protected String lastname;
    protected String password;
    protected String title;
    protected boolean enabled=true;
    protected boolean accountExpired=false;
    protected boolean credentialsExpired=false;
    protected boolean accountLocked=false;
    protected String createUser = "sysadm";
    protected Timestamp createDate = new Timestamp(new Date().getTime());
    protected String updateUser = "sysadm";
    protected Timestamp updateDate = new Timestamp(new Date().getTime());
    protected List<RoleDTO> roleDTOList = new ArrayList<RoleDTO>();
    protected UserSessionCookieDTO userSessionCookieDTO = new UserSessionCookieDTO();

    public UserDTO() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isAccountExpired() {
        return accountExpired;
    }

    public void setAccountExpired(boolean accountExpired) {
        this.accountExpired = accountExpired;
    }

    public boolean isCredentialsExpired() {
        return credentialsExpired;
    }

    public void setCredentialsExpired(boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }

    public boolean isAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    public List<RoleDTO> getRoleDTOList() {
        return roleDTOList;
    }

    public void setRoleDTOList(List<RoleDTO> roleDTOList) {
        this.roleDTOList = roleDTOList;
    }

    public UserSessionCookieDTO getUserSessionCookieDTO() {
        return userSessionCookieDTO;
    }

    public void setUserSessionCookieDTO(UserSessionCookieDTO userSessionCookieDTO) {
        this.userSessionCookieDTO = userSessionCookieDTO;
    }
}
