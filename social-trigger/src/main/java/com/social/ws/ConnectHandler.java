package com.social.ws;

import com.fasterxml.jackson.databind.JsonNode;

import com.social.annotations.WsController;
import com.social.annotations.WsHandler;
import com.social.common.WsWord;
import com.social.response.WsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Slf4j
@Component
@WsController
public class ConnectHandler {
    @WsHandler("CONNECT")
    public void handleConnect(String userId, JsonNode json, WebSocketSession session, MessageContext ctx) {
        log.info("connect:{}", userId);
        ctx.send(session, new WsResponse(WsWord.CONNECT_SUC, userId));
    }
}