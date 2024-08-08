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
    @Id
    String id;
    String sessionId;
    String issueId;
    String vote;

    @DBRef
    User user;
    @DBRef
    Issues issue;

    public Vote(String id, String sessionId, String issueId, String vote) {
    }

}
