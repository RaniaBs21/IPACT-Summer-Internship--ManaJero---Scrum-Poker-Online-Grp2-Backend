package com.example.manajeroback.services;

import com.example.manajeroback.entities.Demo;
import com.example.manajeroback.entities.Diagram;
import com.example.manajeroback.repositories.DemoRepository;
import com.example.manajeroback.repositories.DiagramRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
class DiagramServiceIntegrationTest {

    @Autowired
    private DiagramService diagramService;
    @Autowired
    private DiagramRepository diagramRepository;

    @Test
    void addDiagram() {
        Diagram diagram = new Diagram( "Integration Test Title", "Integration Test Description");
        Diagram savedDiagram = diagramService.addDiagram(diagram);

        Assertions.assertNotNull(savedDiagram.getId());
        Assertions.assertEquals("Integration Test Title", savedDiagram.getStptitle());

        System.out.println("Diagram added");
    }
    @Test
    void getAllDiagrams() {
        // Récupérer le nombre initial de sessions dans la base de données
        int initialDiagramCount = diagramService.getAllDiagrams().size();

        // Ajouter quelques démos pour tester
        Diagram diagram1 = new Diagram("Title 1",  "Description 1");
        Diagram diagram2 = new Diagram("Title 2",  "Description 2");
        diagramRepository.save(diagram1);
        diagramRepository.save(diagram2);

        // Appeler la méthode pour récupérer toutes les démos
        List<Diagram> diagrams = diagramService.getAllDiagrams();

        // Vérifier si le nombre de démos a augmenté de 2
        Assertions.assertNotNull(diagrams);
        Assertions.assertEquals(initialDiagramCount + 2, diagrams.size());
        Assertions.assertTrue(diagrams.stream().anyMatch(diagram -> "Title 1".equals(diagram.getStptitle())));
        Assertions.assertTrue(diagrams.stream().anyMatch(diagram -> "Title 2".equals(diagram.getStptitle())));

        System.out.println("All diagrams retrieved successfully, including the newly added ones.");
    }
    @Test
    void getDiagramById() {
        Diagram diagram = new Diagram( "Health Insurance", "Comprehensive health insurance plan");
        Diagram savedDiagram = diagramService.addDiagram(diagram);

        Diagram foundDiagram = diagramService.getDiagramById(savedDiagram.getId());
        org.assertj.core.api.Assertions.assertThat(foundDiagram).isNotNull();
        org.assertj.core.api.Assertions.assertThat(foundDiagram.getStptitle()).isEqualTo("Health Insurance");
        // Afficher les résultats dans la console
        System.out.println("Title: " + savedDiagram.getStptitle());
        System.out.println("Description: " + savedDiagram.getStpDescription());
    }

    @Test
    void updateDiagram() {
        Diagram diagram = new Diagram( "Integration Test Title update", "Integration Test Description update");
        Diagram savedDiagram = diagramService.addDiagram(diagram);

        Diagram updatedDiagram = new Diagram("Updated Title", "Updated Intro");
        Diagram result = diagramService.updateDiagram(updatedDiagram, savedDiagram.getId());

        Assertions.assertEquals("Updated Title", result.getStptitle());
        Assertions.assertEquals("Updated Intro", result.getStpDescription());
        System.out.println("Diagram updated");
    }

    @Test
    void deleteDiagram() {
        Diagram diagram = new Diagram("To Delete",  "Description");
        Diagram savedDiagram = diagramService.addDiagram(diagram);

        diagramService.deleteDiagram(savedDiagram.getId());

        Optional<Diagram> deletedDiagram = diagramRepository.findById(savedDiagram.getId());
        Assertions.assertTrue(deletedDiagram.isEmpty());
        System.out.println("Diagram deleted successfuly");
    }
}
