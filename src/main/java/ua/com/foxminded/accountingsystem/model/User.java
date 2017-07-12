package ua.com.foxminded.accountingsystem.model;

import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "fm_users")
@Audited
public class User extends AbstractAuditEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @NotEmpty
    @Size(min = 3, max = 30)
    @Column(name = "username", unique = true, nullable = false, length = 30)
    private String username;

    @NotEmpty
    @Size(min = 8)
    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @Column(name = "enabled", nullable = false, columnDefinition = "boolean default false")
    private Boolean enabled;

    @Email
    @NotEmpty
    @Column(name = "email", nullable = false)
    private String email;

    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    @JoinTable(name = "fm_users_user_role",
        joinColumns = @JoinColumn(name = "username"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<UserRole> userRoles = new HashSet<>();

    public void addRole(UserRole userRole) {
        userRoles.add(userRole);
    }

    public void removeRole(UserRole userRole) {
        userRoles.remove(userRole);
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return username != null ? username.equals(user.username) : user.username == null;
    }

    @Override
    public int hashCode() {
        return username != null ? username.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User{" +
            "username='" + username + '\'' +
            ", enabled=" + enabled +
            ", email='" + email + '\'' +
            ", userRoles=" + userRoles +
            '}';
    }
}
