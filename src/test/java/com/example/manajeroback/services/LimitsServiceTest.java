package com.example.manajeroback.services;

import com.example.manajeroback.entities.Limits;
import com.example.manajeroback.repositories.LimitsRepository;
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
class LimitsServiceTest {

    @InjectMocks
    private LimitsService limitsService;

    @Mock
    private LimitsRepository limitsRepository;

    @Test
    void addLimits() {
        Limits limits = new Limits();
        limits.setTitle("Test Title");
        limits.setLimitDescription("Test Description");

        Mockito.when(limitsRepository.save(Mockito.any(Limits.class))).thenReturn(limits);

        Limits savedLimits = limitsService.addLimits(limits);

        Assertions.assertEquals("Test Title", savedLimits.getTitle());
        Assertions.assertEquals("Test Description", savedLimits.getLimitDescription());

        System.out.println("test addLimits validé");
        System.out.println(savedLimits);
    }

    @Test
    void getAllLimitsTest() {
        List<Limits> limitsList = Arrays.asList(
                new Limits("1", "Title1 limits", "Description1 limits"),
                new Limits("2", "Title2 limits", "Description2 limits")
        );

        Mockito.when(limitsRepository.findAll()).thenReturn(limitsList);

        List<Limits> result = limitsService.getAllLimits();

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("Title1 limits", result.get(0).getTitle());
        Assertions.assertEquals("Title2 limits", result.get(1).getTitle());

        System.out.println("Retrieved limits: " + result);
        for (Limits limit : result) {
            System.out.println("Title: " + limit.getTitle());
            System.out.println("Description: " + limit.getLimitDescription());
        }

        Mockito.verify(limitsRepository).findAll();
    }

    @Test
    void getLimitByIdTest() {
        String limitId = "1";
        Limits limits = new Limits("1", "Test Title limits", "Test Description limits");

        Mockito.when(limitsRepository.findById(limitId)).thenReturn(Optional.of(limits));

        Limits result = limitsService.getLimitById(limitId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(limitId, result.getId());
        Assertions.assertEquals("Test Title limits", result.getTitle());
        Assertions.assertEquals("Test Description limits", result.getLimitDescription());

        System.out.println("Retrieved Limit: " + result);
        System.out.println("Title: " + result.getTitle());
        System.out.println("Description: " + result.getLimitDescription());

        Mockito.verify(limitsRepository).findById(limitId);
    }

    @Test
    void updateLimit() {
        String id = "1";
        Limits existingLimits = new Limits("1", "Old Title", "Old Description");
        Limits updatedLimits = new Limits("1", "New Title", "New Description");

        Mockito.when(limitsRepository.findById(id)).thenReturn(Optional.of(existingLimits));
        Mockito.when(limitsRepository.save(existingLimits)).thenReturn(existingLimits);

        Limits result = limitsService.updateLimit(updatedLimits, id);

        Assertions.assertEquals("New Title", result.getTitle());
        Assertions.assertEquals("New Description", result.getLimitDescription());

        Mockito.verify(limitsRepository).findById(id);
        Mockito.verify(limitsRepository).save(existingLimits);

        System.out.println("test updateLimit validé");
        System.out.println("Updated Limit: " + result);
        System.out.println("Title: " + result.getTitle());
        System.out.println("Description: " + result.getLimitDescription());
        System.out.println(result);
    }

    @Test
    void deleteLimit() {
        String id = "1";

        limitsService.deleteLimits(id);

        Mockito.verify(limitsRepository).deleteById(id);

        System.out.println("test deleteLimit validé");
        System.out.println("Deleted Limits with ID: " + id);
    }
}
