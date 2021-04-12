package com.example.travelfeemanagews.service;


import com.example.travelfeemanagews.entity.User;

public interface UserService {

    boolean isExistById(Integer id);
    Integer login(User user);

}
