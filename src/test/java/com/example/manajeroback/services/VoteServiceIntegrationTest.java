package com.example.manajeroback.services;

import com.example.manajeroback.entities.Steps;
import com.example.manajeroback.entities.Vote;
import com.example.manajeroback.repositories.VoteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
class VoteServiceIntegrationTest {

    @Autowired
    private VoteService voteService;

    @Autowired
    private VoteRepository voteRepository;

    @Test
    void addVote() {
        Vote vote = new Vote("session1", "user1", "issue1", "5");
        Vote savedVote = voteService.addVote(vote);

        Assertions.assertNotNull(savedVote.getId());
        Assertions.assertEquals("session1", savedVote.getSessionId());

        System.out.println("Vote added");
    }

    @Test
    void getVotesBySessionAndIssue() {
        // Ajouter des votes pour tester
        Vote vote1 = new Vote("session1", "user1", "issue1", "5");
        Vote vote2 = new Vote("session1", "user1", "issue1", "3");
        voteService.addVote(vote1);
        voteService.addVote(vote2);

        List<Vote> votes = voteService.getVotesBySessionAndIssue("session1", "issue1");

        assertEquals(2, votes.size());
        assertTrue(votes.stream().anyMatch(vote -> "5".equals(vote.getVote())));
        assertTrue(votes.stream().anyMatch(vote -> "3".equals(vote.getVote())));

        System.out.println("test passed");

    }

    @Test
    void calculateAverageVote() {
        // Ajouter des votes pour tester
        Vote vote1 = new Vote("session1", "user1", "issue1", "5");
        Vote vote2 = new Vote("session1", "user1", "issue1", "3");
        voteService.addVote(vote1);
        voteService.addVote(vote2);

        double averageVote = voteService.calculateAverageVote("session1", "issue1");

        assertEquals(4.0, averageVote);
        System.out.println("average = "+ averageVote);
    }

    @Test
    void getVoteCountByIssueId() {
        // Ajouter des votes pour tester
        Vote vote1 = new Vote("session1", "user1", "issue1", "5");
        Vote vote2 = new Vote("session1", "user1", "issue1", "3");
        voteService.addVote(vote1);
        voteService.addVote(vote2);

        Long voteCount = voteService.getVoteCountByIssueId("issue1");

        assertEquals(2, voteCount);
        System.out.println("voteCount = "+ voteCount);

    }

    @Test
    void calculateVoteFrequencyForSession() {
        // Ajouter des votes pour tester
        Vote vote1 = new Vote("session1", "user1", "issue1", "5");
        Vote vote2 = new Vote("session1", "user1", "issue1", "3");
        Vote vote3 = new Vote("session1", "user1", "issue1", "3");
        voteService.addVote(vote1);
        voteService.addVote(vote2);
        voteService.addVote(vote3);

        Map<String, Integer> frequencyMap = voteService.calculateVoteFrequencyForSession("session1");

        assertEquals(2, frequencyMap.get("3"));
        assertEquals(1, frequencyMap.get("5"));

        System.out.println("frequency :" + frequencyMap);
    }

    @Test
    void getVoteDistribution() {
        // Ajouter des votes pour tester
        Vote vote1 = new Vote("session1", "user1", "issue1", "5");
        Vote vote2 = new Vote("session1", "user1", "issue1", "3");
        Vote vote3 = new Vote("session1", "user1", "issue1", "3");
        voteService.addVote(vote1);
        voteService.addVote(vote2);
        voteService.addVote(vote3);

        Map<String, Object> distributionMap = voteService.getVoteDistribution("session1");

        Map<String, Object> vote3Details = (Map<String, Object>) distributionMap.get("3");
        Map<String, Object> vote5Details = (Map<String, Object>) distributionMap.get("5");

        assertNotNull(vote3Details);
        assertEquals(2, vote3Details.get("count"));
        assertEquals(66.66666666666666, vote3Details.get("percentage"));

        assertNotNull(vote5Details);
        assertEquals(1, vote5Details.get("count"));
        assertEquals(33.33333333333333, vote5Details.get("percentage"));
        System.out.println("distribution :" + distributionMap);

    }

}
