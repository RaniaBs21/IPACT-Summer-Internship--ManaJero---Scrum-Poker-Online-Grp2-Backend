package com.example.manajeroback.Controllers;

import com.example.manajeroback.entities.Benefits;
import com.example.manajeroback.entities.Limits;
import com.example.manajeroback.services.LimitsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class LimitsController {
    LimitsService limitsService;


    @PostMapping("/addLimits")
    Limits addLimits(@RequestBody Limits limits) {
        return  limitsService.addLimits(limits);
    }


    @GetMapping("/getLimits")
    List<Limits> getLimits(){
        return limitsService.getAllLimits();
    }

    @GetMapping("/getLimits/{id}")
    public Limits getLimitById(@PathVariable String id) {
        return limitsService.getLimitById(id);
    }
    @DeleteMapping("/deletelimit/{id}")
    public void deletelimit(@PathVariable String id ){
        limitsService.deleteLimits(id);
    }

    @PutMapping("/updateLimit/{id}")
    public Limits updateBenefit(@PathVariable String id, @RequestBody Limits limit) {
        return limitsService.updateLimit(limit, id);

    }
}
