package com.example.manajeroback.services;

import com.example.manajeroback.ManajeroBackApplication;
import com.example.manajeroback.entities.Session;
import com.example.manajeroback.entities.VotingSystem;
import com.example.manajeroback.repositories.SessionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = ManajeroBackApplication.class)

@RunWith(MockitoJUnitRunner.class)
class SessionServiceTest {
    @Mock
    private SessionRepository sessionRepository;

//    @Mock
//    private ModelMapper mapper;

    @InjectMocks
    private SessionService sessionService;

    @Test
    public void addSession() {
        Session session = new Session("Session 2", VotingSystem.FIBONACCI);
        session.setName("test");
        session.setVotingSystem(VotingSystem.FIBONACCI);
        session.setId("test");
        Mockito.when(sessionRepository.save(Mockito.any(Session.class))).thenReturn(session);
        Session savedSession = sessionService.addSession(session);
        Assertions.assertEquals("test", savedSession.getName());
        Assertions.assertEquals(VotingSystem.FIBONACCI, savedSession.getVotingSystem());
        Assertions.assertEquals("test", savedSession.getId());

        System.out.println("test validé");
        System.out.println(session);

    }
    @Test
    void getAllSessions() {
        List<Session> arraySession = Arrays.asList(
                new Session("Session 1", VotingSystem.FIBONACCI),
                new Session("Session 2", VotingSystem.FIBONACCI)
        );

        when(sessionRepository.findAll()).thenReturn(arraySession);
        List<Session> result = sessionService.getAllSessions();
        assertEquals(2, result.size());
        assertEquals("Session 1", result.get(0).getName());
        assertEquals("Session 2", result.get(1).getName());
        verify(sessionRepository, times(1)).findAll();
        System.out.println("Retrieved session: " + result);
        for (Session session: result) {
            System.out.println("Title: " + session.getName());
            System.out.println("Description: " + session.getVotingSystem());
        }Mockito.verify (sessionRepository).findAll();
    }

    @Test
    void getSessionById() {
        String sessionID = "66a95b2e5d40af76e69cabd9";
        Session session = new Session("Session 3", VotingSystem.FIBONACCI);
        session.setId(sessionID);
        Mockito.when(sessionRepository.findById(sessionID)).thenReturn(Optional.of(session));
        Session result = sessionService.getSessionById(sessionID);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(sessionID, result.getId());
        Assertions.assertEquals("Session 3", result.getName());
        Assertions.assertEquals(VotingSystem.FIBONACCI, result.getVotingSystem());
        System.out.println("Retrieved session: " + result);
        System.out.println("Name: " + result.getName());
        System.out.println("Voting System: " + result.getVotingSystem());

        Mockito.verify(sessionRepository).findById(sessionID);
    }


    @Test
    void deleteSession() {
        sessionService.deleteSession("1");
        verify(sessionRepository, times(1)).deleteById("1");
        System.out.println("session deleted");
    }
    @Test
    void updateSessionTest() {
        String id = "668eaf72261f395a90fd4747";
        Session existingSession = new Session("Session 2", VotingSystem.FIBONACCI);
        existingSession.setName("existing name");
        existingSession.setVotingSystem(VotingSystem.FIBONACCI);
        Session updatedSession = new Session("Session 2", VotingSystem.FIBONACCI);
        updatedSession.setName("new name");
        updatedSession.setVotingSystem(VotingSystem.TSHIRTS);
        Mockito.when(sessionRepository.findById(id)).thenReturn(Optional.of(existingSession));
        Mockito.when(sessionRepository.save(existingSession)).thenReturn(existingSession);
        Session result = sessionService.updateSession(updatedSession, id);
        Assertions.assertEquals("new name", result.getName());
        Assertions.assertEquals(VotingSystem.TSHIRTS, result.getVotingSystem());
        Mockito.verify(sessionRepository, Mockito.times(1)).findById(id);
        Mockito.verify(sessionRepository, Mockito.times(1)).save(existingSession);
        System.out.println("Updated Demo: " + result);
        System.out.println("Name: " + result.getName());
        System.out.println("VotingSystem: " + result.getVotingSystem());

    }

}
