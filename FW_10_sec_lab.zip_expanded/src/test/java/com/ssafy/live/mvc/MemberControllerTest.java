package com.ssafy.live.mvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ssafy.live.controller.BootMemberController;
import com.ssafy.live.exception.RecordNotFoundException;
import com.ssafy.live.model.dto.Member;
import com.ssafy.live.model.service.BasicMemberService;
import com.ssafy.live.security.ByPassingSecurityConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebMvcTest(controllers = BootMemberController.class)
@WithMockUser(username = "test@test.com", roles = { "USER" })
@Import(ByPassingSecurityConfig.class)
public class MemberControllerTest {

    @Autowired
    MockMvc mock;
    


    @MockitoBean
    BasicMemberService mService;

    @Test
    void registMemberForm_get요청_viewName_member_regist_form() throws Exception {
        mock.perform(MockMvcRequestBuilders.get("/member/regist-member-form")).andExpect(status().isOk())
                .andExpect(view().name("member/member-regist-form"));
    }

    @Test
    void loginForm_get요청_viewName_member_login_form() throws Exception {
        mock.perform(MockMvcRequestBuilders.get("/member/login-form")).andExpect(status().isOk())
                .andExpect(view().name("member/login-form"));
    }

    @Test
    void registMember_post요청_성공_redirect_root_alertMsg() throws Exception {
        // Given
        Member member = Member.builder().email("test@test.com").password("1234").name("테스터").build();
        when(mService.registMember(any(Member.class))).thenReturn(1);

        // When & Then
        mock.perform(MockMvcRequestBuilders.post("/member/regist-member").flashAttr("member", member))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/"))
                .andExpect(flash().attribute("alertMsg", "등록되었습니다. 로그인 후 사용해주세요."));
    }

    @Test
    void registMember_post요청_실패_SQLException_forward_registForm_errorAttr() throws Exception {
        // Given
        Member member = Member.builder().email("test@test.com").password("1234").name("테스터").build();
        when(mService.registMember(any(Member.class))).thenThrow(new DataIntegrityViolationException("DB 등록 오류"));

        // When & Then
        mock.perform(MockMvcRequestBuilders.post("/member/regist-member").flashAttr("member", member))
                .andExpect(status().isOk()).andExpect(forwardedUrl("/WEB-INF/views/member/member-regist-form.jsp"))
                .andExpect(model().attribute("error", "DB 등록 오류"));
    }

    // @Test : 이런 login은 없어짐.
    void login_post요청_성공_rememberMe_null_redirect_root_session_cookie_삭제() throws Exception {
        // Given
        String email = "test@test.com";
        String password = "1234";
        Member loginMember = Member.builder().email(email).password(password).name("테스터").build();
        when(mService.login(email, password)).thenReturn(loginMember);

        // When & Then
        mock.perform(MockMvcRequestBuilders.post("/member/login").param("email", email).param("password", password))
                .andExpect(status().is3xxRedirection())
                .andExpect(request().sessionAttribute("loginUser", CoreMatchers.notNullValue()))
                .andExpect(redirectedUrl("/")).andExpect(cookie().exists("remember-me"))
                .andExpect(cookie().maxAge("remember-me", 0));
    }

    // @Test : 이런 login은 없어짐.
    void login_post요청_성공_rememberMe_존재_redirect_root_session_cookie_생성() throws Exception {
        // Given
        String email = "test@test.com";
        String password = "1234";
        Member loginMember = Member.builder().email(email).password(password).name("테스터").build();
        when(mService.login(email, password)).thenReturn(loginMember);

        // When & Then
        mock.perform(MockMvcRequestBuilders.post("/member/login").param("email", email).param("password", password)
                .param("remember-me", "on")).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/"))
                .andExpect(request().sessionAttribute("loginUser", CoreMatchers.notNullValue()))
                .andExpect(cookie().exists("remember-me")).andExpect(cookie().value("remember-me", email))
                .andExpect(cookie().maxAge("remember-me", 60 * 60 * 24 * 365));
    }

    // @Test : 이런 login은 없어짐.
    void login_post요청_실패_RecordNotFoundException_forward_loginForm_alertMsg() throws Exception {
        // Given
        String email = "test@test.com";
        String password = "wrong_password";
        when(mService.login(email, password)).thenThrow(new RecordNotFoundException("id/pass 확인"));

        // When & Then
        mock.perform(MockMvcRequestBuilders.post("/member/login").param("email", email).param("password", password))
                .andExpect(status().isOk()).andExpect(forwardedUrl("/WEB-INF/views/member/login-form.jsp"))
                .andExpect(model().attribute("alertMsg", "id/pass 확인")).andDo(print());
    }

    // @Test : 이런 logout은 없어짐.
    void logout_get요청_redirect_root_session_invalidate() throws Exception {
        // Given
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("loginUser",
                Member.builder().name("test").password("1234").email("test@test.com").build());

        // When & Then
        mock.perform(MockMvcRequestBuilders.get("/member/logout").session(session))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/"))
                .andExpect(request().sessionAttributeDoesNotExist("loginUser"));
    }

    @Test
    void checkEmailDuplicate_get요청_중복없음_canUse_true() throws Exception {
        // Given
        String email = "test@test.com";
        when(mService.selectDetail(email)).thenReturn(null);

        // When & Then
        mock.perform(MockMvcRequestBuilders.get("/member/checkEmail").param("email", email)).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.canUse").value(true));
    }

    @Test
    void checkEmailDuplicate_get요청_중복있음_canUse_false() throws Exception {
        // Given
        String email = "test@test.com";
        when(mService.selectDetail(email))
                .thenReturn(Member.builder().email(email).name("hong").password("1234").build());

        // When & Then
        mock.perform(MockMvcRequestBuilders.get("/member/checkEmail").param("email", email)).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.canUse").value(false));
    }

    @Test
    void checkEmailDuplicate_get요청_SQLException_error_message() throws Exception {
        // Given
        String email = "test@test.com";
        when(mService.selectDetail(email)).thenThrow(new DataIntegrityViolationException("DB 조회 오류"));

        // When & Then
        mock.perform(MockMvcRequestBuilders.get("/member/checkEmail").param("email", email)).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error").value("DB 조회 오류"));
    }

}


