package com.example.manajeroback.Controllers;

import com.example.manajeroback.entities.User;
import com.example.manajeroback.entities.Vote;
import com.example.manajeroback.repositories.IssuesRepository;
import com.example.manajeroback.repositories.UserRepository;
import com.example.manajeroback.repositories.VoteRepository;
import com.example.manajeroback.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/votes")
public class VoteController {

    @Autowired
    private VoteService voteService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private IssuesRepository issuesRepository;

    @PostMapping
    public ResponseEntity<Vote> addVote(@RequestBody Vote vote) {
        try {
            Vote savedVote = voteService.addVote(vote);
            return ResponseEntity.ok(savedVote);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    @GetMapping("/session/{sessionId}/issue/{issueId}")
    public ResponseEntity<List<Vote>> getVotesBySessionAndIssue(@PathVariable String sessionId, @PathVariable String issueId) {
        List<Vote> votes = voteService.getVotesBySessionAndIssue(sessionId, issueId);
        return ResponseEntity.ok(votes);
    }
    @GetMapping("/getaverage")
    public ResponseEntity<Double> getAverageVote(
            @RequestParam String sessionId,
            @RequestParam String issueId) {
        double averageVote = voteService.calculateAverageVote(sessionId, issueId);
        return ResponseEntity.ok(averageVote);
    }
    @PostMapping("/session/submitVote")
    public Vote submitVote(@RequestBody Vote vote) {
        User user = userRepository.findById(vote.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Vote newVote = new Vote();
        newVote.setSessionId(vote.getSessionId());
        newVote.setIssueId(vote.getIssueId());
        newVote.setVote(vote.getVote());
        newVote.setUserId(user.getId());
        newVote.setUserName(user.getName());
        return voteRepository.save(newVote);
    }
    @GetMapping("/users-in-session/{sessionId}")
    public long getNumberOfUsersInSession(@PathVariable String sessionId) {
        return voteService.getNumberOfUsersInSession(sessionId);
    }

    @GetMapping("/player-performance/{sessionId}")
    public Map<String, Long> getPlayerPerformance(@PathVariable String sessionId) {
        return voteService.getPlayerPerformance(sessionId);
    }

    @GetMapping("/estimation-classification/{sessionId}")
    public Map<String, Long> getEstimationClassification(@PathVariable String sessionId) {
        return voteService.getEstimationClassification(sessionId);
    }
    @GetMapping("/api/statistics/{sessionId}")
    public Map<String, Object> getStatistics(@PathVariable String sessionId) {
        long numberOfUsers = voteService.countUsersBySessionId(sessionId);
        Map<String, Long> playerPerformance = voteService.getPlayerPerformance(sessionId);
        Map<String, Long> estimationClassification = voteService.getEstimationClassification(sessionId);
        return Map.of(
                "numberOfUsers", numberOfUsers,
                "playerPerformance", playerPerformance,
                "estimationClassification", estimationClassification
        );
    }
    @GetMapping("/api/session/{sessionId}/userCount")
    public long getUserCount(@PathVariable String sessionId) {
        return voteService.countUsersBySessionId(sessionId);
    }
}
