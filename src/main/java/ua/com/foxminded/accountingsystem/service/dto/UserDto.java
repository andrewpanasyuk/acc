package ua.com.foxminded.accountingsystem.service.dto;

import ua.com.foxminded.accountingsystem.model.UserRole;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by andreb on 05.07.17.
 */
public class UserDto {

    private String username;

    private Boolean enabled;

    private Set<UserRole> userRoles = new HashSet<>();

    public UserDto() {
    }

    public UserDto(String username, Boolean enabled, Set<UserRole> userRoles) {
        this.username = username;
        this.enabled = enabled;
        this.userRoles = userRoles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDto userDto = (UserDto) o;

        return username != null ? username.equals(userDto.username) : userDto.username == null;
    }

    @Override
    public int hashCode() {
        return username != null ? username.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "UserDto{" +
            "username='" + username + '\'' +
            ", enabled=" + enabled +
            ", userRoles=" + userRoles +
            '}';
    }
}

