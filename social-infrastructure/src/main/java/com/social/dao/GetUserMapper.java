package com.social.dao;

import com.social.dao.entity.Animal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface GetUserMapper {

    @Select("select * from animals")
    public List<Animal> getAllAnimals();
}
