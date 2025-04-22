package com.ssafy.live.mvc.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.live.model.dto.Member;
import com.ssafy.live.mvc.service.SimpleService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// TODO: 02-1. /simple의 하위 요청을 처리하도록 설정하세요.
@Slf4j
public class SimpleController {
    // TODO: 02-2. /simple/forward 요청이 왔을 때 SimpleService의 helloMVC()를 호출하세요.
    //  결과는 simple.jsp에서 보여주는데 request scope에 data라는 attribute로 helloMVC()의 반환값을 전달한다.

    // END

    // TODO: 03-1. /redirect 요청이 왔을 때 redirect로 forward를 호출하세요.

    // END

    // TODO: 03-2. /json 요청이 왔을 때 Map 객체를 반환하세요.

    // END

    // TODO: 05-1. /logout요청이 오면 session에서 loginUser 값을 출력해보고 logoug 처리해보자.
    //  응답으로는 @ResponseBody로 {"loginUser": session_value}를 반환한다.

    // END

    @GetMapping("/calc")
    // TODO: 06-1. 2개의 int num1, num2와 사칙 연산자 oper이 파라미터로 받아 연산 결과를 반환해보자.
    //  연산 결과는 Map타입으로 하고 key는 result로 한다.
     public void calc(){}

    // END

    // TODO: 07-1. 전달된 파라미터를 이용해서 Member를 구성하고 Map에 {"result":member}로 반환해보자.
    @GetMapping("/regist")
    public @ResponseBody Map<String, Object> regist(@RequestParam String name, @RequestParam String email,
            @RequestParam String password, Model model) {
        Member member = new Member(name, email, password);
        log.debug("member: {}", member);
        model.addAttribute("member", member);
        log.debug("model: {}", model.getAttribute("member"));
        return Map.of("result", member);
    }

    // TODO: 08-1. 전달된 쿠키에서 name, age을 추출해서 Map으로 반환해보자.
    @GetMapping("/cookie")
    public @ResponseBody Map<String, Object> cookie(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        Map<String, Object> data = new HashMap<>();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("name")) {
                    data.put("name", c.getValue());
                } else if (c.getName().equals("age")) {
                    data.put("age", Integer.parseInt(c.getValue()));
                }
            }
        }
        return data;
    }

    // TODO: 09-1. redirect 시 추가 데이터 전달을 위해서 RedirectAttributes를 사용해보자.
    @GetMapping("/redir-attr")
    public String redirAttribute(RedirectAttributes redirs, HttpSession session) {
        session.setAttribute("bySession", "annoying");
        redirs.addAttribute("byParam", "dirty");
        redirs.addFlashAttribute("byFlash", "SSG");
        return "redirect:/simple/forward";
    }

    @GetMapping("/problem")
    public String problem() {
        throw new RuntimeException("일반적인 요청의 경우");
    }

    @GetMapping("/jsonproblem")
    @ResponseBody
    public Map<String, Object> jsonproblem() {
        throw new RuntimeException("json 요청의 경우");
    }

    @Value("${spring.servlet.multipart.location}")
    String filePath;

    // TODO: 14-3. 파일 업로드를 위한 controller 동작을 확인하세요.
    @PostMapping("/fileupload")
    public String fileUpload(@RequestParam MultipartFile file, @RequestParam(required = false) String desc,
            RedirectAttributes redir) {
        File localFile = new File(filePath, file.getOriginalFilename());
        try {
            file.transferTo(localFile);
            redir.addFlashAttribute("file", file.getOriginalFilename());
            redir.addFlashAttribute("desc", desc);
            redir.addFlashAttribute("alertMsg", "등록되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }
}
