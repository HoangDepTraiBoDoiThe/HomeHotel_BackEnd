package dev.hoang.homehotel.role.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue
    private String id;
    private String roleName;

    public Role(String roleName) {
        this.roleName = roleName;
    }
}
