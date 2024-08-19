package com.example.manajeroback.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Session")
public class Session implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String id;
    private String currentTurnUserId; // ID de l'utilisateur dont c'est le tour
    private Map<String, String> votes; //
    String name;
    @Enumerated(EnumType.STRING)
    VotingSystem votingSystem=VotingSystem.FIBONACCI;
    @JsonIgnore
    @DBRef
    List<Issues> issues;
    boolean closed = false;
    public Session(String s, VotingSystem votingSystem) {
        this.name=s;
        this.votingSystem=votingSystem;
    }
}
