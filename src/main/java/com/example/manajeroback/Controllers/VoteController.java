package com.example.manajeroback.Controllers;

import com.example.manajeroback.entities.Vote;
import com.example.manajeroback.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/votes")
public class VoteController {

    @Autowired
    private VoteService voteService;

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
    @GetMapping("getaverage")
    public ResponseEntity<Double> getAverageVote(
            @RequestParam String sessionId,
            @RequestParam String issueId) {
        double averageVote = voteService.calculateAverageVote(sessionId, issueId);
        return ResponseEntity.ok(averageVote);
    }
}
