package com.example.manajeroback.Integration;

import com.example.manajeroback.Models.IssuesRequest;
import com.example.manajeroback.entities.Issues;
import com.example.manajeroback.entities.Session;
import com.example.manajeroback.repositories.IssuesRepository;
import com.example.manajeroback.repositories.SessionRepository;
import com.example.manajeroback.services.IssuesServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IssuesServicesTestIntegration {

    @Autowired
    private IssuesServices issuesServices;

    @Autowired
    private IssuesRepository issuesRepository;

    @Autowired
    private SessionRepository sessionRepository;

    private Issues testIssue;
    private Session testSession;

    @BeforeEach
    void setUp() {
        issuesRepository.deleteAll();
        sessionRepository.deleteAll();
        System.out.println("Setup: Les dépôts sont nettoyés.");

        testSession = new Session();
        testSession.setName("Test Session");
        sessionRepository.save(testSession);
        System.out.println("Setup: La session de test a été créée avec l'ID = " + testSession.getId());

        testIssue = new Issues();
        testIssue.setIssueDescription("Test Description");
        testIssue.setSession(testSession);
        System.out.println("Setup: L'issue de test a été créée.");
    }

    @Test
    void addIssue() {
        Issues savedIssue = issuesServices.addIssue(testSession.getId(), testIssue);
        System.out.println("AddIssue: Issue ajoutée avec ID = " + savedIssue.getId());

        assertNotNull(savedIssue.getId(), "L'ID ne devrait pas être nul après l'enregistrement");
        assertEquals(testIssue.getIssueDescription(), savedIssue.getIssueDescription());
        assertEquals(testSession.getId(), savedIssue.getSession().getId());
    }

    @Test
    void getAllIssues() {
        issuesServices.addIssue(testSession.getId(), testIssue);
        System.out.println("GetAllIssues: Issue ajoutée.");

        List<Issues> issuesList = issuesServices.getAllIssues();
        System.out.println("GetAllIssues: Nombre d'issues récupérées = " + issuesList.size());

        assertFalse(issuesList.isEmpty(), "La liste des issues ne devrait pas être vide");
        assertEquals(1, issuesList.size(), "La taille de la liste devrait être 1");
    }

    @Test
    void getIssuesById() {
        Issues savedIssue = issuesServices.addIssue(testSession.getId(), testIssue);
        System.out.println("GetIssuesById: Issue ajoutée avec ID = " + savedIssue.getId());

        Issues foundIssue = issuesServices.getIssuesById(savedIssue.getId());
        System.out.println("GetIssuesById: Issue trouvée avec ID = " + foundIssue.getId());

        assertNotNull(foundIssue, "L'issue ne devrait pas être nulle pour un ID valide");
        assertEquals(savedIssue.getIssueDescription(), foundIssue.getIssueDescription());
    }

    @Test
    void getIssuesBySessionId() {
        issuesServices.addIssue(testSession.getId(), testIssue);
        System.out.println("GetIssuesBySessionId: Issue ajoutée pour la session ID = " + testSession.getId());

        List<Issues> issuesList = issuesServices.getIssuesBySessionId(testSession.getId());
        System.out.println("GetIssuesBySessionId: Nombre d'issues récupérées = " + issuesList.size());

        assertFalse(issuesList.isEmpty(), "La liste des issues ne devrait pas être vide pour une session donnée");
        assertEquals(1, issuesList.size(), "La taille de la liste devrait être 1");
    }

    @Test
    void updateIssues() {
        Issues savedIssue = issuesServices.addIssue(testSession.getId(), testIssue);
        System.out.println("UpdateIssues: Issue ajoutée avec ID = " + savedIssue.getId());

        Issues updatedIssue = new Issues();
        updatedIssue.setIssueDescription("Updated Description");

        Issues result = issuesServices.updateIssues(updatedIssue, savedIssue.getId());
        System.out.println("UpdateIssues: Issue mise à jour avec la nouvelle description = " + result.getIssueDescription());

        assertEquals("Updated Description", result.getIssueDescription(), "La description devrait être mise à jour");
    }

    @Test
    void deleteIssues() {
        Issues savedIssue = issuesServices.addIssue(testSession.getId(), testIssue);
        System.out.println("DeleteIssues: Issue ajoutée avec ID = " + savedIssue.getId());

        issuesServices.deleteIssues(savedIssue.getId());
        System.out.println("DeleteIssues: Issue supprimée avec ID = " + savedIssue.getId());

        Issues deletedIssue = issuesServices.getIssuesById(savedIssue.getId());
        System.out.println("DeleteIssues: Issue après suppression = " + deletedIssue);

        assertNull(deletedIssue, "L'issue devrait être supprimée");
    }

    @Test
    void uploadIssues() throws IOException {
        String csvContent = "name,description\n" +
                "Issue 1,Description 1\n" +
                "Issue 2,Description 2";
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "issues.csv",
                "text/csv",
                csvContent.getBytes()
        );

        System.out.println("UploadIssues: Fichier CSV simulé créé.");
        int issuesCount = issuesServices.uploadIssues(file, testSession.getId());
        System.out.println("UploadIssues: Nombre d'issues importées = " + issuesCount);
        assertEquals(2, issuesCount, "Le nombre d'issues devrait être 2");
        assertEquals(2, issuesRepository.count(), "Le dépôt devrait contenir 2 issues");
    }

    @Test
    void insertIssues1() {
        IssuesRequest issuesRequest = new IssuesRequest();
        issuesRequest.setDescription("Test Description");

        List<IssuesRequest> issuesRequests = List.of(issuesRequest);
        System.out.println("InsertIssues1: Requête d'issue créée.");

        issuesServices.insertIssues1(issuesRequests, testSession.getId());
        System.out.println("InsertIssues1: Issue insérée pour la session ID = " + testSession.getId());

        List<Issues> issuesList = issuesServices.getIssuesBySessionId(testSession.getId());
        System.out.println("InsertIssues1: Nombre d'issues récupérées = " + issuesList.size());

        assertEquals(1, issuesList.size(), "Une issue devrait être insérée");
        assertEquals("Test Description", issuesList.get(0).getIssueDescription());
    }
}
