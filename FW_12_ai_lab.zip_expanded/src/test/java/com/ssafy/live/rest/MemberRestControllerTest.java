package com.ssafy.live.rest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.InputStream;
import java.util.Base64;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.live.model.dto.Address;
import com.ssafy.live.model.dto.Member;
import com.ssafy.live.model.dto.Page;
import com.ssafy.live.model.dto.SearchCondition;
import com.ssafy.live.model.service.BasicAddressService;
import com.ssafy.live.model.service.BasicMemberService;
import com.ssafy.live.restcontroller.AuthRestController;
import com.ssafy.live.restcontroller.MemberRestController;
import com.ssafy.live.security.ByPassingSecurityConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebMvcTest(controllers = { MemberRestController.class, AuthRestController.class })
@WithMockUser(username = "test@test.com", roles = { "USER" })
@Import(ByPassingSecurityConfig.class)
public class MemberRestControllerTest {

    @Autowired
    MockMvc mock;

    @MockitoBean
    BasicMemberService mService;

    @MockitoBean
    BasicAddressService aService;

    @Test
    public void registMemberTest() throws Exception {
        // given
        Member member = Member.builder().name("name").email("email").password("pass").build();
        ObjectMapper mapper = new ObjectMapper();
        RequestBuilder builder = MockMvcRequestBuilders.post("/api/v1/members").contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(member));
        // when & then
        when(mService.registMember(member)).thenReturn(1);
        mock.perform(builder).andExpect(status().isCreated()).andExpect(jsonPath("$.status", equalTo("SUCCESS")))
                .andExpect(jsonPath("$.data", notNullValue())).andExpect(jsonPath("$.error").doesNotExist());
    }

    @Test
    public void memberDetail() throws Exception {
        // given
        RequestBuilder builder = MockMvcRequestBuilders.get("/api/v1/members/admin@ssafy.com");
        Member member = Member.builder().name("관리자").email("email").password("pass").build();
        // when & then
        when(mService.selectDetail("admin@ssafy.com")).thenReturn(member);
        mock.perform(builder).andExpect(status().isOk()).andExpect(jsonPath("$.status", equalTo("SUCCESS")))
                .andExpect(jsonPath("$.data.result.name", equalTo("관리자")));

        // given
        builder = MockMvcRequestBuilders.get("/api/v1/members/notexist@ssafy.com");
        when(mService.selectDetail("notexist@ssafy.com")).thenReturn(null);
        mock.perform(builder).andExpect(status().isNoContent()).andExpect(jsonPath("$.status", equalTo("SUCCESS")))
                .andExpect(jsonPath("$.data.result", equalTo("notexist@ssafy.com")));
    }

    @Test
    public void memberListTest() throws Exception {
        // given
        RequestBuilder builder = MockMvcRequestBuilders.get("/api/v1/members")
                .param("key", "email")
                .param("word", "01")
                .param("currentPage", "1");
        Member member1 = Member.builder().name("관리자").email("admin@ssafy.com").password("pass").build();
        Member member2 = Member.builder().name("019").email("019@ssafy.com").password("pass").build();
        SearchCondition condition = new SearchCondition("email", "01", 1);
        Page<Member> page = new Page<>(condition, 2, List.of(member1, member2));
        // when & then
        when(mService.search(condition)).thenReturn(page);

        mock.perform(builder).andExpect(status().isOk()).andExpect(jsonPath("$.status", equalTo("SUCCESS")))
                .andExpect(jsonPath("$.data.result.list.length()", equalTo(2)))
                .andExpect(jsonPath("$.data.result.list[1].email", equalTo("019@ssafy.com")));

    }

    @Test
    public void memberModifyTest() throws Exception {
        // given
        Member member = Member.builder().name("관리자").email("email").password("pass").build();
        ObjectMapper mapper = new ObjectMapper();
        RequestBuilder builder = MockMvcRequestBuilders.put("/api/v1/members").contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(member));
        // when & then
        doNothing().when(mService).update(member);
        mock.perform(builder).andExpect(status().isOk()).andExpect(jsonPath("$.status", equalTo("SUCCESS")))
                .andExpect(jsonPath("$.data.result.name", equalTo("관리자")));

    }

    @Test
    public void memberDeleteTest() throws Exception {
        // given
        RequestBuilder builder = MockMvcRequestBuilders.delete("/api/v1/members/1");

        // when & then
        doNothing().when(mService).delete(1);
        mock.perform(builder).andExpect(status().isOk()).andExpect(jsonPath("$.status", equalTo("SUCCESS")))
                .andExpect(jsonPath("$.data.result", equalTo(1)));
    }

    @Test
    public void addAddressTest() throws Exception {
        // given
        Address address = Address.builder().title("title").mno(1).address("addr").detailAddress("detail").build();
        ObjectMapper mapper = new ObjectMapper();
        RequestBuilder builder = MockMvcRequestBuilders.post("/api/v1/members/admin@ssafy.com/addresses")
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(address));
        Member member = Member.builder().name("관리자").email("admin@ssafy.com").password("pass")
                .addresses(List.of(address)).build();

        // then & then
        doNothing().when(aService).registAddress(address);
        when(mService.selectDetail(member.getEmail())).thenReturn(member);

        mock.perform(builder).andExpect(status().isOk()).andExpect(jsonPath("$.status", equalTo("SUCCESS")))
                .andExpect(jsonPath("$.data.result.length()", equalTo(1)));
    }

    @Test
    public void addressDeleteTest() throws Exception {
        // given
        Address address = Address.builder().ano(1).title("title").mno(1).address("addr").detailAddress("detail").build();
        RequestBuilder builder = MockMvcRequestBuilders.delete("/api/v1/members/admin@ssafy.com/addresses/1");
        Member member = Member.builder().name("관리자").email("admin@ssafy.com").password("pass")
                .addresses(List.of(address)).build();
        // when & then
        //doNothing().when(aService).deleteAddress(1);
        when(mService.selectDetail("admin@ssafy.com")).thenReturn(member);
        mock.perform(builder).andExpect(status().isOk()).andExpect(jsonPath("$.status", equalTo("SUCCESS")))
                .andExpect(jsonPath("$.data.result.length()", equalTo(1)));
    }

    @Test
    public void profileUpdate() throws Exception {
        // 파일을 로드하기 위한 InputStream
        InputStream is = MemberRestControllerTest.class.getResourceAsStream("MemberRestControllerTest.class");
        byte[] bytes = is.readAllBytes();
        String base64 = Base64.getEncoder().encodeToString(bytes);

        // MockMultipartFile 생성
        MockMultipartFile file = new MockMultipartFile("file", "testfile.png", "image/png", bytes);

        // 요청 빌더 생성: multipart 요청은 기본적으로 post. 이를 재정의 해서 patch로 전달
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .multipart("/api/v1/members/admin@ssafy.com/profile").file(file).with(request -> {
                    request.setMethod("PATCH");
                    return request;
                });
        // when & then
        doNothing().when(mService).updateProfile("admin@ssafy.com", bytes);
        mock.perform(builder).andExpect(status().isOk()).andExpect(jsonPath("$.status").value("SUCCESS"))
                .andExpect(jsonPath("$.data.img").value(base64));

        // InputStream 닫기
        is.close();
    }
}
