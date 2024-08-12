package com.example.manajeroback.Controllers;

import com.example.manajeroback.entities.User;
import com.example.manajeroback.entities.Vote;
import com.example.manajeroback.repositories.UserRepository;
import com.example.manajeroback.repositories.VoteRepository;
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
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VoteRepository voteRepository;

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

   @GetMapping("getaverage")
    public ResponseEntity<Double> getAverageVote(
            @RequestParam String sessionId,
            @RequestParam String issueId) {
        double averageVote = voteService.calculateAverageVote(sessionId, issueId);
        return ResponseEntity.ok(averageVote);
    }

 /*@GetMapping("getaverage")
 public ResponseEntity<?> calculateAverageVote(
         @RequestParam String sessionId,
         @RequestParam String issueId) {
     Object result = voteService.calculateAverageVote(sessionId, issueId);
     return ResponseEntity.ok(result);
 }*/


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

        return  voteRepository.save(newVote);
    }


}
