package com.example.manajeroback.config;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SessionWebSocketHandler extends TextWebSocketHandler {

    private final List<WebSocketSession> sessions = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        // Example of received message, you can issue specific updates
        sendUpdateToAllSessions("A message was received");
    }

    private void sendUpdateToAllSessions(String updateMessage) {
        for (WebSocketSession session : sessions) {
            try {
                session.sendMessage(new TextMessage(updateMessage));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
