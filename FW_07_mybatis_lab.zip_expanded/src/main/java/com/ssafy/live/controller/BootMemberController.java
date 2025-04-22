package com.ssafy.live.controller;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.live.exception.RecordNotFoundException;
import com.ssafy.live.model.dto.Member;
import com.ssafy.live.model.service.MemberService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class BootMemberController implements ControllerHelper {

    private final MemberService mService;

    @GetMapping("/regist-member-form")
    private String registMemberForm() {
        return "member/member-regist-form";
    }

    @GetMapping("/login-form")
    private String loginForm() {
        return "member/login-form";
    }

    @PostMapping("/login")
    private String login(@RequestParam String email, @RequestParam String password,
            @RequestParam(value = "remember-me", required = false) String rememberMe, HttpSession session, HttpServletResponse response,
            Model model) {
        try {
            Member member = mService.login(email, password);
            session.setAttribute("loginUser", member);
            if (rememberMe == null) {
                setupCookie("remember-me", "bye", 0, null, response);
            } else {
                setupCookie("remember-me", email, 60 * 60 * 24 * 365, null, response);
            }
            return "redirect:/";
        } catch (RecordNotFoundException | DataAccessException e) {
            e.printStackTrace();
            model.addAttribute("alertMsg", e.getMessage());
            return "member/login-form";
        }
    }

    @GetMapping("/logout")
    private String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/regist-member")
    private String registMember(@ModelAttribute Member member, Model model, RedirectAttributes redirs) {
        try {
            mService.registMember(member);
            String message = "등록되었습니다. 로그인 후 사용해주세요.";
            redirs.addFlashAttribute("alertMsg", message);
            return "redirect:/";
        } catch (DataAccessException e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            return "member/member-regist-form";
        }
    }

    @GetMapping("/checkEmail")
    @ResponseBody
    private Map<String, ?> checkEmailDuplicate(@RequestParam String email) {
        try {
            Member selected = mService.selectDetail(email);
            return Map.of("canUse", selected == null);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return Map.of("error", e.getMessage());
        }
    }

}
