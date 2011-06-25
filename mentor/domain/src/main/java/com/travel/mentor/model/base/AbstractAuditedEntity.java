package com.travel.mentor.model.base;

import com.travel.mentor.model.impl.User;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public abstract class AbstractAuditedEntity implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STORE")
    protected Long id;

    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "username")
    private User createUser;


//    @Column(name = "created_by")
//    protected String createUser = "sysadm";

    @Column(name = "created")
    protected Timestamp createDate = new Timestamp(new Date().getTime());

//    @Column(name = "updated_by")
//    protected String updateUser = "sysadm";

    @ManyToOne
    @JoinColumn(name = "updated_by", referencedColumnName = "username")
    private User updateUser;

    @Column(name = "updated")
    protected Timestamp updateDate = new Timestamp(new Date().getTime());

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public User getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(User updateUser) {
        this.updateUser = updateUser;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }
}
