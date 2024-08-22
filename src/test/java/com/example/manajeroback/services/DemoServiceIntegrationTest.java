package com.example.manajeroback.services;

import com.example.manajeroback.entities.Demo;
import com.example.manajeroback.repositories.DemoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
class DemoServiceIntegrationTest {

    @Autowired
    private DemoService demoService;
    @Autowired
    private DemoRepository demoRepository;

    @Test
    void addDemo() {
        Demo demo = new Demo("Integration Test Title", "Integration Test Intro", "Integration Test Description");
        Demo savedDemo = demoService.addDemo(demo);

        Assertions.assertNotNull(savedDemo.getId());
        Assertions.assertEquals("Integration Test Title", savedDemo.getTitle());

        System.out.println("demo added");
    }
    @Test
    void updateDemo() {
        Demo demo = new Demo("Integration Test Title", "Integration Test Intro", "Integration Test Description");
        Demo savedDemo = demoRepository.save(demo);

        Demo updatedDemo = new Demo("Updated Title", "Updated Intro", "Updated Description");
        Demo result = demoService.updateDemo(updatedDemo, savedDemo.getId());

        Assertions.assertEquals("Updated Title", result.getTitle());
        Assertions.assertEquals("Updated Intro", result.getIntro());
        Assertions.assertEquals("Updated Description", result.getDescription());
        System.out.println("demo updated");

    }
    @Test
    void retrieveDemo() {
        // Récupérer le nombre initial de sessions dans la base de données
        int initialDemoCount = demoService.retreiveDemo().size();

        // Ajouter quelques démos pour tester
        Demo demo1 = new Demo("Title 1", "Intro 1", "Description 1");
        Demo demo2 = new Demo("Title 2", "Intro 2", "Description 2");
        demoRepository.save(demo1);
        demoRepository.save(demo2);

        // Appeler la méthode pour récupérer toutes les démos
        List<Demo> demos = demoService.retreiveDemo();

        // Vérifier si le nombre de démos a augmenté de 2
        Assertions.assertNotNull(demos);
        Assertions.assertEquals(initialDemoCount + 2, demos.size());
        Assertions.assertTrue(demos.stream().anyMatch(demo -> "Title 1".equals(demo.getTitle())));
        Assertions.assertTrue(demos.stream().anyMatch(demo -> "Title 2".equals(demo.getTitle())));

        System.out.println("All demos retrieved successfully, including the newly added ones.");
    }


    @Test
    void deleteDemo() {
        Demo demo = new Demo("To Delete", "Intro", "Description");
        Demo savedDemo = demoRepository.save(demo);

        demoService.deleteDemo(savedDemo.getId());

        Optional<Demo> deletedDemo = demoRepository.findById(savedDemo.getId());
        Assertions.assertTrue(deletedDemo.isEmpty());
        System.out.println("demo deleted successfuly");
    }
}
