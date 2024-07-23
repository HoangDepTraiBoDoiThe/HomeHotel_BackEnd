package dev.hoang.homehotel.role.dto;

import dev.hoang.homehotel.role.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleResponse {
    private String roleName;

    public RoleResponse(String roleName) {
        this.roleName = roleName;
    }

    public RoleResponse(Role role) {
        this.roleName = role.getRoleName();
    }
}
