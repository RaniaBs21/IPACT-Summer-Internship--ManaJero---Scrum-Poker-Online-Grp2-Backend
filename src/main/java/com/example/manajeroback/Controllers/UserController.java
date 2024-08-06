package com.example.manajeroback.Controllers;
import com.example.manajeroback.entities.User;
import com.example.manajeroback.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {
    UserService userService;




    @PostMapping("/session/addUser/{sessionId}")
    public User addIssue(@PathVariable String sessionId, @RequestBody User user) {
        return userService.addUser(sessionId, user);
    }

}
