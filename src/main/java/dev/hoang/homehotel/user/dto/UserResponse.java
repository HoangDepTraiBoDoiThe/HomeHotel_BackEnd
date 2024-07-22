package dev.hoang.homehotel.user.dto;

import dev.hoang.homehotel.role.model.Role;
import dev.hoang.homehotel.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private Collection<Role> roles;

    public UserResponse(String name, String email, Collection<Role> roles) {
        this.name = name;
        this.email = email;
        this.roles = roles;
    }
    public UserResponse(User user) {
        this.name = user.getUserName();
        this.email = user.getEmail();
        this.roles = user.getRoles();
    }
}
