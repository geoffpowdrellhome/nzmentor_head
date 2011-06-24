package com.travel.mentor.dao.dto.base;

import com.travel.mentor.dao.dto.impl.UserSessionCookieDTO;

import java.io.Serializable;
import java.sql.Timestamp;

public abstract class AbstractAuditedDTO implements Serializable {

    protected Long id;
    protected String createUser;
    protected Timestamp createDate;
    protected String updateUser;
    protected Timestamp updateDate;
    protected UserSessionCookieDTO userSessionCookieDTO = new UserSessionCookieDTO();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public UserSessionCookieDTO getUserSessionCookieDTO() {
        return userSessionCookieDTO;
    }

    public void setUserSessionCookieDTO(UserSessionCookieDTO userSessionCookieDTO) {
        this.userSessionCookieDTO = userSessionCookieDTO;
    }
}
