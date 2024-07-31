package com.example.manajeroback.services;

import com.example.manajeroback.entities.Vote;
import com.example.manajeroback.repositories.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
