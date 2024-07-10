package com.example.manajeroback.services;

import com.example.manajeroback.entities.Benefits;
import com.example.manajeroback.entities.Demo;
import com.example.manajeroback.repositories.BenefitsRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BenefitsServiceImpl implements IBenefitsService{

    BenefitsRepository benefitsRepository;
    public Benefits addBenefits(Benefits benefits) {
        return benefitsRepository.save(benefits);
    }

    public List<Benefits> getAllBenefits() {
        return benefitsRepository.findAll();
    }

    public Benefits getBenefitById(String id) {
        return benefitsRepository.findById(id).orElse(null);
    }

    public void deleteBenefit(String id) {
        benefitsRepository.deleteById(id);
    }

    @Override
    public Benefits updateBenefit(Benefits benefits, String id) {
        Benefits existingBenefit = benefitsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity with id " + id + " not found"));
        existingBenefit.setBenefDescription(benefits.getBenefDescription());
        existingBenefit.setTitle(benefits.getTitle());
        return benefitsRepository.save(existingBenefit);
    }



}
