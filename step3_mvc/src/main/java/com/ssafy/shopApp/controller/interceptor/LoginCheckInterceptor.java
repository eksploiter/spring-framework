package com.ssafy.shopApp.controller.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("LoginCheckInterceptor's preHandler");
		HttpSession session = request.getSession();
		if(session.getAttribute("userId") == null) {
			response.sendRedirect(request.getContextPath()+"/user/loginForm");
			return false;
		}
		
		return true;
	}
}
