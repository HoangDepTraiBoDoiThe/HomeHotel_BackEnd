package dev.hoang.homehotel.authentication;

import dev.hoang.homehotel.authentication.userDeitail.AuthenticationRequest;
import dev.hoang.homehotel.authentication.userDeitail.AuthenticationResponse;
import dev.hoang.homehotel.authentication.userDeitail.CustomUserDetail;
import dev.hoang.homehotel.authentication.userDeitail.CustomUserDetailService;
import dev.hoang.homehotel.role.model.Role;
import dev.hoang.homehotel.user.dto.UserRequest;
import dev.hoang.homehotel.user.dto.UserResponse;
import dev.hoang.homehotel.user.model.User;
import dev.hoang.homehotel.user.serivce.UserService;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final AuthUtils authUtils;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRequest userRequest) {
        User newUser = userService.createNewUser(userRequest);
        return ResponseEntity.ok(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> userLogin(@RequestBody AuthenticationRequest authenticationRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetail userDetail = (CustomUserDetail) authentication.getPrincipal();

        String userEmail = (userDetail.getUsername());
        String jwtToken = authUtils.generateToken(userEmail);

        List<String> roles = userDetail.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        return ResponseEntity.ok(new AuthenticationResponse(userDetail.getUsername(), userEmail, roles, jwtToken));
    }
}
