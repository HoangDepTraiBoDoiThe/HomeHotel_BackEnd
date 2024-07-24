package dev.hoang.homehotel.authentication.userDeitail;

import dev.hoang.homehotel.user.exception.UserException;
import dev.hoang.homehotel.user.model.User;
import dev.hoang.homehotel.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(username).orElseThrow(() -> new UserException("Can not find user"));
        CustomUserDetail userDetail = new CustomUserDetail();
        userDetail.setEmail(user.getEmail());
        userDetail.setPassword(user.getPassword());
        userDetail.setAuthorities(user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList()));
        return userDetail;
    }
}
