package com.example.manajeroback.repositories;
import com.example.manajeroback.entities.Issues;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IssuesRepository  extends MongoRepository<Issues, String> {

    List<Issues> findBySessionId(String sessionId);

}
