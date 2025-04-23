package com.social.config.ws;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Autowired
    private MessageRouter router;

    @PostConstruct
    public void init() {
        router.setSessions(sessions);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        String userId = UUID.randomUUID().toString();
        session.getAttributes().put("userId", userId);
        sessions.put(userId, session);
//        log.info("xxxxx connect:{}", userId);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        String userId = (String) session.getAttributes().get("userId");
//        log.info("handleTextMessage message:{}", message.getPayload());
        router.route(userId, message.getPayload());//转发
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        String userId = (String) session.getAttributes().get("userId");
        sessions.remove(userId);
    }
}


