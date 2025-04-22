package com.ssafy.shopApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class HomeController {

	@RequestMapping({"/", "/main"})
	public Object index() {
		return "index";
	}

}
