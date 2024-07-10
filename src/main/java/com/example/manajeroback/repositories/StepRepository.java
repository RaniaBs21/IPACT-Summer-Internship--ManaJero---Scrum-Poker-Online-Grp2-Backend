package com.example.manajeroback.repositories;

import com.example.manajeroback.entities.Limits;
import com.example.manajeroback.entities.Steps;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StepRepository extends MongoRepository<Steps, String> {
}
