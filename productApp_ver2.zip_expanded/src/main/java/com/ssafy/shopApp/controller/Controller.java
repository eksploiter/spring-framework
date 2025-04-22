package com.ssafy.shopApp.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Controller {
	public Object handleRequest(String action, HttpServletRequest request, HttpServletResponse response)
			throws Exception ;
		
}
