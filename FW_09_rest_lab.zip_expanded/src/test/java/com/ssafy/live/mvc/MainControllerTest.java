package com.ssafy.live.mvc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.ssafy.live.controller.BootMainController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebMvcTest(value = BootMainController.class)
public class MainControllerTest {

    @Autowired
    MockMvc mock;

    @Test
    void index_get요청_viewName_index() throws Exception {
        mock.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"));
    }

}
