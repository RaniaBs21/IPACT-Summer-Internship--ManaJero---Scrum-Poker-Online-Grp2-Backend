package com.example.manajeroback.repositories;

import com.example.manajeroback.entities.Limits;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LimitsRepository  extends MongoRepository<Limits, String> {
}
