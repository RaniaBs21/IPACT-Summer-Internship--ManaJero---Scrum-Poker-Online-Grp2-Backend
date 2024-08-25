package com.example.manajeroback.Integration;

import com.example.manajeroback.entities.Diagram;
import com.example.manajeroback.repositories.DiagramRepository;
import com.example.manajeroback.services.DiagramService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DiagramServiceTestIntegration {

    @Autowired
    private DiagramService diagramService;

    @Autowired
    private DiagramRepository diagramRepository;

    private Diagram testDiagram;

    @BeforeEach
    void setUp() {
        // Créez un objet Diagram pour les tests
        testDiagram = new Diagram();
        testDiagram.setStptitle("Test Title");
        testDiagram.setStpDescription("Test Description");

        // Nettoyez le dépôt avant chaque test
        diagramRepository.deleteAll();
    }

    @Test
    void addDiagram() {
        Diagram savedDiagram = diagramService.addDiagram(testDiagram);
        assertNotNull(savedDiagram.getId(), "L'ID ne devrait pas être nul après l'enregistrement");
        assertEquals(testDiagram.getStptitle(), savedDiagram.getStptitle());
        assertEquals(testDiagram.getStpDescription(), savedDiagram.getStpDescription());
    }

    @Test
    void getAllDiagrams() {
        diagramService.addDiagram(testDiagram);

        List<Diagram> diagramsList = diagramService.getAllDiagrams();
        assertFalse(diagramsList.isEmpty(), "La liste des diagrammes ne devrait pas être vide");
        assertEquals(1, diagramsList.size(), "La taille de la liste devrait être 1");
    }

    @Test
    void getDiagramById() {
        Diagram savedDiagram = diagramService.addDiagram(testDiagram);
        Diagram foundDiagram = diagramService.getDiagramById(savedDiagram.getId());
        assertNotNull(foundDiagram, "Le diagramme ne devrait pas être nul pour un ID valide");
        assertEquals(savedDiagram.getStptitle(), foundDiagram.getStptitle());
        assertEquals(savedDiagram.getStpDescription(), foundDiagram.getStpDescription());
    }

    @Test
    void deleteDiagram() {
        Diagram savedDiagram = diagramService.addDiagram(testDiagram);
        diagramService.deleteDiagram(savedDiagram.getId());

        Diagram deletedDiagram = diagramService.getDiagramById(savedDiagram.getId());
        assertNull(deletedDiagram, "Le diagramme devrait être supprimé");
    }

    @Test
    void updateDiagram() {
        Diagram savedDiagram = diagramService.addDiagram(testDiagram);

        Diagram updatedDiagram = new Diagram();
        updatedDiagram.setStptitle("Updated Title");
        updatedDiagram.setStpDescription("Updated Description");

        Diagram result = diagramService.updateDiagram(updatedDiagram, savedDiagram.getId());
        assertEquals("Updated Title", result.getStptitle(), "Le titre devrait être mis à jour");
        assertEquals("Updated Description", result.getStpDescription(), "La description devrait être mise à jour");
    }
}
