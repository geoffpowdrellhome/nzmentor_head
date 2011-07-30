package com.travel.mentor.domain.general;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(schema = "public", name = "users")
@NamedQueries({
        @NamedQuery(name = "User.findUserByUsername",
                query = "SELECT o FROM User o WHERE o.username =:username ") ,
        @NamedQuery(name = "User.findUserByUsernamePassword",
                query = "SELECT o FROM User o WHERE o.username =:username and o.password =:password ") ,
        @NamedQuery(name = "User.findAll", query = "SELECT o FROM User o order by o.username")
})
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User implements Serializable {

    public static final String FIND_USER_BY_USERNAME = "User.findUserByUsername";
    public static final String FIND_USER_BY_USERNAME_PASSWORD = "User.findUserByUsernamePassword";
    public static final String FIND_ALL_USERS = "User.findAll";

    @Id
    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(name = "accountExpired", nullable = false)
    private boolean accountExpired;

    @Column(name = "credentialsExpired", nullable = false)
    private boolean credentialsExpired;

    @Column(name = "accountLocked", nullable = false)
    private boolean accountLocked;

    @Column(name = "created_by")
    protected String createUser = "sysadm";

    @Column(name = "created")
    protected Timestamp createDate = new Timestamp(new Date().getTime());

    @Column(name = "updated_by")
    protected String updateUser = "sysadm";

    @Column(name = "updated")
    protected Timestamp updateDate = new Timestamp(new Date().getTime());

//    @ManyToMany(fetch=FetchType.EAGER)
//    @JoinTable(
//            name="user_role",
//            joinColumns=
//                    @JoinColumn(name="username", referencedColumnName="username"),
//            inverseJoinColumns=
//                    @JoinColumn(name="rolename", referencedColumnName="rolename")
//    )
//    private List<Role> roles = new ArrayList<Role>();

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

//    public List<Role> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(List<Role> roles) {
//        this.roles = roles;
//    }
}
