package com.social.ws;


import com.social.response.WsResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

@Component
public class MessageContext {

    private Map<String, WebSocketSession> sessions;

    public void setSessions(Map<String, WebSocketSession> sessions) {
        this.sessions = sessions;
    }

    public WebSocketSession getSession(String userId) {
        return sessions.get(userId);
    }

    public void send(WebSocketSession session, WsResponse response) {
        try {
            if (session != null && session.isOpen()) {
                session.sendMessage(new TextMessage(response.toJson()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void broadcast(Set<String> userIds, WsResponse response) {
        for (String userId : userIds) {
            send(sessions.get(userId), response);
        }
    }

    public Map<String, WebSocketSession> getSessions() {
        return sessions;
    }
}


