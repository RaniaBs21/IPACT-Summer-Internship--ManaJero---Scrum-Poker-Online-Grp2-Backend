package com.example.manajeroback.services;

import com.example.manajeroback.entities.Benefits;
import com.example.manajeroback.entities.Demo;

import java.util.List;

public interface IBenefitsService {

    public Benefits addBenefits(Benefits benefits);

    public List<Benefits> getAllBenefits();

    public Benefits getBenefitById(String id);

    public void deleteBenefit(String id);
    public Benefits updateBenefit (Benefits benefits, String id);
}
