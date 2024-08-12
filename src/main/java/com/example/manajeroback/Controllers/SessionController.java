package com.example.manajeroback.Controllers;

import com.example.manajeroback.entities.Session;
import com.example.manajeroback.services.ApiResponse;
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
    @PutMapping("/updateSession/{id}")
    public Session updateSession(@PathVariable String id, @RequestBody Session session) {
        return sessionService.updateSession(session, id);

    }
    // User Invitation
    @PostMapping("/{sessionId}/invite")
    public ResponseEntity<String> inviteUserToSession(@PathVariable String sessionId, @RequestParam String email) {
        sessionService.inviteUserToSession(sessionId, email);
        return ResponseEntity.ok("Invitation sent to " + email);
    }
 /*   @PostMapping("/close/{id}")
    public ResponseEntity<ApiResponse> closeSession(@PathVariable String id) {
        try {
            sessionService.closeSession(id);
            return ResponseEntity.ok(new ApiResponse("Session closed successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
        }
    }*/
 @PostMapping("/close/{id}")
 public ResponseEntity<ApiResponse> closeSession(@PathVariable String id) {
     try {
         sessionService.closeSession(id);
         return ResponseEntity.ok(new ApiResponse("Session closed successfully"));
     } catch (RuntimeException e) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
     }
 }

    @GetMapping("/status/{id}")
    public ResponseEntity<ApiResponse> getSessionStatus(@PathVariable String id) {
        boolean closed = sessionService.isSessionClosed(id);
        return ResponseEntity.ok(new ApiResponse(closed ? "Session is closed" : "Session is open"));
    }
    // Point de terminaison pour obtenir le nombre d'utilisateurs dans une session

  
}
