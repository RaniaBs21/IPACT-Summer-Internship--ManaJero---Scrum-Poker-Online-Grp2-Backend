package com.example.manajeroback.repositories;

import com.example.manajeroback.entities.Issues;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssuesRepository extends MongoRepository<Issues, String> {
    List<Issues> findBySessionId(String sessionId);
    long countBySessionId(String sessionId);
}
