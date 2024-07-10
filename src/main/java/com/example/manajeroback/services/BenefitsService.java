package com.example.manajeroback.services;

import com.example.manajeroback.entities.Benefits;
import com.example.manajeroback.repositories.BenefitsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BenefitsService {

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

  /*  public void deleteBenefit(String id) {
        benefitsRepository.deleteById(id);
    }*/


}
