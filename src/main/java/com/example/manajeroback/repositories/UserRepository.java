package com.example.manajeroback.repositories;

import com.example.manajeroback.entities.User;
import com.example.manajeroback.entities.Vote;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
