package com.example.manajeroback.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "Issues")

public class Issues {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String id;
    private String name;
    private String description;
    private String sessionId;

}
