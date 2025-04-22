package com.ssafy.live.mvc;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.InputStream;
import java.util.Base64;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.live.model.dao.MemberDao;
import com.ssafy.live.model.dto.Address;
import com.ssafy.live.model.dto.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
public class MemberRestControllerTest extends AbstractMvcTest {
    @Autowired
    MemberDao mDao;

    @Test
    // TODO: 02-4. rest api를 테스트하는 코드를 살펴보자.(@RequestBody로 설정한 후 테스트 할 것)
    public void registMemberTest() throws Exception {
        Member member = Member.builder().name("name").email("email").password("pass").build();
        ObjectMapper mapper = new ObjectMapper();
        RequestBuilder builder = MockMvcRequestBuilders.post("/api/v1/members").contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(member));

        mock.perform(builder).andExpect(status().isCreated()).andExpect(jsonPath("$.status", equalTo("SUCCESS")))
                .andExpect(jsonPath("$.data", notNullValue())).andExpect(jsonPath("$.error").doesNotExist());

        mock.perform(builder).andExpect(status().is5xxServerError()).andExpect(jsonPath("$.status", equalTo("FAIL")))
                .andExpect(jsonPath("$.data").doesNotExist()).andExpect(jsonPath("$.error", notNullValue()));
    }

    // TODO: 03-2. /members/{email}에 대한 REST API를 테스트 해보자.
    @Test
    public void memberDetail() throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.get("/api/v1/members/admin@ssafy.com");

        mock.perform(builder).andExpect(status().isOk()).andExpect(jsonPath("$.status", equalTo("SUCCESS")))
                .andExpect(jsonPath("$.data.result.name", equalTo("관리자")));

        builder = MockMvcRequestBuilders.get("/api/v1/members/notexist@ssafy.com");

        mock.perform(builder).andExpect(status().isNoContent()).andExpect(jsonPath("$.status", equalTo("SUCCESS")))
                .andExpect(jsonPath("$.data.result", equalTo("notexist@ssafy.com")));
    }

    // TODO: 04-2. GET /members?key=email&word=001&currentPage=1 형태에 대한 REST

    // END

    // TODO: 05-2. 회원 수정을 위한 API를 테스트 하세요.
    @Test
    public void memberModifyTest() throws Exception {
        Member member = mDao.selectDetail("admin@ssafy.com");
        member.setName("modified");
        ObjectMapper mapper = new ObjectMapper();
        RequestBuilder builder = MockMvcRequestBuilders.put("/api/v1/members").contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(member));

        mock.perform(builder).andExpect(status().isOk()).andExpect(jsonPath("$.status", equalTo("SUCCESS")))
                .andExpect(jsonPath("$.data.result.name", equalTo("modified")));

    }

    // TODO: 06-2. 회원 삭제를 위한 API를 테스트 하세요.
    @Test
    public void memberDeleteTest() throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.delete("/api/v1/members/1");

        mock.perform(builder).andExpect(status().isOk()).andExpect(jsonPath("$.status", equalTo("SUCCESS")))
                .andExpect(jsonPath("$.data.result", equalTo(1)));
    }

    @Test
    public void addAddressTest() throws Exception {
        Address address = Address.builder().title("title").mno(1).address("addr").detailAddress("detail").build();
        ObjectMapper mapper = new ObjectMapper();
        RequestBuilder builder = MockMvcRequestBuilders.post("/api/v1/members/admin@ssafy.com/addresses")
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(address));

        mock.perform(builder).andExpect(status().isOk()).andExpect(jsonPath("$.status", equalTo("SUCCESS")))
                .andExpect(jsonPath("$.data.result.length()", equalTo(3)));
    }

    @Test
    public void addressDeleteTest() throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.delete("/api/v1/members/admin@ssafy.com/addresses/1");

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

        // 요청 실행 및 응답 검증
        mock.perform(builder).andExpect(status().isOk()).andExpect(jsonPath("$.status").value("SUCCESS"))
                .andExpect(jsonPath("$.data.img").value(base64));

        // InputStream 닫기
        is.close();
    }
}
