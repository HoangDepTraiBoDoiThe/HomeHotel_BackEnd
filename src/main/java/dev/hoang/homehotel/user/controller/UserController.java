package dev.hoang.homehotel.user.controller;

import dev.hoang.homehotel.user.dto.UserRequest;
import dev.hoang.homehotel.user.dto.UserResponse;
import dev.hoang.homehotel.user.model.User;
import dev.hoang.homehotel.user.serivce.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUser() {
        List<UserResponse>userResponses = userService.GetAllUsers().stream().map(user -> new UserResponse(user.getUserName(), user.getEmail(), user.getRoles())).collect(Collectors.toList());
        return ResponseEntity.ok(userResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String id) {
       User user = userService.getUserById(id);
       UserResponse userResponse = new UserResponse(user);
       return ResponseEntity.ok(userResponse);
    }
}
