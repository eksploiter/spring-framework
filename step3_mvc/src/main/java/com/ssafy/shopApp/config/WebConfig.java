package com.ssafy.shopApp.config;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ssafy.shopApp.listener.AppInitListener;

import jakarta.servlet.ServletContextListener;

@Configuration
public class WebConfig {
	
	@Bean
	ServletListenerRegistrationBean<ServletContextListener> initListener() {
		return new ServletListenerRegistrationBean<>(new AppInitListener());
	}
}
