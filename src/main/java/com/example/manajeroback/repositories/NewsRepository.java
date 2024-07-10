package com.example.manajeroback.repositories;

import com.example.manajeroback.entities.News;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends MongoRepository<News, String> {
}
