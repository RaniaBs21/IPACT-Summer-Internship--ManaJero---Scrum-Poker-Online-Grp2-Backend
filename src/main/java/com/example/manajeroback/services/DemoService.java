package com.example.manajeroback.services;

import com.example.manajeroback.entities.Demo;
import com.example.manajeroback.repositories.DemoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DemoService {

    DemoRepository demoRepository;

    public Demo addDemo(Demo demo) {
        return demoRepository.save(demo);
    }
    public List<Demo> retreiveDemo() {
        return demoRepository.findAll();
    }


    public Demo updateDemo (Demo demo, String id) {
        Demo existingDemo = demoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity with id " + id + " not found"));
        existingDemo.setDescription(demo.getDescription());
        existingDemo.setTitle(demo.getTitle());

        return demoRepository.save(existingDemo);
    }


    public void deleteDemo(String id) {
        demoRepository.deleteById(id);
    }
}
