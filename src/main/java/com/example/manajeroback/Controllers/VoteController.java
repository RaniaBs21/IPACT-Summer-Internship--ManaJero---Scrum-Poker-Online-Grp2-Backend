package com.example.manajeroback.Controllers;

import com.example.manajeroback.entities.Vote;
import com.example.manajeroback.services.VoteService;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
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
        Vote savedVote = voteService.addVote(vote);
        return ResponseEntity.ok(savedVote);
    }

    @GetMapping("/session/{sessionId}/issue/{issueId}")
    public ResponseEntity<List<Vote>> getVotesBySessionAndIssue(@PathVariable String sessionId, @PathVariable String issueId) {
        List<Vote> votes = voteService.getVotesBySessionAndIssue(sessionId, issueId);
        return ResponseEntity.ok(votes);
    }


}
