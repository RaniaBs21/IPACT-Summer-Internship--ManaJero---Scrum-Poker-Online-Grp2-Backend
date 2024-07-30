package com.example.manajeroback.services;

import com.example.manajeroback.entities.Demo;
import com.example.manajeroback.entities.Diagram;
import com.example.manajeroback.entities.Steps;
import com.example.manajeroback.repositories.DemoRepository;
import com.example.manajeroback.repositories.DiagramRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class DiagramServiceTest {

    @InjectMocks
    private DiagramService diagramService;
    @Mock
    private DiagramRepository diagramRepository ;
    @Test
    void addDiagram() {
        Diagram diagram = new Diagram();
        diagram.setStptitle("test");
        diagram.setStpDescription("test");
        // Configurer le comportement du mock
        Mockito.when(diagramRepository.save(Mockito.any(Diagram.class))).thenReturn(diagram);
        Diagram savedDiagram = diagramService.addDiagram(diagram);
        Assertions.assertEquals("test", diagram.getStptitle());
        Assertions.assertEquals("test", diagram.getStpDescription());

        System.out.println("test validé");
        System.out.println(savedDiagram);
        System.out.println(diagram);
    }

    @Test
    void getAllDiagrams() { // Créer une liste de démos factices
        // Créer une liste de démos factices
        List<Diagram> diagramsList = Arrays.asList(
                new Diagram("title1", "description1"),
                new Diagram("title2", "description2")
        );

        // Configurer le comportement du mock
        Mockito.when(diagramRepository.findAll()).thenReturn(diagramsList);

        // Appeler la méthode à tester
        List<Diagram> result = diagramService.getAllDiagrams();

        // Vérifier les résultats
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("title1", result.get(0).getStptitle());
        Assertions.assertEquals("title2", result.get(1).getStptitle());

        // Afficher les résultats dans la console
        System.out.println("Retrieved steps: " + result);
        for (Diagram diagram : result) {
            System.out.println("Title: " + diagram.getStptitle());
            System.out.println("Description: " + diagram.getStpDescription());
        }

        // Vérifier les interactions avec le mock
        Mockito.verify(diagramRepository).findAll();
    }

    @Test
    void getDiagramById() {
        // ID fictif à rechercher
        String diagramId = "669072ad2e433d7552384b21";

        // Créer un objet Steps factice
        Diagram diagram = new Diagram("title1", "description1");
        diagram.setId(diagramId);

        // Configurer le comportement du mock
        Mockito.when(diagramRepository.findById(diagramId)).thenReturn(Optional.of(diagram));

        // Appeler la méthode à tester
        Diagram result = diagramService.getDiagramById(diagramId);

        // Vérifier les résultats
        Assertions.assertNotNull(result);
        Assertions.assertEquals(diagramId, result.getId());
        Assertions.assertEquals("title1", result.getStptitle());
        Assertions.assertEquals("description1", result.getStpDescription());

        // Afficher les résultats dans la console
        System.out.println("Retrieved Step: " + result);
        System.out.println("Title: " + result.getStptitle());
        System.out.println("Description: " + result.getStpDescription());

        // Vérifier les interactions avec le mock
        Mockito.verify(diagramRepository).findById(diagramId);
    }

    @Test
    void updateDiagram() {
        String id = "669072ad2e433d7552384b21";
        Diagram existingDiagram= new Diagram();
        existingDiagram.setStptitle("existing title");
        existingDiagram.setStpDescription("existing description");

        Diagram updatedDiagram = new Diagram();
        updatedDiagram.setStptitle("new title");
        updatedDiagram.setStpDescription("new description");

        // Configurer le comportement du mock
        Mockito.when(diagramRepository.findById(id)).thenReturn(Optional.of(existingDiagram));
        Mockito.when(diagramRepository.save(existingDiagram)).thenReturn(existingDiagram);

        // Appeler la méthode à tester
        Diagram result = diagramService.updateDiagram(updatedDiagram, id);

        // Vérifier les résultats
        Assertions.assertEquals("new title", result.getStptitle());
        Assertions.assertEquals("new description", result.getStpDescription());

        // Vérifier les interactions avec le mock
        Mockito.verify(diagramRepository).findById(id);
        Mockito.verify(diagramRepository).save(existingDiagram);

        // Afficher les résultats dans la console
        System.out.println("Updated Demo: " + result);
        System.out.println("Title: " + result.getStptitle());
        System.out.println("Description: " + result.getStpDescription());
    }

    @Test
    void deleteDiagram() {
        // ID fictif à supprimer
        String diagramId = "669072ad2e433d7552384b21";

        // Appeler la méthode à tester
        diagramService.deleteDiagram(diagramId);

        // Vérifier que la méthode deleteById du repository a été appelée avec le bon ID
        Mockito.verify(diagramRepository).deleteById(diagramId);

        // Imprimer un message pour vérifier dans la console
        System.out.println("Deleted Demo with ID: " + diagramId);
        System.out.println("test validé " );
    }
}
