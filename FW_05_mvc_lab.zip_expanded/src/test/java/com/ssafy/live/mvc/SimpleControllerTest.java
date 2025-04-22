package com.ssafy.live.mvc;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.ssafy.live.mvc.controller.SimpleController;
import com.ssafy.live.mvc.service.SimpleService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@WebMvcTest(value = SimpleController.class) // 테스트할 대상 Controller
@Slf4j
public class SimpleControllerTest {

    @Autowired
    MockMvc mock; // 가상의 웹 환경을 위한 MockMvc 객체

    @MockitoBean // 실제 서비스를 테스트할 계획은 없다.
    SimpleService sService;

    @Test
    public void forwardTest() throws Exception {
        // TODO: 04-2. /simple/forward가 잘 동작하는지 테스트 해보세요.

        // END
    }

    @Test
    public void redirectTest() throws Exception {
        // TODO: 04-3. /simple/redirect의 동작을 테스트 해보자.

        // END
    }

    @Test
    public void json() throws Exception {
        // TODO: 04-4. /simple/json의 동작을 테스트해보자.

        // END
    }

    // TODO: 05-2. session을 테스트 하기 위한 코드를 살펴보세요.
    @Test
    public void logoutTest() throws Exception {
        // given
        var request = MockMvcRequestBuilders.get("/simple/logout").sessionAttr("loginUser",
                "hong");
        // when
        var response = mock.perform(request);
        // then
        response.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200)).andExpect(jsonPath("$.loginUser", equalTo("hong")));
    }

    // TODO: 06-2. parameter를 설정해서 테스트 하기 위한 코드를 살펴보세요.
    @Test
    public void calcTest() throws Exception {
        // given
        var request = MockMvcRequestBuilders.get("/simple/calc").param("num1", "100")
                .param("num2", "10").param("oper", "+");
        // when
        var response = mock.perform(request);
        // then
        response.andExpect(status().is(200)).andExpect(jsonPath("$.result", equalTo(110))).andDo(print());
    }

    // TODO: 07-2. Member의 property들을 전달해보자.
    @Test
    public void registTest() throws Exception {
        // given
        var request = MockMvcRequestBuilders.get("/simple/regist")
                .param("email", "admin@ssafy.com").param("name", "hong gil dong").param("password", "1234");
        // when
        var response = mock.perform(request);
        // then
        response.andExpect(status().is(200)).andExpect(jsonPath("$.result.email", equalTo("admin@ssafy.com")));
    }

    // TODO: 08-2. cookie 값을 확인해보자.
    @Test
    public void cookieTest() throws Exception {
        // given
        var request = MockMvcRequestBuilders.get("/simple/cookie")
                .cookie(new Cookie("name", "hong"), new Cookie("age", "10"));
        // when
        var response = mock.perform(request);
        // then
        response.andExpect(status().is(200)).andExpect(jsonPath("$.name", equalTo("hong")))
                .andExpect(jsonPath("$.age", equalTo(10)));
    }

    // TODO: 09-2. flash scope와 session scope의 차이점을 살펴보자.
    @Test
    public void redirectAttributeTest() throws Exception {
        // given
        var request = MockMvcRequestBuilders.get("/simple/redir-attr");

        // when
        var response = mock.perform(request);
        // then
        MvcResult mvcResult = response.andExpect(status().is(302))
                .andExpect(MockMvcResultMatchers.request().sessionAttribute("bySession", "annoying"))
                .andExpect(flash().attribute("byFlash", "SSG"))
                .andExpect(redirectedUrlPattern("/simple/forward?byParam=dirty")).andDo(result -> {
                    HttpSession session = result.getRequest().getSession();
                    log.debug("flash map: {}", session
                            .getAttribute("org.springframework.web.servlet.support.SessionFlashMapManager.FLASH_MAPS"));
                }).andReturn();

        request = MockMvcRequestBuilders.get(mvcResult.getResponse().getRedirectedUrl())
                .session((MockHttpSession) mvcResult.getRequest().getSession());
        // when
        response = mock.perform(request);
        // then
        response.andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.request().sessionAttribute("bySession", "annoying"))
                .andExpect(flash().attribute("byFlash", nullValue()));
    }
}
