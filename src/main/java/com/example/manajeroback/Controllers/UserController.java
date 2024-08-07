package com.example.manajeroback.Controllers;

import com.example.manajeroback.entities.Session;
import com.example.manajeroback.entities.User;
import com.example.manajeroback.repositories.SessionRepository;
import com.example.manajeroback.repositories.UserRepository;
import com.example.manajeroback.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class UserController {
    UserService userService;
    SessionRepository sessionRepository;
    UserRepository userRepository;
    @PostMapping("/adduser/{sessionId}")
    public ResponseEntity<User> addUser(@PathVariable String sessionId, @RequestBody User user) {
        User savedUser = userService.addUserToSession(sessionId, user);
        return ResponseEntity.ok(savedUser);
    }
    @GetMapping("/session/user/{sessionId}")
    public ResponseEntity<List<User>> getUsersBySession(@PathVariable String sessionId) {
        List<User> users = userService.getUsersBySession(sessionId);
        return ResponseEntity.ok(users);
    }
}
