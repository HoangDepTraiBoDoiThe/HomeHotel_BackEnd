package dev.hoang.homehotel.role.model.repository;

import dev.hoang.homehotel.role.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface roleRepository extends JpaRepository<Role, String> {
}
