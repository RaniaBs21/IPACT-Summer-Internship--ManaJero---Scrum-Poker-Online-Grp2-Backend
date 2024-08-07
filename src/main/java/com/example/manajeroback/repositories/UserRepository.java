package com.example.manajeroback.repositories;

import com.example.manajeroback.entities.Session;
import com.example.manajeroback.entities.User;
import com.example.manajeroback.entities.Vote;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    List<User> findBySession(Session session);
}
