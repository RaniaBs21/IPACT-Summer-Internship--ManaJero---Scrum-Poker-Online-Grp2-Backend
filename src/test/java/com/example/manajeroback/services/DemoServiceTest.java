package com.example.manajeroback.services;

import com.example.manajeroback.ManajeroBackApplication;
import com.example.manajeroback.entities.Demo;
import com.example.manajeroback.repositories.DemoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class DemoServiceTest {
    @InjectMocks
    private DemoService demoService;
    @Mock
    private DemoRepository demoRepository;

    @Test
    void addDemo() {
        Demo demo = new Demo();
        demo.setTitle("test");
        demo.setIntro("test");
        demo.setDescription("test");
        // Configurer le comportement du mock
        Mockito.when(demoRepository.save(Mockito.any(Demo.class))).thenReturn(demo);
        Demo savedDemo = demoService.addDemo(demo);
        Assertions.assertEquals("test", demo.getTitle());
        Assertions.assertEquals("test", demo.getIntro());
        Assertions.assertEquals("test", demo.getDescription());

        System.out.println("test validé");
        System.out.println(demo);
    }


    @Test
    void updateDemo() {
        String id = "668eae10261f395a90fd473f";
        Demo existingDemo = new Demo();
        existingDemo.setTitle("existing title");
        existingDemo.setIntro("existing intro");
        existingDemo.setDescription("existing description");

        Demo updatedDemo = new Demo();
        updatedDemo.setTitle("new title");
        updatedDemo.setIntro("new intro");
        updatedDemo.setDescription("new description");

        // Configurer le comportement du mock
        Mockito.when(demoRepository.findById(id)).thenReturn(Optional.of(existingDemo));
        Mockito.when(demoRepository.save(existingDemo)).thenReturn(existingDemo);

        // Appeler la méthode à tester
        Demo result = demoService.updateDemo(updatedDemo, id);

        // Vérifier les résultats
        Assertions.assertEquals("new title", result.getTitle());
        Assertions.assertEquals("new intro", result.getIntro());
        Assertions.assertEquals("new description", result.getDescription());

        // Vérifier les interactions avec le mock
        Mockito.verify(demoRepository).findById(id);
        Mockito.verify(demoRepository).save(existingDemo);

        // Afficher les résultats dans la console
        System.out.println("Updated Demo: " + result);
        System.out.println("Title: " + result.getTitle());
        System.out.println("Intro: " + result.getIntro());
        System.out.println("Description: " + result.getDescription());

    }
    @Test
    void retrieveDemo() {
        // Créer une liste de démos factices
        List<Demo> demoList = Arrays.asList(
                new Demo("title1", "intro1", "description1"),
                new Demo("title2", "intro2", "description2")
        );

        // Configurer le comportement du mock
        Mockito.when(demoRepository.findAll()).thenReturn(demoList);

        // Appeler la méthode à tester
        List<Demo> result = demoService.retreiveDemo();

        // Vérifier les résultats
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("title1", result.get(0).getTitle());
        Assertions.assertEquals("title2", result.get(1).getTitle());

        // Afficher les résultats dans la console
        System.out.println("Retrieved Demos: " + result);
        for (Demo demo : result) {
            System.out.println("Title: " + demo.getTitle());
            System.out.println("Intro: " + demo.getIntro());
            System.out.println("Description: " + demo.getDescription());
        }

        // Vérifier les interactions avec le mock
        Mockito.verify(demoRepository).findAll();
    }
    @Test
    void deleteDemo() {
        // ID fictif à supprimer
        String demoId = "123";

        // Appeler la méthode à tester
        demoService.deleteDemo(demoId);

        // Vérifier que la méthode deleteById du repository a été appelée avec le bon ID
        Mockito.verify(demoRepository).deleteById(demoId);

        // Imprimer un message pour vérifier dans la console
        System.out.println("Deleted Demo with ID: " + demoId);
    }

}
