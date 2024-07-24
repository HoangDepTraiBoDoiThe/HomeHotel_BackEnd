package dev.hoang.homehotel.user.dto;

import dev.hoang.homehotel.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String name;
    private String password;
    private String email;

    public User toUser(PasswordEncoder passwordEncoder) {
        User newUser = new User(this.name, this.email);
        newUser.setPassword(passwordEncoder.encode(this.getPassword()));
        return newUser;
    }
}
