package com.example.manajeroback.Controllers;

import com.example.manajeroback.entities.Demo;
import com.example.manajeroback.services.IDemoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
