package dev.hoang.homehotel.user.repository;

import dev.hoang.homehotel.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    public Optional<User> findUserByEmail(String email);
}
