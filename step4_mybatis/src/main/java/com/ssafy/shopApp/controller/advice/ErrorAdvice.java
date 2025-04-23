package com.ssafy.shopApp.controller.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice // spring 의 controller 처럼  
@Slf4j
public class ErrorAdvice {
	
	@ExceptionHandler(Exception.class) // 마치 catch 처럼
	public String error(Exception e, Model model) {
		log.error(e.getMessage(), e);
		model.addAttribute("errorMsg", e.getMessage());
		return "common/error";
	}
}
