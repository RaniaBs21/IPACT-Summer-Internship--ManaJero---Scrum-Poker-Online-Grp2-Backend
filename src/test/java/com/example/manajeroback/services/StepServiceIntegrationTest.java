package com.example.manajeroback.services;

import com.example.manajeroback.entities.Steps;
import com.example.manajeroback.repositories.StepRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;


@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
class StepServiceIntegrationTest {

    @Autowired
    private StepService stepService;
    @Autowired
    private StepRepository stepRepository;
    @Test
    void addSteps() {
        Steps step = new Steps("Title 1",  "Description 1");
        Steps savedSteps = stepService.addSteps(step);

        Assertions.assertNotNull(savedSteps.getId());
        Assertions.assertEquals("Title 1", savedSteps.getTitle());

        System.out.println("Step added");
    }

    @Test
    void getAllSteps() {
        // Récupérer le nombre initial de steps dans la base de données
        int initialStepsCount = stepService.getAllSteps().size();

        // Ajouter quelques steps pour tester
        Steps step1 = new Steps("Title 11", "Description 11");
        Steps step2 = new Steps("Title 22", "Description 22");
        stepRepository.save(step1);
        stepRepository.save(step2);

        // Appeler la méthode pour récupérer tous les steps
        List<Steps> steps = stepService.getAllSteps();

        // Vérifier si le nombre de steps a augmenté de 2
        Assertions.assertNotNull(steps);
        Assertions.assertEquals(initialStepsCount + 2, steps.size());
        Assertions.assertTrue(steps.stream().anyMatch(step -> "Title 11".equals(step.getTitle())));
        Assertions.assertTrue(steps.stream().anyMatch(step -> "Title 22".equals(step.getTitle())));

        System.out.println("All steps retrieved successfully, including the newly added ones.");
    }


    @Test
    void getStepById() {
        Steps step = new Steps("Title 1",  "Description 1");
        Steps savedSteps = stepService.addSteps(step);

        Steps foundStep = stepService.getStepById(savedSteps.getId());
        org.assertj.core.api.Assertions.assertThat(foundStep).isNotNull();
        org.assertj.core.api.Assertions.assertThat(foundStep.getTitle()).isEqualTo("Title 1");
        // Afficher les résultats dans la console
        System.out.println("Title: " + savedSteps.getTitle());
        System.out.println("Description: " + savedSteps.getStepDescription());
    }

    @Test
    void updateStep() {
        Steps step = new Steps( "Integration Test Title update", "Integration Test Description update");
        Steps savedSteps = stepService.addSteps(step);

        Steps updatedSteps = new Steps("Updated Title", "Updated Intro");
        Steps result = stepService.updateStep(updatedSteps, savedSteps.getId());

        Assertions.assertEquals("Updated Title", result.getTitle());
        Assertions.assertEquals("Updated Intro", result.getStepDescription());
        System.out.println("Steps updated");
    }

    @Test
    void deleteStep() {
        Steps step = new Steps("To Delete",  "Description");
        Steps savedSteps = stepService.addSteps(step);

        stepService.deleteStep(savedSteps.getId());

        Optional<Steps> deletedStep = stepRepository.findById(savedSteps.getId());
        Assertions.assertTrue(deletedStep.isEmpty());
        System.out.println("Steps deleted successfuly");
    }
}
