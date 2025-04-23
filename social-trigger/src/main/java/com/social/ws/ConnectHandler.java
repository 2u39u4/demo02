package com.social.ws;

import com.fasterxml.jackson.databind.JsonNode;

import com.social.annotations.WsHandler;
import com.social.response.WsResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Component
public class ConnectHandler {
    @WsHandler("CONNECT")
    public void handleConnect(String userId, JsonNode json, WebSocketSession session, MessageContext ctx) {
        ctx.send(session, new WsResponse("CONNECT_SUC", userId));
    }
}