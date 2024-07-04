package com.example.manajeroback.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Document(collection = "Demo")
public class Demo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idDemo;
    String title;
    String description;
    String benefits;
    String limits;
    String image;

}






