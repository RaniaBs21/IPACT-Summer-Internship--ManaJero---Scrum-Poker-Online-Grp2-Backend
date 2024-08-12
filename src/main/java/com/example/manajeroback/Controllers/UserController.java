package com.example.manajeroback.Controllers;
import com.example.manajeroback.entities.User;
import com.example.manajeroback.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {
    UserService userService;




    @PostMapping("/session/addUser/{sessionId}")
    public User addIssue(@PathVariable String sessionId, @RequestBody User user) {
        return userService.addUser(sessionId, user);
    }

    @GetMapping("/session/user/{sessionId}")
    public ResponseEntity<List<User>> getUsersBySession(@PathVariable String sessionId) {
        List<User> users = userService.getUsersBySession(sessionId);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/getUserById/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/{sessionId}/users/total")
    public ResponseEntity<Long> countUsersInSession(@PathVariable String sessionId) {
        long userCount = userService.countUsersInSession(sessionId);
        return ResponseEntity.ok(userCount);
    }

}
