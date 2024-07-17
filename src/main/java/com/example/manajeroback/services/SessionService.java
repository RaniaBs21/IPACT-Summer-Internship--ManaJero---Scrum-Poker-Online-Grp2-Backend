package com.example.manajeroback.services;
import com.example.manajeroback.entities.Session;
import com.example.manajeroback.repositories.SessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class SessionService {
    SessionRepository sessionRepository ;

    public Session addSession(Session session) {
        return sessionRepository.save(session);
    }

    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    public Session getSessionById(String id) {
        return sessionRepository.findById(id).orElse(null);
    }
    public void deleteSession(String id) {
        sessionRepository.deleteById(id);
    }

}
