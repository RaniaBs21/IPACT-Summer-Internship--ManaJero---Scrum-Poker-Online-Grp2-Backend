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
            try {
                double voteValue = Double.parseDouble(vote.getVote());
                sum += voteValue;
                count++;
            } catch (NumberFormatException e) {
                // Handle the case where the vote value is not a number
                // For example, you might log this or ignore this vote
                System.err.println("Invalid vote value: " + vote.getVote());
            }
        }

        return count == 0 ? 0.0 : sum / count;
    }

}
