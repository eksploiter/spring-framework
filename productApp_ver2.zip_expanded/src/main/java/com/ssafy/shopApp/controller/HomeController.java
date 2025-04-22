package com.ssafy.shopApp.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HomeController implements Controller {

	@Override
	public Object handleRequest(String action, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return "forward:/index.jsp";
	}

}
