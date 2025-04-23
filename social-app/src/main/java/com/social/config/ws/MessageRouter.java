package com.social.config.ws;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.social.annotations.WsHandler;

import com.social.response.WsResponse;
import com.social.ws.MessageContext;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import org.springframework.web.socket.WebSocketSession;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan(basePackages = {"com.social.ws"},
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,
                value = WsHandler.class))
public class MessageRouter implements ApplicationContextAware {

    private ApplicationContext context;

    private final Map<String, Method> handlerMap = new HashMap<>();
    private final Map<Method, Object> beanMap = new HashMap<>();

    @Autowired
    private MessageContext mcontext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.context = applicationContext;
    }

    @PostConstruct
    public void init() {
        for (Object bean : context.getBeansWithAnnotation(WsHandler.class).values()) {
            for (Method method : bean.getClass().getDeclaredMethods()) {
                if (method.isAnnotationPresent(WsHandler.class)) {
                    String type = method.getAnnotation(WsHandler.class).value();
                    handlerMap.put(type, method);
                    beanMap.put(method, bean);
                }
            }
        }
    }

    public void setSessions(Map<String, WebSocketSession> sessions) {
        mcontext.setSessions(sessions);
    }

    public void route(String userId, String payload) {
        try {
            // 创建 ObjectMapper 实例
            ObjectMapper objectMapper = new ObjectMapper();

            // 将 JSON 字符串解析为 JsonNode
            JsonNode rootNode = objectMapper.readTree(payload);

            // 获取 type 字段
            String type = rootNode.path("type").asText();

            Method method = handlerMap.get(type);
            if (method != null) {
                Object bean = beanMap.get(method);
                method.invoke(bean, userId, rootNode,mcontext.getSession(userId), mcontext);
            } else {
                mcontext.send(mcontext.getSession(userId),
                        new WsResponse("error", "Unknown message type: " + type));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


