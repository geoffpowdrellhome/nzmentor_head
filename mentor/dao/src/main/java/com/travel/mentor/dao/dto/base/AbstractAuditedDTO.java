package com.travel.mentor.dao.dto.base;

import com.travel.mentor.dao.dto.security.SecureUserDTO;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public abstract class AbstractAuditedDTO implements Serializable {

    protected SecureUserDTO createUserDTO= new SecureUserDTO();
    protected Timestamp createDate=new Timestamp(new Date().getTime());
    protected SecureUserDTO updateUserDTO= new SecureUserDTO();
    protected Timestamp updateDate=new Timestamp(new Date().getTime());

    protected SecureUserDTO loggedInUser = new SecureUserDTO();

    public SecureUserDTO getCreateUserDTO() {
        return createUserDTO;
    }

    public void setCreateUserDTO(SecureUserDTO createUserDTO) {
        this.createUserDTO = createUserDTO;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public SecureUserDTO getUpdateUserDTO() {
        return updateUserDTO;
    }

    public void setUpdateUserDTO(SecureUserDTO updateUserDTO) {
        this.updateUserDTO = updateUserDTO;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    public SecureUserDTO getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(SecureUserDTO loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}
