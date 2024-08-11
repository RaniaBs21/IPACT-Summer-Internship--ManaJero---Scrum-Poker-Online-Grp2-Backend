package com.example.manajeroback.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Vote")
public class Vote implements Serializable {
   /* @Id
    private String id;
    private String sessionId;
    private String issueId;
    private String userId;
    private String vote;
    public Vote(String id, String sessionId, String issueId, String vote) {
    }*/

    @Id
    String id;
    String sessionId;
    String issueId;
    String vote;
    String userId; // Ajout de l'userId


    @DBRef
    User user;
    @DBRef
    Issues issue;
    public Vote(String id, String sessionId, String issueId, String vote) {
    }
    public Vote(String id, String sessionId, String issueId, String vote, String userId) {
        this.id = id;
        this.sessionId = sessionId;
        this.issueId = issueId;
        this.vote = vote;
        this.userId = userId;
    }
}
