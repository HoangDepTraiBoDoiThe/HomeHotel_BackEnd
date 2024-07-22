package dev.hoang.homehotel.user.serivce;

import dev.hoang.homehotel.user.dto.UserRequest;
import dev.hoang.homehotel.user.exception.UserException;
import dev.hoang.homehotel.user.model.User;
import dev.hoang.homehotel.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> GetAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) throw new UserException("Can not find any user");
        return users;
    }

    public User getUserById(String id) throws UserException{
        return userRepository.findById(id).orElseThrow(() -> new UserException("Can not find any user with this id: " + id));
    }

    public User createNewUser(UserRequest userRequest) {
        User newUser = userRequest.toUser();
        if (newUser == null) throw new RuntimeException("Can not create new user.");
        return newUser;
    }
}
