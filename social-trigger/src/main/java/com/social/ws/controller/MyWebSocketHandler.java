package com.social.ws.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.uuid.Generators;
import com.social.ws.model.Result;
import com.social.ws.model.UserInfo;
import com.social.ws.model.UserMessage;
import com.social.ws.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MyWebSocketHandler extends TextWebSocketHandler {

    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
    private final UserService userService; // 假设有一个UserService处理数据库操作

    public MyWebSocketHandler(UserService userService) {
        this.userService = userService;
    }


    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        try {
            String jsonPayload = message.getPayload();

            // 1. 解析 JSON
            UserMessage userMessage = objectMapper.readValue(jsonPayload, UserMessage.class);

            // 2. 处理消息
            if ("USER_INFO".equals(userMessage.getType())) {
                UserInfo userInfo = userMessage.getData();
                if (userInfo == null) {
                    throw new IllegalArgumentException("UserInfo is null");
                }
                System.out.println("收到用户: " + userInfo.getUserName() + ", 性别: " + userInfo.getGender());

                userService.saveUserSession(userInfo.getUserName(), userInfo.getGender());
            }
        } catch (Exception e) {
            // 记录异常并关闭会话
            System.err.println("Error in handleTextMessage: " + e.getMessage());
            session.close();
        }
    }
    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) throws Exception {
        String sessionId = session.getId();
        sessions.remove(sessionId);
        System.out.println("连接关闭, sessionId: " + sessionId);
    }
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 生成 sessionId
        String sessionId = Generators.timeBasedGenerator().generate().toString();
        sessions.put(sessionId, session);

        // 发送 sessionId 给客户端
        Map<String, String> message = new HashMap<>();
        message.put("type", "CONNECT_SUC");
        message.put("msg", sessionId);
        message.put("sender", "SERVER");

        // 将 Map 转为 JSON 字符串
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonMessage = objectMapper.writeValueAsString(message);

        // 发送消息
        session.sendMessage(new TextMessage(jsonMessage));
    }
}
