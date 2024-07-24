package dev.hoang.homehotel.user.serivce;

import dev.hoang.homehotel.role.model.Role;
import dev.hoang.homehotel.user.dto.UserRequest;
import dev.hoang.homehotel.user.exception.UserException;
import dev.hoang.homehotel.user.model.User;
import dev.hoang.homehotel.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.text.Format;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> GetAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) throw new UserException("Can not find any user");
        return users;
    }

    public User getUserById(String id) throws UserException{
        return userRepository.findById(id).orElseThrow(() -> new UserException("Can not find any user with this id: " + id));
    }

    public User createNewUser(UserRequest userRequest) {
        if (userRepository.findUserByEmail(userRequest.getEmail()).isPresent()) throw new RuntimeException("Exiting User with this email");
        return userRepository.save(userRequest.toUser(passwordEncoder));
    }

    public Collection<Role> getUserRoles(String id) {
        User user = getUserById(id);
        return user.getRoles();
    }

    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new UserException("Can not find any user with email"));
    }
}
