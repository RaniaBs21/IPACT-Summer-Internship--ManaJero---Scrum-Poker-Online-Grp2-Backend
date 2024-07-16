package com.example.manajeroback.services;



import com.example.manajeroback.entities.Steps;
import com.example.manajeroback.repositories.StepRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StepService {

    StepRepository stepRepo;
    public Steps addSteps(Steps steps) {
        return stepRepo.save(steps);
    }

    public List<Steps> getAllSteps() {
        return stepRepo.findAll();
    }

    public Steps getStepById(String id) {
        return stepRepo.findById(id).orElse(null);
    }

    public Steps updateStep (Steps steps, String id) {
        Steps existingStep = stepRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity with id " + id + " not found"));
        existingStep.setTitle(steps.getTitle());
        existingStep.setStepDescription(steps.getStepDescription());
        return stepRepo.save(existingStep);
    }
    public void deleteStep(String id) {
        stepRepo.deleteById(id);
    }


}
