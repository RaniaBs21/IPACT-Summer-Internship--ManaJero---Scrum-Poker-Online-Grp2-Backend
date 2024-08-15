package com.example.manajeroback.services;

import com.example.manajeroback.entities.Issues;
import com.example.manajeroback.entities.Vote;
import com.example.manajeroback.repositories.IssuesRepository;
import com.example.manajeroback.repositories.VoteRepository;
import com.mongodb.DBObject;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VoteService {


    private VoteRepository voteRepository;
    private IssuesRepository issuesRepository;

    public Vote addVote(Vote vote) {
        return voteRepository.save(vote);
    }

    public List<Vote> getVotesBySessionAndIssue(String sessionId, String issueId) {
        return voteRepository.findBySessionIdAndIssueId(sessionId, issueId);
    }

    public double calculateAverageVote(String sessionId, String issueId) {
        List<Vote> votes = voteRepository.findBySessionIdAndIssueId(sessionId, issueId);
        if (votes.isEmpty()) {
            return 0.0;
        }

        double sum = 0.0;
        int count = 0;

        for (Vote vote : votes) {
            String voteValueStr = vote.getVote();

            if (voteValueStr.equals("☕️") || voteValueStr.equals("☕")) {
                continue; // Ignore coffee votes
            }

            double voteValue;
            try {
                // Try to parse numeric value
                voteValue = Double.parseDouble(voteValueStr);
            } catch (NumberFormatException e) {
                // If parsing fails, convert alphabetical votes to a numeric value
                voteValue = convertAlphaVoteToNumeric(voteValueStr);
            }

            sum += voteValue;
            count++;
        }

        return count == 0 ? 0.0 : sum / count;
    }

    private double convertAlphaVoteToNumeric(String vote) {
        switch (vote.toUpperCase()) {
            case "XS":
            case "1":
                return 1;
            case "S":
            case "2":
                return 2;
            case "M":
            case "3":
                return 3;
            case "L":
            case "4":
                return 4;
            case "XL":
            case "5":
                return 5;
            default:
                return 0; // Default to 0 if vote is unrecognized
        }

    }

    public Long getVoteCountByIssueId(String issueId) {
        List<Vote> votes = voteRepository.findByIssueId(issueId);
        return (long) votes.size();
    }
    public List<Vote> getVotesByIssueId(String issueId) {
        return voteRepository.findByIssueId(issueId);
    }

    public Map<String, Integer> getCardUsageStatistics(String sessionId) {
        List<Vote> votes = voteRepository.findBySessionId(sessionId);
        Map<String, Integer> cardUsage = new HashMap<>();

        for (Vote vote : votes) {
            cardUsage.put(vote.getVote(), cardUsage.getOrDefault(vote.getVote(), 0) + 1);
        }

        return cardUsage;
    }
}
