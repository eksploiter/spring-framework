package com.ssafy.live.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.ssafy.live.model.dao")
public class ApplicationConfig {

}
