package com.example.manajeroback.services;

import com.example.manajeroback.entities.Issues;
import com.example.manajeroback.entities.Session;
import com.example.manajeroback.repositories.IssuesRepository;
import com.example.manajeroback.repositories.SessionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IssuesServices {

    IssuesRepository issuesRepo;
    SessionRepository sessionRepo;
    public Issues addIssues(Issues issues) {
        return issuesRepo.save(issues);
    }

    public List<Issues> getAllIssues() {
        return issuesRepo.findAll();
    }

    public Issues getIssueById(String id) {
        return issuesRepo.findById(id).orElse(null);
    }

    public Issues updateIssues (Issues issues, String id) {
        Issues existingIssue = issuesRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity with id " + id + " not found"));
        existingIssue.setIssueDescription(issues.getIssueDescription());
        return issuesRepo.save(existingIssue);
    }
    public void deleteIssues(String id) {
        issuesRepo.deleteById(id);
    }
    public List<Issues> getIssuesBySessionId(String sessionId) {
        return issuesRepo.findBySessionId(sessionId);
    }
    public Issues addIssue(String sessionId, Issues issue) {
        Session session = sessionRepo.findById(sessionId).orElseThrow(() -> new IllegalArgumentException("Invalid session ID"));
        issue.setSession(session);
        return issuesRepo.save(issue);
    }
}
