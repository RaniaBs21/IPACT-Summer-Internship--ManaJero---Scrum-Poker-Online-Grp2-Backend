package com.example.manajeroback.services;

import com.example.manajeroback.entities.Demo;

import java.util.List;

public interface IDemoService {

    Demo addDemo(Demo demo);
    List<Demo> retreiveDemo () ;
}
