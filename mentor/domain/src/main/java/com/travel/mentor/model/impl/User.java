package com.travel.mentor.model.impl;

import com.travel.mentor.model.base.AbstractAuditedEntity;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "public", name = "users")
@NamedQueries({
        @NamedQuery(name = "FindUserByUsername",
                query = "SELECT o FROM User o WHERE o.username =:username ") ,
        @NamedQuery(name = "FindUserByUsernamePassword",
                query = "SELECT o FROM User o WHERE o.username =:username and o.password =:password ") ,
        @NamedQuery(name = "User.findAll", query = "SELECT o FROM User o order by o.username")
})
@javax.persistence.SequenceGenerator(name = "SEQ_STORE", sequenceName = "public.users_id_seq", allocationSize = 1)
public class User extends AbstractAuditedEntity {

    public static final String FIND_USER_BY_USERNAME = "FindUserByUsername";
    public static final String FIND_USER_BY_USERNAME_PASSWORD = "FindUserByUsernamePassword";
    public static final String FIND_ALL_USERS = "FindAllUsers";

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

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

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name="user_role",
            joinColumns=
                    @JoinColumn(name="username", referencedColumnName="username"),
            inverseJoinColumns=
                    @JoinColumn(name="rolename", referencedColumnName="rolename")
    )
    private List<Role> roles = new ArrayList<Role>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
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
}
