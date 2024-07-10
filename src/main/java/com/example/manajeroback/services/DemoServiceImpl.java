package com.example.manajeroback.services;

import com.example.manajeroback.entities.Demo;
import com.example.manajeroback.repositories.ApiRepository;
import com.example.manajeroback.repositories.DemoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DemoServiceImpl implements IDemoService{

    DemoRepository demoRepository;
    @Override
    public Demo addDemo(Demo demo) {
        return demoRepository.save(demo);
    }

    @Override
    public List<Demo> retreiveDemo() {
        return demoRepository.findAll();
    }

    @Override
    public Demo updateDemo (Demo demo, String id) {
        Demo existingDemo = demoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity with id " + id + " not found"));
        existingDemo.setDescription(demo.getDescription());
        return demoRepository.save(existingDemo);
    }

    @Override
    public void deleteDemo(String id) {
        demoRepository.deleteById(id);
    }
}
