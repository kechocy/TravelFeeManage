package com.example.travelfeemanagews.mapper;

import com.example.travelfeemanagews.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    boolean isExistById(Integer id);
    Integer login(User user);

}
