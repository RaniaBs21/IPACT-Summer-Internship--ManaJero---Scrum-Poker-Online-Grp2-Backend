package com.example.manajeroback.repositories;

import com.example.manajeroback.entities.Demo;
import com.example.manajeroback.entities.Test;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DemoRepository extends MongoRepository<Demo, Long> {
}
