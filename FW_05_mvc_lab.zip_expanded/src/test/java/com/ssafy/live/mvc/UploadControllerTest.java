package com.ssafy.live.mvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ssafy.live.mvc.controller.SimpleController;
import com.ssafy.live.mvc.service.SimpleService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebMvcTest(value = SimpleController.class)
public class UploadControllerTest {

    @Autowired
    MockMvc mock; // 가상의 웹 환경을 위한 MockMvc 객체

    @MockitoBean // 실제 서비스를 테스트할 계획은 없다.
    SimpleService sService;

    @Test
    public void uploadTest() throws Exception {
        // given
        InputStream is = new FileInputStream("./pom.xml");
        MockMultipartFile file = new MockMultipartFile("file", "pom.xml", null, is);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.multipart("/simple/fileupload").file(file).param("desc",
                "설명");

        // when
        ResultActions actions = mock.perform(request);
        // then
        actions.andExpect(status().is(302)).andExpect(flash().attribute("file", "pom.xml"))
                .andExpect(redirectedUrl("/")).andDo(result -> {
                    HttpSession session = result.getRequest().getSession();
                    log.debug("flash map: {}", session
                            .getAttribute("org.springframework.web.servlet.support.SessionFlashMapManager.FLASH_MAPS"));
                });

    }
}
