package com.example.manajeroback.repositories;

import com.example.manajeroback.entities.Vote;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends MongoRepository<Vote, String> {
    List<Vote> findBySessionIdAndIssueId(String sessionId, String issueId);

    long countBySessionId(String sessionId);
    List<Vote> findBySessionId(String sessionId);
}
