package com.ssafy.live;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ssafy.live.model.dao.AddressDao;

@SpringBootApplication
public class Fw07MybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(Fw07MybatisApplication.class, args);
    }

}
