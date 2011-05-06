package com.nz.travel.mentor.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.QueryHint;
import javax.persistence.Table;

import com.nz.travel.mentor.type.BooleanJPAType;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

@Entity
@Table(name = "users")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@TypeDef(name = "booleanType", typeClass = BooleanJPAType.class)
@NamedQueries( {
    @NamedQuery(name = User.FIND_USER_WITH_USERNAME_AND_PASSWORD, query = "select u from User u where username=:username and password=:password", hints = {@QueryHint(name = "org.hibernate.cacheable", value = "true")}),
    @NamedQuery(name = User.FIND_USER_WITH_USERNAME, query = "select u from User u where username=:username", hints = {@QueryHint(name = "org.hibernate.cacheable", value = "true")})})
public final class User extends MentorObject {

    public static final String FIND_USER_WITH_USERNAME_AND_PASSWORD = "findUserWithUsernameAndPassword";
    public static final String FIND_USER_WITH_USERNAME = "findUserWithUsername";
    @Id
    @Column
    private Long id;
    @Column
    private String username;
    @Column
    private String password;
    
    @Column(name = "active")
    @Type(type = "booleanType")
    private boolean enabled;

    public User() {}

    public User(Long id, String username, String password, boolean enabled) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
