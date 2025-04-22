package com.social.ws.model;

public class UserMessage {
    private String type;          // "USER_INFO"
    private UserInfo data;        // 嵌套对象

    // Getters & Setters
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public UserInfo getData() {
        return data;
    }
    public void setData(UserInfo data) {
        this.data = data;
    }
}

