package com.social.ws.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Socket {
    //String类型的type，Object类型的data，String类型的id，String类型的server
    private String type;
    private Object data;
    private String id;
    private String server;

}
