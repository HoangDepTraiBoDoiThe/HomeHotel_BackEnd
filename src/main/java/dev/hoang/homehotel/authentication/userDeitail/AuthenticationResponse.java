package dev.hoang.homehotel.authentication.userDeitail;

import dev.hoang.homehotel.role.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String name;
    private String email;
    private List<String> roles;
    private String jwtToken;
}
