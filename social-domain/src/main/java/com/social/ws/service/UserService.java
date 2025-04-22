package com.social.ws.service;


import com.social.dao.mysql.mapper.UserMapper;
import com.social.ws.model.Socket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void saveUserSession(String userName, String gender) {
        userMapper.insertUserSession(userName, gender);

    }
}

