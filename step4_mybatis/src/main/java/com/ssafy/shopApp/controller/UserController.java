package com.ssafy.shopApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.shopApp.model.dto.UserDTO;
import com.ssafy.shopApp.model.service.IUserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequestMapping("/user")
@RequiredArgsConstructor
@Controller
public class UserController {

	private final IUserService userService;

	@PostMapping("/login")
	protected String login(@RequestParam String userId, @RequestParam String password, HttpSession session,
			RedirectAttributes rAttributes) throws Exception {
 
		String name = userService.login(userId, password);
		if (name != null) {
			session.setAttribute("userId", userId);
			session.setAttribute("name", name);
		} else {
			rAttributes.addFlashAttribute("loginError", "아이디 또는 비밀번호가 일치하지 않습니다.");
			return "redirect:/user/loginForm"; // flash attribute
		}
		return "redirect:/main";
	}

	@GetMapping("/logout")
	protected String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getSession().invalidate();
		return "redirect:/main?action=home";
	}

	@PostMapping("/register")
	protected String register(UserDTO user) throws Exception {
		userService.register(user);
		return "user/login";
	}

	@GetMapping("/myPage")
	protected String myPage(Model model, HttpSession session) throws Exception {
		String userId = (String) session.getAttribute("userId");
		System.out.println(userId);
		model.addAttribute("user", userService.getUserById(userId));
		return "user/myPage";
	}

	@GetMapping("/loginForm")
	String loginForm() {
		return "user/login";
	}

	@GetMapping("/registerForm")
	String registerForm() {
		return "user/register";
	}
}
