package com.example.manajeroback.services;

import com.example.manajeroback.entities.Demo;

import java.util.List;

public interface IDemoService {
    public Demo addDemo(Demo demo);


    public List<Demo> retreiveDemo();
    public Demo updateDemo (Demo demo, long id);
}
