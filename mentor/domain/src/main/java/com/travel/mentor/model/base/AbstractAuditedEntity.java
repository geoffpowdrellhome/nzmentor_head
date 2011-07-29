package com.travel.mentor.model.base;

import com.travel.mentor.model.security.SecureUser;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@MappedSuperclass
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public abstract class AbstractAuditedEntity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "username")
    protected SecureUser createUser;

    @Column(name = "created")
    protected Timestamp createDate = new Timestamp(new Date().getTime());

    @ManyToOne
    @JoinColumn(name = "updated_by", referencedColumnName = "username")
    protected SecureUser updateUser;

    @Column(name = "updated")
    protected Timestamp updateDate = new Timestamp(new Date().getTime());

    public SecureUser getCreateUser() {
        return createUser;
    }

    public void setCreateUser(SecureUser createUser) {
        this.createUser = createUser;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public SecureUser getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(SecureUser updateUser) {
        this.updateUser = updateUser;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

}
