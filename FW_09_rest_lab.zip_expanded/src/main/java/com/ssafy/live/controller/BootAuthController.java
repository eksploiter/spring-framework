package com.ssafy.live.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.live.model.dto.Address;
import com.ssafy.live.model.dto.Member;
import com.ssafy.live.model.dto.Page;
import com.ssafy.live.model.dto.SearchCondition;
import com.ssafy.live.model.service.AddressService;
import com.ssafy.live.model.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class BootAuthController implements ControllerHelper {

    private final MemberService mService;
    private final AddressService aService;
    
    @Value("${api.key_sgis_service_id}")
    private String key_sgis_service_id;
    @Value("${api.key_sgis_security}")
    private String key_sgis_security;
    
    @GetMapping("/member-detail")
    private String memberDetail(@RequestParam String email, Model model) {
        try {
            Member member = mService.selectDetail(email);
            model.addAttribute("member", member);
            model.addAttribute("key_sgis_service_id", key_sgis_service_id);
            model.addAttribute("key_sgis_security", key_sgis_security);
        } catch (DataAccessException e) {
            e.printStackTrace();
            model.addAttribute("alertMsg", e.getMessage());
        }
        return "member/member-detail";
    }

    @GetMapping("/member-list")
    private String memberList(@ModelAttribute SearchCondition condition, Model model) {
        Map<String, String> keyMap = Map.of("1", "name", "2", "email");
        String key = condition.getKey();
        if (key != null) {
            condition.setKey(keyMap.getOrDefault(key, ""));
        }
        try {
            Page<Member> page = mService.search(condition);
            model.addAttribute("page", page);
        } catch (DataAccessException e) {
            e.printStackTrace();
            model.addAttribute("alertMsg", e.getMessage());
        }
        return "member/member-list";
    }

    @GetMapping("/member-modify-form")
    private String memberModifyForm(@RequestParam String email, Model model, RedirectAttributes redirs) {
        try {
            Member member = mService.selectDetail(email);
            model.addAttribute("member", member);
            return "member/member-modify-form";
        } catch (DataAccessException e) {
            e.printStackTrace();
            redirs.addFlashAttribute("alertMsg", e.getMessage());
            return "redirect:/auth/member-detail?email=" + email;
        }
    }

    @PostMapping("/member-modify")
    private String memberModify(@ModelAttribute Member member, HttpSession session, RedirectAttributes redirs) {

        try {
            mService.update(member);
            if (((Member) session.getAttribute("loginUser")).getEmail().equals(member.getEmail())) {
                session.setAttribute("loginUser", member);
            }
            return "redirect:/auth/member-detail?email=" + member.getEmail();
        } catch (DataAccessException e) {
            e.printStackTrace();
            redirs.addFlashAttribute("alertMsg", e.getMessage());
            return "redirect:/auth/member-detail?email=" + member.getEmail();
        }
    }

    @PostMapping("/address-insert")
    @ResponseBody
    private Object addressInsert(@ModelAttribute Address addr, @RequestParam String email) {
        try {
            aService.registAddress(addr);
            Member member = mService.selectDetail(email);
            return member.getAddresses();
        } catch (DataAccessException e) {
            e.printStackTrace();
            return Map.of("error", e.getMessage());
        }
    }

    @PostMapping("/address-delete")
    @ResponseBody
    private Object addressDelete(@RequestParam int ano, @RequestParam String email) {
        try {
            aService.deleteAddress(ano);
            Member member = mService.selectDetail(email);
            return member.getAddresses();
        } catch (DataAccessException e) {
            e.printStackTrace();
            return Map.of("error", e.getMessage());
        }
    }

    @PostMapping("/member-delete")
    private String memberDelete(@RequestParam int mno, @RequestParam String email, HttpSession session,
            RedirectAttributes redirs) {
        try {
            mService.delete(mno);
            if (session.getAttribute("loginUser") instanceof Member m && m.getMno() == mno) {
                session.invalidate();
                return "redirect:/";
            } else {
                redirs.addFlashAttribute("alertMsg", "삭제되었습니다.");
                return "redirect:/auth/member-list?currentPage=1";
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
            redirs.addFlashAttribute("alertMsg", e.getMessage());
            return "redirect:/auth/member-detail?email=" + email;
        }
    }

    @PostMapping("/profileajax")
    @ResponseBody
    private Object profileAjax(@RequestParam MultipartFile file, @RequestParam String email) {
        try {
            mService.updateProfile(email, file.getBytes());
            return Map.of("img", "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (DataAccessException | IOException e) {
            e.printStackTrace();
            return Map.of("error", e.getMessage());
        }
    }
}
