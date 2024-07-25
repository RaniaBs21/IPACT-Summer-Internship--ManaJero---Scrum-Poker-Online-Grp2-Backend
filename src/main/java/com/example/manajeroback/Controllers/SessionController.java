package com.example.manajeroback.Controllers;

import com.example.manajeroback.entities.Issues;
import com.example.manajeroback.entities.News;
import com.example.manajeroback.entities.Session;
import com.example.manajeroback.services.SessionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class SessionController {
    SessionService sessionService ;

    @PostMapping("/addSession")
    Session addSession(@RequestBody Session session) {
        return  sessionService.addSession(session);
    }


    @GetMapping("/getSessions")
    List<Session> getSessions(){
        return sessionService.getAllSessions();
    }

    @GetMapping("/getSession/{id}")
    public Session getSessionById(@PathVariable String id) {
        return sessionService.getSessionById(id);
    }

    @DeleteMapping("/deleteSession/{id}")
    public void deleteSession(@PathVariable String id ){
        sessionService.deleteSession(id);
    }


    }


