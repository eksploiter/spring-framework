package com.ssafy.live.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BootMainController implements ControllerHelper {

    @Value("${api.key_vworld}")
    private String keyVWorld;
    
    @GetMapping({ "/", "/index" })
    public String index(Model model) {
        model.addAttribute("key_vworld", keyVWorld);
        return "index";
    }
}
