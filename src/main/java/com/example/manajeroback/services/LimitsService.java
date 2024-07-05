package com.example.manajeroback.services;

import com.example.manajeroback.entities.Benefits;
import com.example.manajeroback.entities.Limits;
import com.example.manajeroback.repositories.BenefitsRepository;
import com.example.manajeroback.repositories.LimitsRepository;
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

    public Limits getLimitById(Long id) {
        return limitsRepository.findById(id).orElse(null);
    }

    public void deleteLimits(Long id) {
        limitsRepository.deleteById(id);
    }
}
