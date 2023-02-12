package ru.project.carwash.entity;

import java.util.Objects;

public class UserDTO {
    private String username;
    private boolean enabled;

    public UserDTO(String username, boolean enabled) {
        this.username = username;
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserDTO userDTO = (UserDTO) o;
        return enabled == userDTO.enabled && username.equals(userDTO.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, enabled);
    }
}
