package com.example.manajeroback.repositories;

import com.example.manajeroback.entities.Session;
import com.example.manajeroback.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    List<User> findBySession(Session session);

}
