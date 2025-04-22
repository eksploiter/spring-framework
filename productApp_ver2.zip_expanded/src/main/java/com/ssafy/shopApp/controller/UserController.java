package com.ssafy.shopApp.controller;

import com.ssafy.shopApp.model.service.IUserService;
import com.ssafy.shopApp.model.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class UserController implements Controller{
	
	private IUserService userService = new UserService();
	
	@Override
	public Object handleRequest(String action, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return switch (action) {
		case "login" -> login(request,response);
		case "logout" -> logout(request,response);				
		case "loginForm" -> "forward:/user/login.jsp";								
		default -> throw new IllegalArgumentException("처리할 컨트롤러 로직이 존재하지 않습니다.");
	};
	}
	
	protected String login(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		// 1. get parameter		
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");

		// 2. call model
		String name = userService.login(userId, password);
		if(name != null) {
			HttpSession session = request.getSession();
			session.setAttribute("userId", userId);
			session.setAttribute("name", name);
		}else {
			request.setAttribute("loginError", "아이디 또는 비밀번호가 일치하지 않습니다.");
			return "forward:/main?action=user_loginForm";
		}
		// view
		return "redirect:/main?action=home";
	}
	protected String logout(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		request.getSession().invalidate();
		// view
		return "redirect:/main?action=home";
	}

}
