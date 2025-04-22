package com.ssafy.live.mvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.ssafy.live.model.dto.Address;
import com.ssafy.live.model.dto.Member;
import com.ssafy.live.model.dto.Page;
import com.ssafy.live.model.dto.SearchCondition;
import com.ssafy.live.model.service.AddressService;
import com.ssafy.live.model.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
 //@WebMvcTest(value = AuthController.class)
public class AuthControllerTest {

    @Autowired
    MockMvc mock;

    @MockitoBean
    MemberService mService;

    @MockitoBean
    AddressService aService;

    private Member createTestMember(String email) {
        return Member.builder().email(email).password("password").name("테스터").build();
    }

    private MockHttpServletRequestBuilder sessionRequest(String path, String method) {
        Member loginUser = createTestMember("admin@ssafy.com");
        MockHttpServletRequestBuilder builder = method.equals("GET") ? MockMvcRequestBuilders.get(path) : MockMvcRequestBuilders.post(path);
        builder.sessionAttr("loginUser", loginUser);
        return builder;
    }

    @Test
    void memberDetail_get요청_성공_model에member와api키존재_viewName_member_detail() throws Exception {
        // Given
        String email = "test@test.com";
        Member member = createTestMember(email);
        when(mService.selectDetail(email)).thenReturn(member);
        var request = sessionRequest("/auth/member-detail", "GET").param("email", email);
        // When & Then
        mock.perform(request).andExpect(status().isOk())
                .andExpect(model().attribute("member", member))
                .andExpect(view().name("member/member-detail"));
    }

    @Test
    void memberDetail_get요청_실패_SQLException_model에alertMsg존재_viewName_member_detail() throws Exception {
        // Given
        String email = "test@test.com";
        when(mService.selectDetail(email)).thenThrow(new DataIntegrityViolationException("DB 조회 오류"));
        var request = sessionRequest("/auth/member-detail", "GET").param("email", email);
        // When & Then
        mock.perform(request).andExpect(status().isOk()).andExpect(model().attribute("alertMsg", "DB 조회 오류"))
                .andExpect(view().name("member/member-detail"));
    }

    @Test
    void memberList_get요청_key존재_성공_model에page존재_viewName_member_list() throws Exception {
        // Given
        String email = "test@test.com";
        SearchCondition condition = new SearchCondition("1", "테스터", 1);
        Page<Member> page = new Page<>(condition, 1, List.of(createTestMember(email)));
        when(mService.search(any(SearchCondition.class))).thenReturn(page);
        var request = sessionRequest("/auth/member-list", "GET").param("key", "1").param("word", "테스터").param("currentPage", "1");

        // When & Then
        mock.perform(request).andExpect(status().isOk()).andExpect(model().attribute("page", page))
                .andExpect(view().name("member/member-list"));
    }

    @Test
    void memberModifyForm_get요청_성공_model에member존재_viewName_member_modify_form() throws Exception {
        // Given
        String email = "test@test.com";
        Member member = createTestMember(email);
        when(mService.selectDetail(email)).thenReturn(member);
        var request = sessionRequest("/auth/member-modify-form", "GET").param("email", email);
        // When & Then
        mock.perform(request).andExpect(status().isOk()).andExpect(model().attribute("member", member))
                .andExpect(view().name("member/member-modify-form"));
    }

    @Test
    void memberModifyForm_get요청_실패_SQLException_redirect_memberDetail_alertMsg존재() throws Exception {
        // Given
        String email = "test@test.com";
        when(mService.selectDetail(email)).thenThrow(new DataIntegrityViolationException("DB 조회 오류"));
        var request = sessionRequest("/auth/member-modify-form", "GET").param("email", email);
        // When & Then
        mock.perform(request).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/auth/member-detail?email=" + email))
                .andExpect(flash().attribute("alertMsg", "DB 조회 오류"));
    }

    @Test
    void memberModify_post요청_성공_redirect_memberDetail_session업데이트() throws Exception {
        // Given
        String email = "test@test.com";
        var request = sessionRequest("/auth/member-modify", "POST").param("email", email).param("name", "hong")
                .param("password", "1234");
        doNothing().when(mService).update(any(Member.class)); // void 메서드는 doNothing() 사용
        // When & Then
        mock.perform(request).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/auth/member-detail?email=" + email));
    }

    @Test
    void memberModify_post요청_성공_다른회원수정_redirect_memberDetail_session유지() throws Exception {
        // Given
        String modifyEmail = "modify@test.com";
        Member modifyMember = createTestMember(modifyEmail);
        doNothing().when(mService).update(any(Member.class)); // void 메서드는 doNothing() 사용

        var request = sessionRequest("/auth/member-modify", "POST").param("email", modifyEmail)
                .param("name", "테스터").param("password", "password");
        // When & Then
        mock.perform(request).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/auth/member-detail?email=" + modifyEmail))
                .andExpect(request().sessionAttribute("loginUser", createTestMember("admin@ssafy.com")));
        verify(mService, times(1)).update(modifyMember);
    }

