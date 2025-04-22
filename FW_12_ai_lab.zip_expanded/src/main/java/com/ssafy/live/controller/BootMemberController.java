package com.ssafy.live.controller;

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

import com.ssafy.live.model.dto.Member;
import com.ssafy.live.model.service.MemberService;

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
    private String loginForm(Model model, @RequestParam(required = false) String error) {
        if (error != null) {
            model.addAttribute("alertMsg", "email/password를 확인하세요.");
        }
        return "member/login-form";
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
