package com.travel.mentor.model.base;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
public class Audited implements Serializable {

    @Column(name = "created_by")
    protected String createUser = "sysadm";

    @Column(name = "created")
    protected Timestamp createDate = new Timestamp(new Date().getTime());

    @Column(name = "updated_by")
    protected String updateUser = "sysadm";

    @Column(name = "updated")
    protected Timestamp updateDate = new Timestamp(new Date().getTime());


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
}
