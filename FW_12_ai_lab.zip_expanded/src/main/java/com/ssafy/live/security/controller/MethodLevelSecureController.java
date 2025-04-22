package com.ssafy.live.security.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/methodlevelSecure")
public class MethodLevelSecureController {

    @GetMapping("/user")
    @Secured("ROLE_USER")
    public String forUser() {
        return "secured/user/user";
    }

    @GetMapping("/admin")
    @Secured("ROLE_ADMIN")
    public String forAdmin() {
        return "secured/admin/admin";
    }
}
