package com.travel.mentor.dao.dto.base;

import com.travel.mentor.dao.dto.impl.UserDTO;
import com.travel.mentor.dao.dto.impl.UserSessionCookieDTO;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public abstract class AbstractAuditedDTO implements Serializable {

    protected Long id;
    protected UserDTO createUserDTO;
    protected Timestamp createDate=new Timestamp(new Date().getTime());
    protected UserDTO updateUserDTO;
    protected Timestamp updateDate=new Timestamp(new Date().getTime());
    protected UserSessionCookieDTO userSessionCookieDTO = new UserSessionCookieDTO();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getCreateUserDTO() {
        return createUserDTO;
    }

    public void setCreateUserDTO(UserDTO createUserDTO) {
        this.createUserDTO = createUserDTO;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public UserDTO getUpdateUserDTO() {
        return updateUserDTO;
    }

    public void setUpdateUserDTO(UserDTO updateUserDTO) {
        this.updateUserDTO = updateUserDTO;
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
