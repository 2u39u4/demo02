package com.social.dao.mysql.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO user_sessions (user_name, gender) VALUES (#{userName}, #{gender})")
    void insertUserSession(String userName, String gender);
}