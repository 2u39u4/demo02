package com.social.dao.mysql;

import com.social.dao.mysql.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GetUserMapper {

//    @Select("select * from users")
    public List<User> getAllUsers();
}
