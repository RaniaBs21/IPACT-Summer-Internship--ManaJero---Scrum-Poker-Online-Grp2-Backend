package com.example.manajeroback.Controllers;

import com.example.manajeroback.entities.Diagram;
import com.example.manajeroback.services.DiagramService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class DiagramController {
    DiagramService diagramService;


    @PostMapping("/addDiagram")
    Diagram addNews(@RequestBody Diagram diagram) {
        return  diagramService.addDiagram(diagram);
    }


    @GetMapping("/getDiagrams")
    List<Diagram> getDiagrams(){
        return diagramService.getAllDiagrams();
    }

    @GetMapping("/getDiagrams/{id}")
    public Diagram getDiagramById(@PathVariable String id) {
        return diagramService.getDiagramById(id);
    }

    @PutMapping("/updateDiagram/{id}")
    public Diagram updateDiagram(@PathVariable String id, @RequestBody Diagram diagram) {
        return diagramService.updateDiagram(diagram, id);

    }

    @DeleteMapping("/deleteDiagram/{id}")
    void deleteDiagram(@PathVariable String id) {
        diagramService.deleteDiagram(id);
    }
}
