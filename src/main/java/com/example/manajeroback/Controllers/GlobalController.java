package com.example.manajeroback.Controllers;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller

public class GlobalController {
    private final SimpMessagingTemplate messagingTemplate;

    public GlobalController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendGlobalUpdate(Object update) {
        // Broadcast the update to all subscribers
        messagingTemplate.convertAndSend("/topic/globalUpdates", update);
    }
}
