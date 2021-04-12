package com.example.travelfeemanagews.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "id")
    private Integer id;//用户id

    @Column(name = "username")
    private String username;//用户名

    @Column(name = "password")
    private String password;//用户密码

}
