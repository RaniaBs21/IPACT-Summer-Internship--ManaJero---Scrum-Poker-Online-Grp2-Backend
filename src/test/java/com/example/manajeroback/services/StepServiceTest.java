package com.example.manajeroback.services;

import com.example.manajeroback.entities.Demo;
import com.example.manajeroback.entities.Steps;
import com.example.manajeroback.repositories.StepRepository;
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

@ExtendWith(MockitoExtension.class)
class StepServiceTest {
    @InjectMocks
    private StepService stepService ;
    @Mock
    private StepRepository stepRepository ;

    @Test
    void addStepsTest() {
        Steps steps = new Steps();
        steps.setTitle("test");
        steps.setStepDescription("test");
        // Configurer le comportement du mock
        Mockito.when(stepRepository.save(Mockito.any(Steps.class))).thenReturn(steps);
        Steps savedStep = stepService.addSteps(steps);
        Assertions.assertEquals("test", steps.getTitle());
        Assertions.assertEquals("test", steps.getStepDescription());

        System.out.println("test validé");
        System.out.println(savedStep);
        System.out.println(steps);
    }

    @Test
    void getAllStepsTest() {
        // Créer une liste de démos factices
        List<Steps> stepsList = Arrays.asList(
                new Steps("title1", "description1"),
                new Steps("title2", "description2")
        );

        // Configurer le comportement du mock
        Mockito.when(stepRepository.findAll()).thenReturn(stepsList);

        // Appeler la méthode à tester
        List<Steps> result = stepService.getAllSteps();

        // Vérifier les résultats
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("title1", result.get(0).getTitle());
        Assertions.assertEquals("title2", result.get(1).getTitle());

        // Afficher les résultats dans la console
        System.out.println("Retrieved steps: " + result);
        for (Steps steps : result) {
            System.out.println("Title: " + steps.getTitle());
            System.out.println("Description: " + steps.getStepDescription());
        }

        // Vérifier les interactions avec le mock
        Mockito.verify(stepRepository).findAll();
    }

    @Test
    void getStepByIdTest() {
        // ID fictif à rechercher
        String stepId = "668eaf72261f395a90fd4747";

        // Créer un objet Steps factice
        Steps step = new Steps("title1", "description1");
        step.setId(stepId);

        // Configurer le comportement du mock
        Mockito.when(stepRepository.findById(stepId)).thenReturn(Optional.of(step));

        // Appeler la méthode à tester
        Steps result = stepService.getStepById(stepId);

        // Vérifier les résultats
        Assertions.assertNotNull(result);
        Assertions.assertEquals(stepId, result.getId());
        Assertions.assertEquals("title1", result.getTitle());
        Assertions.assertEquals("description1", result.getStepDescription());

        // Afficher les résultats dans la console
        System.out.println("Retrieved Step: " + result);
        System.out.println("Title: " + result.getTitle());
        System.out.println("Description: " + result.getStepDescription());

        // Vérifier les interactions avec le mock
        Mockito.verify(stepRepository).findById(stepId);

    }

    @Test
    void updateStepTest() {
        String id = "668eaf72261f395a90fd4747";
        Steps existingStep= new Steps();
        existingStep.setTitle("existing title");
        existingStep.setStepDescription("existing description");

        Steps updatedStep = new Steps();
        updatedStep.setTitle("new title");
        updatedStep.setStepDescription("new description");

        // Configurer le comportement du mock
        Mockito.when(stepRepository.findById(id)).thenReturn(Optional.of(existingStep));
        Mockito.when(stepRepository.save(existingStep)).thenReturn(existingStep);

        // Appeler la méthode à tester
        Steps result = stepService.updateStep(updatedStep, id);

        // Vérifier les résultats
        Assertions.assertEquals("new title", result.getTitle());
        Assertions.assertEquals("new description", result.getStepDescription());

        // Vérifier les interactions avec le mock
        Mockito.verify(stepRepository).findById(id);
        Mockito.verify(stepRepository).save(existingStep);

        // Afficher les résultats dans la console
        System.out.println("Updated Demo: " + result);
        System.out.println("Title: " + result.getTitle());
        System.out.println("Description: " + result.getStepDescription());
    }

    @Test
    void deleteStepTest() {
        // ID fictif à supprimer
        String stepId = "668eaf72261f395a90fd4747";

        // Appeler la méthode à tester
        stepService.deleteStep(stepId);

        // Vérifier que la méthode deleteById du repository a été appelée avec le bon ID
        Mockito.verify(stepRepository).deleteById(stepId);

        // Imprimer un message pour vérifier dans la console
        System.out.println("Deleted Demo with ID: " + stepId);
        System.out.println("test validé " );
    }
}
