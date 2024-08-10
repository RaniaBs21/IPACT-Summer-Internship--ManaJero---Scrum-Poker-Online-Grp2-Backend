package com.example.manajeroback.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "User")

public class User implements Serializable {
    @Id
    String id;
    String name;
    String email;

    @JsonIgnore
    @DBRef
    Session session;
    @JsonIgnore
    @DBRef
    List<Vote> votes;


}
