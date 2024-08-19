package com.example.manajeroback.services;

import com.example.manajeroback.entities.Session;
import com.example.manajeroback.entities.User;
import com.example.manajeroback.repositories.SessionRepository;
import com.example.manajeroback.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SessionService {
    private static final Logger logger = LoggerFactory.getLogger(SessionService.class);

    SessionRepository sessionRepository ;
  UserRepository userRepository;
  EmailService emailService;
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
    public Session updateSession(Session session, String id) {
        Session existingSession = sessionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity with id " + id + " not found"));
        existingSession.setName(session.getName());
        existingSession.setVotingSystem(session.getVotingSystem());
        return sessionRepository.save(existingSession);
    }
// User Invitation
public void inviteUserToSession(String sessionId, String userEmail) {
    // Fetch session from the repository
    Session session = sessionRepository.findById(sessionId)
            .orElseThrow(() -> new IllegalArgumentException("Session not found"));

    // Fetch user details from the repository
    User userDetails = userRepository.findByEmail(userEmail);

    if (userDetails == null) {
        // If user is not found, you might need to create a new user
        userDetails = new User();
        userDetails.setEmail(userEmail);
        userDetails.setName(extractNameFromEmail(userEmail)); // Extract name from email or handle appropriately
    }

    // Associate user with the session
    userDetails.setSession(session);
    userRepository.save(userDetails);

    // Prepare and send email invitation
    String invitationLink = "http://localhost:4200/pages/agile/scrum-poker-group2/session/room/" + sessionId;
    String emailBody = "Bonjour " + userDetails.getName() + ",\n\nVous êtes invité à participer à la session de poker planning : "
            + session.getName() + ".\nCliquez sur le lien pour rejoindre la session : "
            + invitationLink;

    emailService.sendEmail(userEmail, emailBody, "Invitation à la session " + session.getName());

    logger.info("User {} invited to session {}", userEmail, session.getName());
}
    private String extractNameFromEmail(String email) {
        String[] parts = email.split("@");
        String namePart = parts[0];
        // Convert namePart to a readable format if necessary
        return namePart;
    }
    public void closeSession(String sessionId) {
        // Récupérer la session
        Optional<Session> optionalSession = sessionRepository.findById(sessionId);
        if (optionalSession.isPresent()) {
            Session session = optionalSession.get();
            // Mettre à jour l'état de la session pour la marquer comme fermée
            session.setClosed(true);
            sessionRepository.save(session);
        } else {
            throw new RuntimeException("Session not found");
        }
    }

    // Méthode pour vérifier si une session est fermée
    public boolean isSessionClosed(String sessionId) {
        Optional<Session> optionalSession = sessionRepository.findById(sessionId);
        return optionalSession.map(Session::isClosed).orElse(true); // Retourne true si la session n'existe pas ou est fermée
    }



}
