package com.example.manajeroback.repositories;

import com.example.manajeroback.entities.Issues;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IssuesRepository extends MongoRepository<Issues, String> {
    List<Issues> findBySessionId(String sessionId);

    // Méthode pour compter les issues par session ID

    long countBySessionId(String sessionId);
}
