package com.example.manajeroback.Controllers;

import com.example.manajeroback.entities.Benefits;
import com.example.manajeroback.services.BenefitsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class BenefitsController {

     BenefitsService benefitsService;

    @PostMapping("/addBenefits")
     Benefits addBenefits(@RequestBody Benefits benefits) {
        return  benefitsService.addBenefits(benefits);
    }


    @GetMapping("/getBenefits")
    List<Benefits> getBenefits(){
        return benefitsService.getAllBenefits();
    }

    @GetMapping("/getBenefits/{id}")
    public Benefits getBenefitById(@PathVariable String id) {
        return benefitsService.getBenefitById(id);
    }


}
