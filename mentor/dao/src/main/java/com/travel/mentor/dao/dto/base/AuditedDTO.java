package com.travel.mentor.dao.dto.base;

import com.travel.mentor.dao.dto.impl.UserSessionCookieDTO;

import java.io.Serializable;
import java.sql.Timestamp;

public abstract class AuditedDTO implements Serializable {

    protected String createUser;
    protected Timestamp createDate;
    protected String updateUser;
    protected Timestamp updateDate;
    protected UserSessionCookieDTO userSessionCookie = new UserSessionCookieDTO();

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

    public UserSessionCookieDTO getUserSessionCookie() {
        return userSessionCookie;
    }

    public void setUserSessionCookie(UserSessionCookieDTO userSessionCookie) {
        this.userSessionCookie = userSessionCookie;
    }
}
