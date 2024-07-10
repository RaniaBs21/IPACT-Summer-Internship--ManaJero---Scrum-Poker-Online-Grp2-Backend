package com.example.manajeroback.services;

import com.example.manajeroback.entities.Benefits;
import com.example.manajeroback.entities.Limits;
import com.example.manajeroback.repositories.BenefitsRepository;
import com.example.manajeroback.repositories.LimitsRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LimitsService {
    LimitsRepository limitsRepository;
    public Limits addLimits(Limits limits) {
        return limitsRepository.save(limits);
    }

    public List<Limits> getAllLimits() {
        return limitsRepository.findAll();
    }

    public Limits getLimitById(String id) {
        return limitsRepository.findById(id).orElse(null);
    }

    public void deleteLimits(String id) {
        limitsRepository.deleteById(id);
    }

    public Limits updateLimit(Limits limits, String id) {
        Limits existingLimit = limitsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity with id " + id + " not found"));
        existingLimit.setLimitDescription(limits.getLimitDescription());
        existingLimit.setTitle(limits.getTitle());
        return limitsRepository.save(existingLimit);
    }
}
