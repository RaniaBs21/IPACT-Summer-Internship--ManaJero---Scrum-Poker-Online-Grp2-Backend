package com.example.manajeroback.repositories;

import com.example.manajeroback.entities.Demo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoRepository extends MongoRepository<Demo, Long> {
}
