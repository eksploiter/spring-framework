package com.ssafy.live.security.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/secured")
@Slf4j
public class SecureController {

    @GetMapping("/admin")
    // TODO: 09-2. Controller 영역에서의 UserDetails 정보 활용을 살펴보세요.
    public String admin(@AuthenticationPrincipal UserDetails details) {
        if (details != null) {
            log.debug("로그인 정보: {}", details);
        }
        return "secured/admin/admin";
    }

    @GetMapping("/user")
    public String user() {
        return "secured/user/user";
    }
}
