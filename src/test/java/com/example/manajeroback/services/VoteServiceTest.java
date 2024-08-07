package com.example.manajeroback.services;

import com.example.manajeroback.entities.Vote;
import com.example.manajeroback.repositories.VoteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class VoteServiceTest {

    @InjectMocks
    private VoteService voteService ;
    @Mock
    private VoteRepository voteRepository ;

    @Test
    void addVoteTest() {
        Vote vote = new Vote();
        vote.setVote("xl");
        // Configurer le comportement du mock
        Mockito.when(voteRepository.save(Mockito.any(Vote.class))).thenReturn(vote);
        Vote savedVote = voteService.addVote(vote);
        Assertions.assertEquals("xl", vote.getVote());

        System.out.println("test validé");
        System.out.println(vote.getVote());
    }

    @Test
    void getVotesBySessionAndIssueTest() {
        String sessionId = "session1";
        String issueId = "issue1";

        List<Vote> votes = new ArrayList<>();
        votes.add(new Vote("1", sessionId, issueId, "5"));
        votes.add(new Vote("2", sessionId, issueId, "3"));

        Mockito.when(voteRepository.findBySessionIdAndIssueId(sessionId, issueId)).thenReturn(votes);

        List<Vote> result = voteService.getVotesBySessionAndIssue(sessionId, issueId);

        assertEquals(2, result.size());
        Mockito.verify(voteRepository).findBySessionIdAndIssueId(sessionId, issueId);

        // Afficher les votes ajoutés
        System.out.println("Votes ajoutés:");
        for (Vote vote : result) {
            System.out.println( " Session ID: " + vote.getSessionId() +
                    ", Issue ID: " + vote.getIssueId() + ", Vote: " + vote.getVote());
        }
        System.out.println("Test getVotesBySessionAndIssueTest passed successfully.");
    }
}
