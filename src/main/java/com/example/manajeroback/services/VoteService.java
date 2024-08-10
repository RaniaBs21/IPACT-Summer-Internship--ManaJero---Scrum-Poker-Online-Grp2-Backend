package com.example.manajeroback.services;

import com.example.manajeroback.entities.Vote;
import com.example.manajeroback.repositories.VoteRepository;
import com.mongodb.DBObject;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VoteService {


    private VoteRepository voteRepository;

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
            case "0":
                return 0;
            case "S":
            case "½":
                return 0.5;
            case "M":
            case "1":
                return 1;
            case "L":
            case "2":
                return 2;
            case "XL":
            case "3":
                return 3;
            case "8":
                return 8;
            case "13":
                return 13;
            case "20":
                return 20;
            case "34":
            case "40":
                return 40;
            case "55":
            case "100":
                return 100;
            case "89":
                return 89;
            default:
                return 0; // Default to 0 if vote is unrecognized
        }
    }
}
