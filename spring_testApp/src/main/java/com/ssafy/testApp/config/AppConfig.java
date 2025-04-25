package com.ssafy.testApp.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan("com.ssafy.testApp.model.dao")
@Configuration
public class AppConfig {
	

}
