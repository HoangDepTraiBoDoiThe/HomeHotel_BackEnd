package dev.hoang.homehotel.user.repository;

import dev.hoang.homehotel.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository<User, String> {
}
