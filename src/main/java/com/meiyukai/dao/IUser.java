package com.meiyukai.dao;

import com.meiyukai.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IUser {

    @Select(value = "select * from user")
    List<User> findAll();



}
