package com.ssafy.live.controller;

import java.sql.SQLException;
import java.util.Map;

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
import com.ssafy.live.model.service.BasicMemberService;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;


// 컨트롤 + 쉬프트 + O
// TODO: 10-3. @Controller로 변경하고 service의 case에 해당하는 handler 메서드를 작성하세요.
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController extends HttpServlet implements ControllerHelper {
    private static final long serialVersionUID = 1L;

    private final BasicMemberService mService;

//    @Override
//    protected void service(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String action = preProcessing(request, response);
//        switch (action) {
//        case "regist-member-form" -> forward(request, response, "/member/member-regist-form.jsp");
//        case "login-form" -> forward(request, response, "/member/login-form.jsp");
//        case "regist-member" -> registMember(request, response);
//        case "login" -> login(request, response);
//        case "logout" -> logout(request, response);
//        case "checkEmail" -> checkEmailDuplicate(request, response);
//        default -> response.sendError(HttpServletResponse.SC_NOT_FOUND);
//        }
//    }
    
    @GetMapping("/resist-member-form")
    private String resistMemberForm() {
    	return "member/member-regist-form";
    }
    
    @GetMapping("/login-form")
    private String loginForm() {
        return "member/login-form";
    }
    
    @PostMapping("/regist-member")
    private String registMember(@ModelAttribute Member member, RedirectAttributes redir, Model model) {
        try {
            mService.registMember(member);
            String message = "등록되었습니다. 로그인 후 사용해주세요.";
            redir.addFlashAttribute("alertMsg", message);
            return "redirect:/";
        } catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            return "member/member-resist-form";
        }
    }

    @PostMapping("login")
    private String login(@RequestParam String email, 
    		@RequestParam("password") String pass, 
    		@RequestParam(value = "remember-me", required = false) String rememberMe,
    		// "remember-me" 로 만 사용하는 것은 값이 한 개일 때 "value =" 생략 가능
    		HttpSession session,
    		HttpServletResponse response,
    		Model model) {
        //String email = request.getParameter("email"); -> email 이름이 어차피 같으므로 지워도 됨 
        //String pass = request.getParameter("password"); 
        // 나는 pass 라고 만들었는데 화면에서는 password 라고 온다. -> 둘 간의 불일치 -> 전달되는 파라미터를 바꿔줘야겠네? -> @RequestParam("password")
        //String rememberMe = request.getParameter("remember-me");
    	
        try {
            Member member = mService.login(email, pass);
            //request.getSession().setAttribute("loginUser", member); ->
            session.setAttribute("loginUser", member);
            if (rememberMe == null) { 
            	// Null 일 수도 있도 아닐 수도 있다 -> @RequestParam("remember-me") String rememberMe 이거 옵셔널할 수 있다.
                setupCookie("remember-me", "bye", 0, null, response);
            } else {
                setupCookie("remember-me", email, 60 * 60 * 24 * 365, null, response);
            }
            //redirect(request, response, "/");
            return "redirect:/";
        } catch (RecordNotFoundException | SQLException e) {
            e.printStackTrace();
            //request.setAttribute("alertMsg", e.getMessage());
            model.addAttribute("alertMsg", e.getMessage());
            //forward(request, response, "/member/login-form.jsp");
            return "member/login-form";
        }
    }

    @GetMapping("/logout")
    private String logout(HttpSession session) {
        //request.getSession().invalidate();
    	session.invalidate();
        //redirect(request, response, "/");
    	return "redirect:/";
    }

    @GetMapping("/checkEmail")
    @ResponseBody
    private Map<String, ?> checkEmailDuplicate(@RequestParam String email) {
        try {
            //String email = request.getParameter("email");
            Member selected = mService.selectDetail(email);
            //Map<String, Boolean> result = Map.of("canUse", selected == null);
            return Map.of("canUse", selected == null);
            //toJSON(result, response);
        } catch (SQLException e) {
            e.printStackTrace();
            //throw new ServletException(e);
            return Map.of("error", e.getMessage());
        }
    }
}
