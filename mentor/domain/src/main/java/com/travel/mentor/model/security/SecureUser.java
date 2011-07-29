package com.travel.mentor.model.security;

import com.travel.mentor.model.base.AbstractAuditedEntity;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "public", name = "secure_user")
@NamedQueries({
        @NamedQuery(name = "SecureUser.findUserByUsername",
                query = "SELECT o FROM SecureUser o WHERE o.username =:username ") ,
        @NamedQuery(name = "User.findUserByUsernamePassword",
                query = "SELECT o FROM SecureUser o WHERE o.username =:username and o.password =:password ") ,
        @NamedQuery(name = "User.findAll", query = "SELECT o FROM SecureUser o order by o.username")
})
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SecureUser extends AbstractAuditedEntity {

    public static final String FIND_SECURE_USER_BY_USERNAME = "SecureUser.findUserByUsername";
    public static final String FIND_SECURE_USER_BY_USERNAME_PASSWORD = "SecureUser.findUserByUsernamePassword";
    public static final String FIND_ALL_SECURE_USERS = "SecureUser.findAll";

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

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "locale", nullable = false)
    private String locale;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(name = "account_non_expired", nullable = false)
    private boolean accountNonExpired;

    @Column(name = "credentials_non_expired", nullable = false)
    private boolean credentialsNonExpired;

    @Column(name = "account_non_locked", nullable = false)
    private boolean accountNonLocked;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name="security_userrole",
            joinColumns=
                    @JoinColumn(name="secure_user_username", referencedColumnName="username"),
            inverseJoinColumns=
                    @JoinColumn(name="security_role_id", referencedColumnName="id")
    )
    private List<SecurityRole> securityRoleList = new ArrayList<SecurityRole>();

    public List<SecurityRole> getSecurityRoleList() {
        return securityRoleList;
    }

    public void setSecurityRoleList(List<SecurityRole> securityRoleList) {
        this.securityRoleList = securityRoleList;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }
}
