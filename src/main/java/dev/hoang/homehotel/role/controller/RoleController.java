package dev.hoang.homehotel.role.controller;

import dev.hoang.homehotel.role.dto.RoleRequest;
import dev.hoang.homehotel.role.dto.RoleResponse;
import dev.hoang.homehotel.role.model.Role;
import dev.hoang.homehotel.role.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<List<RoleResponse>> getAllRoles() {
        List<Role> roles = roleService.getAllRole();
        List<RoleResponse> rolesResponses = roles.stream().map(RoleResponse::new).toList();
        return ResponseEntity.ok(rolesResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleResponse> getRole(@PathVariable String id) {
        Role role = roleService.getRoleById(id);
        return ResponseEntity.ok(new RoleResponse(role));
    }

    @PostMapping
    public ResponseEntity<RoleResponse> createRole(@RequestBody RoleRequest roleRequest) {
        Role newRole = roleService.createNewRole(roleRequest);
        return ResponseEntity.ok(new RoleResponse(newRole));
    }
}
