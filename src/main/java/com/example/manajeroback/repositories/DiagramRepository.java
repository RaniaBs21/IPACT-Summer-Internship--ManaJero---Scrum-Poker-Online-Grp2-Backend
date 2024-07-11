package com.example.manajeroback.repositories;

import com.example.manajeroback.entities.Benefits;
import com.example.manajeroback.entities.Diagram;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagramRepository extends MongoRepository<Diagram, String> {
}
