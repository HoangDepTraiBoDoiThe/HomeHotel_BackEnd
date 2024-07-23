package dev.hoang.homehotel.authentication.userDeitail;

import dev.hoang.homehotel.user.serivce.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@AllArgsConstructor
@Setter
public class CustomUserDetail implements UserDetails {
    private final UserService userService;
    private Long id;
    private  String email;
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userService.getUserByEmail(email).getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
