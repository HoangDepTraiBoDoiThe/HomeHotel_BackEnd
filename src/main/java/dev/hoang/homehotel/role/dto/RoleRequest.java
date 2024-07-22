package dev.hoang.homehotel.role.dto;

import dev.hoang.homehotel.role.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleRequest {
    private String roleName;

    public Role toRole() {
        return new Role(this.roleName);
    }
}
