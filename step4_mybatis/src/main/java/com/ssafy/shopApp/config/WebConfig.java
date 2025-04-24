package com.ssafy.shopApp.config;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.shopApp.controller.interceptor.LoginCheckInterceptor;
import com.ssafy.shopApp.listener.AppInitListener;

import jakarta.servlet.ServletContextListener;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Bean
	ServletListenerRegistrationBean<ServletContextListener> initListener() {
		return new ServletListenerRegistrationBean<>(new AppInitListener());
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/product/insertForm").setViewName("register");
		registry.addViewController("/user/loginForm").setViewName("user/login");
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginCheckInterceptor()).addPathPatterns("/product/**");
	}
}
