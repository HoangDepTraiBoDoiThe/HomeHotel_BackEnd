package dev.hoang.homehotel.role.service;

import dev.hoang.homehotel.role.dto.RoleRequest;
import dev.hoang.homehotel.role.exception.RoleException;
import dev.hoang.homehotel.role.model.Role;
import dev.hoang.homehotel.role.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role createNewRole(RoleRequest roleRequest) {
        List<Role> exitingRoles = roleRepository.findAll().stream().filter(role -> role.getRoleName().equals(roleRequest.getRoleName())).toList();
        if (!exitingRoles.isEmpty()) throw new RuntimeException("This role is already exit");
        Role newRole = roleRepository.save(roleRequest.toRole());
        return newRole;
    }

    public List<Role> getAllRole() {
        List<Role> roles = roleRepository.findAll();
        return roles;
    }

    public  Role getRoleById(String id) throws RoleException {
        Role role = roleRepository.findById(id).orElseThrow(() -> new RoleException("Can not find any role with id: " + id));
        return role;
    }
}
