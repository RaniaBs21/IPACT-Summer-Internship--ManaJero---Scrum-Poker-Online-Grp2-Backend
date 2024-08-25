package com.example.manajeroback.Integration;

import com.example.manajeroback.entities.Benefits;
import com.example.manajeroback.repositories.BenefitsRepository;
import com.example.manajeroback.services.BenefitsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BenefitsServiceTestIntegration {

        @Autowired
        private BenefitsService benefitsService;

        @Autowired
        private BenefitsRepository benefitsRepository;

        private Benefits testBenefit;

        @BeforeEach
        void setUp() {
            // Créez un objet Benefits pour les tests
            testBenefit = new Benefits();
            testBenefit.setBenefDescription("Test Description");
            testBenefit.setTitle("Test Title");

            // Nettoyez le dépôt avant chaque test
            benefitsRepository.deleteAll();
        }

        @Test
        void addBenefits() {
            Benefits savedBenefit = benefitsService.addBenefits(testBenefit);
            assertNotNull(savedBenefit.getId(), "L'ID ne devrait pas être nul après l'enregistrement");
            assertEquals(testBenefit.getBenefDescription(), savedBenefit.getBenefDescription());
        }

        @Test
        void getAllBenefits() {
            benefitsService.addBenefits(testBenefit);

            List<Benefits> benefitsList = benefitsService.getAllBenefits();
            assertFalse(benefitsList.isEmpty(), "La liste des bénéfices ne devrait pas être vide");
            assertEquals(1, benefitsList.size(), "La taille de la liste devrait être 1");
        }

        @Test
        void getBenefitById() {
            Benefits savedBenefit = benefitsService.addBenefits(testBenefit);
            Benefits foundBenefit = benefitsService.getBenefitById(savedBenefit.getId());
            assertNotNull(foundBenefit, "Le bénéfice ne devrait pas être nul pour un ID valide");
            assertEquals(savedBenefit.getTitle(), foundBenefit.getTitle());
        }

        @Test
        void deleteBenefit() {
            Benefits savedBenefit = benefitsService.addBenefits(testBenefit);
            benefitsService.deleteBenefit(savedBenefit.getId());

            Benefits deletedBenefit = benefitsService.getBenefitById(savedBenefit.getId());
            assertNull(deletedBenefit, "Le bénéfice devrait être supprimé");
        }

        @Test
        void updateBenefit() {
            Benefits savedBenefit = benefitsService.addBenefits(testBenefit);

            Benefits updatedBenefit = new Benefits();
            updatedBenefit.setBenefDescription("Updated Description");
            updatedBenefit.setTitle("Updated Title");

            Benefits result = benefitsService.updateBenefit(updatedBenefit, savedBenefit.getId());
            assertEquals("Updated Description", result.getBenefDescription(), "La description devrait être mise à jour");
            assertEquals("Updated Title", result.getTitle(), "Le titre devrait être mis à jour");
        }
    }
