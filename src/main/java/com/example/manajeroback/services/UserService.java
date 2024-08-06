package com.example.manajeroback.services;


import com.example.manajeroback.entities.Session;
import com.example.manajeroback.entities.User;
import com.example.manajeroback.repositories.SessionRepository;
import com.example.manajeroback.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    UserRepository userRepository;
    SessionRepository sessionRepo;

    public User addUser(String sessionId, User user) {
        Session session = sessionRepo.findById(sessionId).orElseThrow(() -> new IllegalArgumentException("Invalid session ID"));
        user.setSession(session);
        return userRepository.save(user);
    }
}
