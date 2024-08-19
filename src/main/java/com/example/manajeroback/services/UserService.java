package com.example.manajeroback.services;

import com.example.manajeroback.entities.Session;
import com.example.manajeroback.entities.User;
import com.example.manajeroback.repositories.SessionRepository;
import com.example.manajeroback.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    SessionRepository sessionRepository;
    UserRepository userRepository;
    public User addUser(String sessionId, User user) {
        Session session = sessionRepository.findById(sessionId).orElseThrow(() -> new IllegalArgumentException("Invalid session ID"));
        user.setSession(session);
        return userRepository.save(user);
    }

    public List<User> getUsersBySession(String sessionId) {
        Optional<Session> sessionOptional = sessionRepository.findById(sessionId);
        if (sessionOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid session ID");
        }
        Session session = sessionOptional.get();
        return userRepository.findBySession(session);
    }
    public User getUserById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }
}
