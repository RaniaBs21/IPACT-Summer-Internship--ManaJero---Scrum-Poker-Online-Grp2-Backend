package com.example.manajeroback.Controllers;

import com.example.manajeroback.entities.Demo;
import com.example.manajeroback.services.DemoServiceImpl;
import com.example.manajeroback.services.IDemoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor

public class DemoController {
    IDemoService demoService;

    @GetMapping("/getDemo")
    List<Demo> getDemo(){

        return demoService.retreiveDemo();
    }

    @PostMapping("/adddemo")
    Demo addDemo(@RequestBody Demo demo){

        return demoService.addDemo(demo);
    }
    @PutMapping("/updateDemo/{id}")
    public Demo updateDemo(@PathVariable String id, @RequestBody Demo demo) {
        return demoService.updateDemo(demo, id);

    }
    @DeleteMapping("/deletedemo/{id}")
    public void deletedemo(@PathVariable String id ){
        demoService.deleteDemo(id);
    }

}
