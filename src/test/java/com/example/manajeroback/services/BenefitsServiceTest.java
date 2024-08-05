package com.example.manajeroback.services;
import com.example.manajeroback.entities.Benefits;
import com.example.manajeroback.repositories.BenefitsRepository;
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
class BenefitsServiceTest {

    @InjectMocks
    private BenefitsService benefitsService;

    @Mock
    private BenefitsRepository benefitsRepository;

    @Test
    void addBenefits() {
        Benefits benefits = new Benefits();
        benefits.setTitle("Test Title");
        benefits.setBenefDescription("Test Description");

        Mockito.when(benefitsRepository.save(Mockito.any(Benefits.class))).thenReturn(benefits);

        Benefits savedBenefits = benefitsService.addBenefits(benefits);

        Assertions.assertEquals("Test Title", savedBenefits.getTitle());
        Assertions.assertEquals("Test Description", savedBenefits.getBenefDescription());

        System.out.println("test addBenefits validé");
        System.out.println(savedBenefits);
    }

    @Test
    void getAllBenefitsTest() {
        // Créer une liste de benefits factices
        List<Benefits> benefitsList = Arrays.asList(
                new Benefits("1", "Title1 benefits", "Description1 benefits"),
                new Benefits("2", "Title2 benefits", "Description2 benefits")
        );

        // Configurer le comportement du mock
        Mockito.when(benefitsRepository.findAll()).thenReturn(benefitsList);

        // Appeler la méthode à tester
        List<Benefits> result = benefitsService.getAllBenefits();

        // Vérifier les résultats
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("Title1 benefits", result.get(0).getTitle());
        Assertions.assertEquals("Title2 benefits", result.get(1).getTitle());

        // Afficher les résultats dans la console
        System.out.println("Retrieved benefits: " + result);
        for (Benefits benefit : result) {
            System.out.println("Title: " + benefit.getTitle());
            System.out.println("Description: " + benefit.getBenefDescription());
        }

        // Vérifier les interactions avec le mock
        Mockito.verify(benefitsRepository).findAll();
    }

    @Test
    void getBenefitByIdTest() {
        // ID fictif à rechercher
        String benefitId = "1";

        // Créer un objet Benefits factice
        Benefits benefits = new Benefits("1", "Test Title benefits", "Test Description benefits");

        // Configurer le comportement du mock
        Mockito.when(benefitsRepository.findById(benefitId)).thenReturn(Optional.of(benefits));

        // Appeler la méthode à tester
        Benefits result = benefitsService.getBenefitById(benefitId);

        // Vérifier les résultats
        Assertions.assertNotNull(result);
        Assertions.assertEquals(benefitId, result.getId());
        Assertions.assertEquals("Test Title benefits", result.getTitle());
        Assertions.assertEquals("Test Description benefits", result.getBenefDescription());

        // Afficher les résultats dans la console
        System.out.println("Retrieved Benefit: " + result);
        System.out.println("Title: " + result.getTitle());
        System.out.println("Description: " + result.getBenefDescription());

        // Vérifier les interactions avec le mock
        Mockito.verify(benefitsRepository).findById(benefitId);
    }


    @Test
    void updateBenefit() {
        String id = "1";
        Benefits existingBenefits = new Benefits("1", "Old Title", "Old Description");
        Benefits updatedBenefits = new Benefits("1", "New Title", "New Description");

        Mockito.when(benefitsRepository.findById(id)).thenReturn(Optional.of(existingBenefits));
        Mockito.when(benefitsRepository.save(existingBenefits)).thenReturn(existingBenefits);

        Benefits result = benefitsService.updateBenefit(updatedBenefits, id);

        Assertions.assertEquals("New Title", result.getTitle());
        Assertions.assertEquals("New Description", result.getBenefDescription());

        Mockito.verify(benefitsRepository).findById(id);
        Mockito.verify(benefitsRepository).save(existingBenefits);

        System.out.println("test updateBenefit validé");
        // Afficher les résultats dans la console
        System.out.println("Updated Demo: " + result);
        System.out.println("Title: " + result.getTitle());
        System.out.println("Description: " + result.getBenefDescription());
        System.out.println(result);
    }

    @Test
    void deleteBenefit() {
        String id = "668ea5171d205a329ab5360f";

        benefitsService.deleteBenefit(id);

        Mockito.verify(benefitsRepository).deleteById(id);

        System.out.println("test deleteBenefit validé");
        System.out.println("Deleted Benefits with ID: " + id);
    }
}