    @Test
    void memberModify_post요청_실패_SQLException_redirect_memberDetail_alertMsg존재() throws Exception {
        // Given
        String email = "test@test.com";
        doThrow(new DataIntegrityViolationException("DB 수정 오류")).when(mService).update(any(Member.class));

        var request = sessionRequest("/auth/member-modify", "POST").param("email", email)
                .param("name", "테스터").param("password", "password");
        // When & Then
        mock.perform(request).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/auth/member-detail?email=" + email))
                .andExpect(flash().attribute("alertMsg", "DB 수정 오류"));
    }

    @Test
    void memberDelete_post요청_로그인회원삭제성공_redirect_root_session무효화() throws Exception {
        // Given
        String email = "test@test.com";
        Member member = createTestMember(email);
        doNothing().when(mService).delete(member.getMno());
        var request = sessionRequest("/auth/member-delete", "POST").param("mno", String.valueOf(member.getMno()))
                .param("email", email);
        // When & Then
        mock.perform(request).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/"))
                .andExpect(request().sessionAttributeDoesNotExist("loginUser"));
        verify(mService, times(1)).delete(member.getMno());
    }

    @Test
    void memberDelete_post요청_다른회원삭제성공_redirect_memberList_alertMsg존재() throws Exception {
        // Given
        int mno = 2;
        String email = "other@test.com";
        Member member = createTestMember(email);
        doNothing().when(mService).delete(mno);
        var request = sessionRequest("/auth/member-delete", "POST").param("mno", String.valueOf(mno))
                .param("email", email).sessionAttr("loginUser", member);

        // When & Then
        mock.perform(request).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/auth/member-list?currentPage=1"))
                .andExpect(flash().attribute("alertMsg", "삭제되었습니다."));
        verify(mService, times(1)).delete(mno);
    }

    @Test
    void memberDelete_post요청_실패_SQLException_redirect_memberDetail_alertMsg존재() throws Exception {
        // Given
        int mno = 2;
        String email = "other@test.com";
        Member member = createTestMember(email);
        doThrow(new DataIntegrityViolationException("DB 수정 오류")).when(mService).delete(mno);
        var request = sessionRequest("/auth/member-delete", "POST").param("mno", String.valueOf(mno))
                .param("email", email).sessionAttr("loginUser", member);

        // When & Then
        mock.perform(request).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/auth/member-detail?email=" + email))
                .andExpect(flash().attribute("alertMsg", "DB 수정 오류"));
        verify(mService, times(1)).delete(mno);
    }

    @Test
    void addressInsert_post요청_성공_ResponseBody_memberAddresses반환() throws Exception {
        // Given
        ObjectMapper mapper = new ObjectMapper();
        String email = "test@test.com";
        Address newAddress = Address.builder().address("서울").detailAddress("강남").build();
        Member member = createTestMember(email);
        member.setAddresses(List.of(newAddress));
        doNothing().when(aService).registAddress(any(Address.class));
        when(mService.selectDetail(email)).thenReturn(member);

        var request = sessionRequest("/auth/address-insert", "POST").param("email", email).param("address", "서울")
                .param("detailAddress", "강남");
        // When & Then
        mock.perform(request).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(mapper.writeValueAsString(member.getAddresses())));
        verify(aService, times(1)).registAddress(newAddress);
        verify(mService, times(1)).selectDetail(email);
    }

    @Test
    void addressInsert_post요청_실패_SQLException_ResponseBody_error반환() throws Exception {
        // Given
        String email = "test@test.com";
        doThrow(new DataIntegrityViolationException("DB 주소 등록 오류")).when(aService).registAddress(any(Address.class));
        var request = sessionRequest("/auth/address-insert", "POST").param("email", email).param("address", "서울")
                .param("detailAddress", "강남");
        // When & Then
        mock.perform(request).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error").value("DB 주소 등록 오류"));
    }

    @Test
    void addressDelete_post요청_성공_ResponseBody_memberAddresses반환() throws Exception {
        // Given
        int ano = 1;
        String email = "test@test.com";
        Member member = createTestMember(email);
        member.setAddresses(List.of());
        doNothing().when(aService).deleteAddress(ano);
        when(mService.selectDetail(email)).thenReturn(member);
        var request = sessionRequest("/auth/address-delete", "POST").param("email", email)
                .param("ano", String.valueOf(ano));

        // When & Then
        mock.perform(request).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(aService, times(1)).deleteAddress(ano);
        verify(mService, times(1)).selectDetail(email);
    }
}
