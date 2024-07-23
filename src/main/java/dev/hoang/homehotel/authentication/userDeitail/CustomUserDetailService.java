package dev.hoang.homehotel.authentication.userDeitail;

import dev.hoang.homehotel.user.model.User;
import dev.hoang.homehotel.user.serivce.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByEmail(username);
        CustomUserDetail userDetail = new CustomUserDetail(userService);
        userDetail.setEmail(user.getEmail());
        userDetail.setPassword(user.getPassword());
        return userDetail;
    }
}
