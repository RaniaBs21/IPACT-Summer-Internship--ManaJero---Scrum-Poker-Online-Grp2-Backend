package com.example.manajeroback.services;

import com.example.manajeroback.entities.Benefits;
import com.example.manajeroback.repositories.BenefitsRepository;
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

    public Benefits getBenefitById(Long id) {
        return benefitsRepository.findById(id).orElse(null);
    }

    public void deleteBenefit(Long id) {
        benefitsRepository.deleteById(id);
    }


}
