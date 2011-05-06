package com.nz.travel.mentor.model.impl;

import com.nz.travel.mentor.model.AbstractJPAEntity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
public class Audited extends AbstractJPAEntity {

    @Column(name = "create_user")
    protected String createUser = "SysUser";

    @Column(name = "create_date")
    protected Timestamp createDate = new Timestamp(new Date().getTime());

    @Column(name = "update_user")
    protected String updateUser = "SysUser";

    @Column(name = "update_date")
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
