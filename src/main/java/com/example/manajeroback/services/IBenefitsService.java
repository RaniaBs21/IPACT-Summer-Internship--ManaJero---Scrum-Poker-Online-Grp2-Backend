package com.example.manajeroback.services;

import com.example.manajeroback.entities.Benefits;

import java.util.List;

public interface IBenefitsService {

    public Benefits addBenefits(Benefits benefits);

    public List<Benefits> getAllBenefits();

    public Benefits getBenefitById(Long id);

    public void deleteBenefit(Long id);
}
