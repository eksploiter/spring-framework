package com.ssafy.testApp.config;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.testApp.listener.AppInitListener;

import jakarta.servlet.ServletContextListener;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Bean
	ServletListenerRegistrationBean<ServletContextListener> initListener() {
		return new ServletListenerRegistrationBean<>(new AppInitListener());
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	}
}
