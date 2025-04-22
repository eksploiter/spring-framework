package com.ssafy.shopApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.shopApp.model.service.IUserService;
import com.ssafy.shopApp.model.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@RequestMapping("/user")
@RequiredArgsConstructor
@Controller
public class UserController {
	
	private final IUserService userService;
	
	@PostMapping("/login")
	protected String login(
			@RequestParam(name="userId") String userId, 
			@RequestParam(name="password") String password, 
			HttpSession session, RedirectAttributes rAttributes) 
			throws Exception {

		ModelAndView mav = new ModelAndView();
		
		// 2. call model
		String name = userService.login(userId, password);
		if(name != null) {
			session.setAttribute("userId", userId);
			session.setAttribute("name", name);
		}else {
			rAttributes.addFlashAttribute("loginError", "아이디 또는 비밀번호가 일치하지 않습니다.");
			return "redirect:/user/loginForm"; // flash attribute 
		}
		// view
		return "redirect:/main";
	}
	
	@GetMapping("/logout")
	protected String logout(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		request.getSession().invalidate();
		// view
		return "redirect:/main?action=home";
	}

	@GetMapping("/loginForm")
	String loginForm() {
		return "user/login";
	}
}
