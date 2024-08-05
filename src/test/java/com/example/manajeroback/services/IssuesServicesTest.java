package com.example.manajeroback.services;

import com.example.manajeroback.entities.Issues;
import com.example.manajeroback.entities.Session;
import com.example.manajeroback.repositories.IssuesRepository;
import com.example.manajeroback.repositories.SessionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class IssuesServiceTest {

    @InjectMocks
    private IssuesServices issuesService;

    @Mock
    private IssuesRepository issuesRepository;

    @Mock
    private SessionRepository sessionRepository;

    @Test
    void addIssue() {
        Issues issue = new Issues();
        issue.setIssueDescription("Test Issue Description");

        Mockito.when(issuesRepository.save(Mockito.any(Issues.class))).thenReturn(issue);

        Issues savedIssue = issuesService.addIssues(issue);

        Assertions.assertEquals("Test Issue Description", savedIssue.getIssueDescription());

        System.out.println("test addIssue validé");
        System.out.println(savedIssue);
    }

    @Test
    void getAllIssuesTest() {
        List<Issues> issuesList = Arrays.asList(
                new Issues("1", "Issue Description 1", "PP-1", null),
                new Issues("2", "Issue Description 2", "PP-2", null)
        );

        Mockito.when(issuesRepository.findAll()).thenReturn(issuesList);

        List<Issues> result = issuesService.getAllIssues();

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("Issue Description 1", result.get(0).getIssueDescription());
        Assertions.assertEquals("Issue Description 2", result.get(1).getIssueDescription());

        System.out.println("Retrieved issues: " + result);
        for (Issues issue : result) {
            System.out.println("Description: " + issue.getIssueDescription());
            System.out.println("Number: " + issue.getIssueNumber());
        }

        Mockito.verify(issuesRepository).findAll();
    }

    @Test
    void getIssueByIdTest() {
        String issueId = "1";
        Issues issue = new Issues("1", "Test Issue Description", "PP-1", null);

        Mockito.when(issuesRepository.findById(issueId)).thenReturn(Optional.of(issue));

        Issues result = issuesService.getIssuesById(issueId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(issueId, result.getId());
        Assertions.assertEquals("Test Issue Description", result.getIssueDescription());

        System.out.println("Retrieved Issue: " + result);
        System.out.println("Description: " + result.getIssueDescription());
        System.out.println("Number: " + result.getIssueNumber());

        Mockito.verify(issuesRepository).findById(issueId);
    }

    @Test
    void updateIssue() {
        String id = "1";
        Issues existingIssue = new Issues("1", "Old Description", "PP-1", null);
        Issues updatedIssue = new Issues("1", "New Description", "PP-1", null);

        Mockito.when(issuesRepository.findById(id)).thenReturn(Optional.of(existingIssue));
        Mockito.when(issuesRepository.save(existingIssue)).thenReturn(existingIssue);

        Issues result = issuesService.updateIssues(updatedIssue, id);

        Assertions.assertEquals("New Description", result.getIssueDescription());

        Mockito.verify(issuesRepository).findById(id);
        Mockito.verify(issuesRepository).save(existingIssue);

        System.out.println("test updateIssue validé");
        System.out.println("Updated Issue: " + result);
        System.out.println("Description: " + result.getIssueDescription());
        System.out.println("Number: " + result.getIssueNumber());
    }

    @Test
    void deleteIssue() {
        String id = "1";

        issuesService.deleteIssues(id);

        Mockito.verify(issuesRepository).deleteById(id);

        System.out.println("test deleteIssue validé");
        System.out.println("Deleted Issue with ID: " + id);
    }

    @Test
    void addIssueWithSession() {
        String sessionId = "session1";
        Issues issue = new Issues();
        issue.setIssueDescription("Test Issue Description");

        Session session = new Session(); // Créer un mock ou une instance valide
        Mockito.when(sessionRepository.findById(sessionId)).thenReturn(Optional.of(session));
        Mockito.when(issuesRepository.countBySessionId(sessionId)).thenReturn(1L);
        Mockito.when(issuesRepository.save(Mockito.any(Issues.class))).thenReturn(issue);

        Issues savedIssue = issuesService.addIssue(sessionId, issue);

        Assertions.assertNotNull(savedIssue.getIssueNumber());
        Assertions.assertEquals("PP-2", savedIssue.getIssueNumber());

        System.out.println("test addIssueWithSession validé");
        System.out.println(savedIssue);
    }
}
