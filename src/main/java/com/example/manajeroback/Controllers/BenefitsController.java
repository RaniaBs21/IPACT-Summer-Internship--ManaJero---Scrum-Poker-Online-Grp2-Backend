package com.example.manajeroback.Controllers;

import com.example.manajeroback.entities.Benefits;
import com.example.manajeroback.entities.Demo;
import com.example.manajeroback.services.BenefitsServiceImpl;
import com.example.manajeroback.services.IBenefitsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@AllArgsConstructor
public class BenefitsController {

    IBenefitsService benefitsService;

    @PostMapping("/addBenefits")
    Benefits addBenefits(@RequestBody Benefits benefits) {
        return  benefitsService.addBenefits(benefits);
    }


    @GetMapping("/getBenefits")
    List<Benefits> getBenefits(){
        return benefitsService.getAllBenefits();
    }

    @GetMapping("/getBenefits/{id}")
    public Benefits getBenefitById(@PathVariable Long id) {
        return benefitsService.getBenefitById(id);
    }


}
