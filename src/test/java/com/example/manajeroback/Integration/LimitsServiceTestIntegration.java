package com.example.manajeroback.Integration;

import com.example.manajeroback.entities.Limits;
import com.example.manajeroback.repositories.LimitsRepository;
import com.example.manajeroback.services.LimitsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LimitsServiceTestIntegration {

    @Autowired
    private LimitsService limitsService;

    @Autowired
    private LimitsRepository limitsRepository;

    private Limits testLimit;

    @BeforeEach
    void setUp() {
        // Créer un objet Limits pour les tests
        testLimit = new Limits();
        testLimit.setTitle("Test Limit");
        testLimit.setLimitDescription("This is a test limit.");

        limitsRepository.deleteAll();
        System.out.println("Setup: Le dépôt des limites est nettoyé.");
    }

    @Test
    void addLimits() {
        Limits savedLimit = limitsService.addLimits(testLimit);
        System.out.println("AddLimits: Limite ajoutée avec ID = " + savedLimit.getId());

        assertNotNull(savedLimit.getId(), "L'ID ne devrait pas être nul après l'enregistrement");
        assertEquals(testLimit.getTitle(), savedLimit.getTitle());
    }

    @Test
    void getAllLimits() {
        limitsService.addLimits(testLimit);
        System.out.println("GetAllLimits: Limite ajoutée.");

        List<Limits> limitsList = limitsService.getAllLimits();
        System.out.println("GetAllLimits: Nombre de limites récupérées = " + limitsList.size());
    }

    @Test
    void getLimitById() {
        Limits savedLimit = limitsService.addLimits(testLimit);
        System.out.println("GetLimitById: Limite ajoutée avec ID = " + savedLimit.getId());

        Limits foundLimit = limitsService.getLimitById(savedLimit.getId());
        System.out.println("GetLimitById: Limite trouvée avec ID = " + foundLimit.getId());

        assertNotNull(foundLimit, "La limite ne devrait pas être nulle pour un ID valide");
        assertEquals(savedLimit.getTitle(), foundLimit.getTitle());
    }

    @Test
    void deleteLimits() {
        Limits savedLimit = limitsService.addLimits(testLimit);
        System.out.println("DeleteLimits: Limite ajoutée avec ID = " + savedLimit.getId());

        limitsService.deleteLimits(savedLimit.getId());
        System.out.println("DeleteLimits: Limite supprimée avec ID = " + savedLimit.getId());

        Limits deletedLimit = limitsService.getLimitById(savedLimit.getId());
        System.out.println("DeleteLimits: Limite après suppression = " + deletedLimit);

        assertNull(deletedLimit, "La limite devrait être supprimée");
    }

    @Test
    void updateLimit() {
        Limits savedLimit = limitsService.addLimits(testLimit);
        System.out.println("UpdateLimit: Limite ajoutée avec ID = " + savedLimit.getId());

        Limits updatedLimit = new Limits();
        updatedLimit.setTitle("Updated Limit");
        updatedLimit.setLimitDescription("Updated Description");

        Limits result = limitsService.updateLimit(updatedLimit, savedLimit.getId());
        System.out.println("UpdateLimit: Limite mise à jour avec le nouveau titre = " + result.getTitle() +
                " et la nouvelle description = " + result.getLimitDescription());

        assertEquals("Updated Limit", result.getTitle(), "Le nom devrait être mis à jour");
        assertEquals("Updated Description", result.getLimitDescription(), "La description devrait être mise à jour");
    }
}
