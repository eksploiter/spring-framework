package com.ssafy.live.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
// TODO: 12-1. 기존의 SessionFilter를 대체해보자.
public class SessionInterceptor implements HandlerInterceptor {} 

// END
