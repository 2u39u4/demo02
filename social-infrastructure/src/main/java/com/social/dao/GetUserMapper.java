package com.social.dao;

import com.social.dao.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GetUserMapper {

//    @Select("select * from users")
    public List<User> getAllUsers();
}
