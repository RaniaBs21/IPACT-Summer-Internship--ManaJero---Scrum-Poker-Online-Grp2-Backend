package com.example.manajeroback.repositories;

import com.example.manajeroback.entities.Session;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository  extends MongoRepository<Session, String> {
    // Méthode personnalisée pour compter les utilisateurs dans une session
}
