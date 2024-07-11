package com.example.manajeroback.services;

import com.example.manajeroback.entities.Diagram;
import com.example.manajeroback.entities.News;
import com.example.manajeroback.repositories.DiagramRepository;
import com.example.manajeroback.repositories.NewsRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class DiagramService {
    DiagramRepository diagramRepository ;

    public Diagram addDiagram(Diagram diagram) {
        return diagramRepository.save(diagram);
    }

    public List<Diagram> getAllDiagrams() {
        return diagramRepository.findAll();
    }

    public Diagram getDiagramById(String id) {
        return diagramRepository.findById(id).orElse(null);
    }

    public Diagram updateDiagram (Diagram diagram, String id) {
        Diagram existingDiagram = diagramRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity with id " + id + " not found"));
        existingDiagram.setStptitle(diagram.getStptitle());
        existingDiagram.setStpDescription(diagram.getStpDescription());
        return diagramRepository.save(existingDiagram);
    }
    public void deleteDiagram(String id) {
        diagramRepository.deleteById(id);
    }
}
