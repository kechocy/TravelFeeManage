package com.example.travelfeemanagews;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.travelfeemanagews.mapper")
public class TravelFeeManageWsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelFeeManageWsApplication.class, args);
    }

}
