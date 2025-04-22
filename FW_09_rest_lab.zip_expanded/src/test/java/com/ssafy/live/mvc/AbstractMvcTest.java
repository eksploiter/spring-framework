package com.ssafy.live.mvc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc // 자동으로 Mock 환경 구축
public abstract class AbstractMvcTest {
    
    @Autowired
    MockMvc mock; // 가상의 웹 환경을 위한 MockMvc 객체

    @Test
    public void mockTest() {
        Assertions.assertNotNull(mock);
    }
}
