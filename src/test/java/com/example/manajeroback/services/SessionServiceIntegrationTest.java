package com.example.manajeroback.services;

import com.example.manajeroback.entities.Session;
import com.example.manajeroback.entities.VotingSystem;
import com.example.manajeroback.repositories.SessionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
class SessionServiceIntegrationTest {

    @Autowired
    private SessionService sessionService;
    @Autowired
    private SessionRepository sessionRepository;
    @Test
    void addSession() {
        Session session = new Session( "Integration Test Title", VotingSystem.FIBONACCI);
        Session savedSession = sessionService.addSession(session);

        Assertions.assertNotNull(savedSession.getId());
        Assertions.assertEquals("Integration Test Title", savedSession.getName());

        System.out.println("Session added");
    }

    @Test
    void getAllSessions() {
        // Récupérer le nombre initial de sessions dans la base de données
        int initialSessionCount = sessionService.getAllSessions().size();

        // Ajouter quelques sessions pour tester
        Session session1 = new Session("Session 1", VotingSystem.FIBONACCI);
        Session session2 = new Session("Session 2", VotingSystem.TSHIRTS);
        sessionRepository.save(session1);
        sessionRepository.save(session2);

        // Appeler la méthode pour récupérer toutes les sessions
        List<Session> sessions = sessionService.getAllSessions();

        // Vérifier si le nombre de sessions a augmenté de 2
        Assertions.assertNotNull(sessions);
        Assertions.assertEquals(initialSessionCount + 2, sessions.size());
        Assertions.assertTrue(sessions.stream().anyMatch(session -> "Session 1".equals(session.getName())));
        Assertions.assertTrue(sessions.stream().anyMatch(session -> "Session 2".equals(session.getName())));

        System.out.println("All sessions retrieved successfully, including the newly added ones.");
    }


    @Test
    void getSessionById() {
        Session session = new Session("Session 1", VotingSystem.FIBONACCI);
        Session savedSession = sessionService.addSession(session);

        Session foundSession = sessionService.getSessionById(savedSession.getId());
        org.assertj.core.api.Assertions.assertThat(foundSession).isNotNull();
        org.assertj.core.api.Assertions.assertThat(foundSession.getName()).isEqualTo("Session 1");
        // Afficher les résultats dans la console
        System.out.println("Name: " + savedSession.getName());
        System.out.println("Voting System: " + savedSession.getVotingSystem());
    }

    @Test
    void deleteSession() {
        Session session = new Session("Session update", VotingSystem.FIBONACCI);
        Session savedSession = sessionService.addSession(session);

        sessionService.deleteSession(savedSession.getId());

        Optional<Session> deletedSession = sessionRepository.findById(savedSession.getId());
        Assertions.assertTrue(deletedSession.isEmpty());
        System.out.println("session deleted successfuly");
    }

    @Test
    void updateSession() {
        Session session = new Session("Session update", VotingSystem.FIBONACCI);
        Session savedSession = sessionService.addSession(session);

        Session updatedSession = new Session("Updated name", VotingSystem.TSHIRTS);
        Session result = sessionService.updateSession(updatedSession, savedSession.getId());

        Assertions.assertEquals("Updated name", result.getName());
        Assertions.assertEquals(VotingSystem.TSHIRTS, result.getVotingSystem());
        System.out.println("Session updated");
    }
}
