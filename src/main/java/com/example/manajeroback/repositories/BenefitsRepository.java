package com.example.manajeroback.repositories;

import com.example.manajeroback.entities.Benefits;
import com.example.manajeroback.entities.Test;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface BenefitsRepository extends MongoRepository<Benefits, Long> {
}
