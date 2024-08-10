package com.example.manajeroback.Models;
import com.example.manajeroback.entities.Session;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IssuesRequest {
     String description;
     String platformId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String id;
    @DBRef
    Session session;
}
