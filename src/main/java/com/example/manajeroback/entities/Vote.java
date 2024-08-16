package com.example.manajeroback.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Vote")
public class Vote implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String sessionId;
    private String issueId;
    private String vote;
    private String userId;
    private String userName;
    public Vote(String id, String sessionId, String userId, String issueId, String vote) {
        this.id = id;
        this.sessionId = sessionId;
        this.issueId = issueId;
        this.userId = userId;
        this.vote = vote;

    }
}
