package com.ssafy.live.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.ssafy.live.security.config.CustomSecurityConfig;
import com.ssafy.live.security.controller.MethodLevelSecureController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebMvcTest(value = MethodLevelSecureController.class)
@Import(CustomSecurityConfig.class)
public class MethodLevelSecurityTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("@Secured 로 보호되는 리소스 호출 확인")
    @WithMockUser(roles = "USER")
    void secureAnnotatedMethodCallTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/methodlevelSecure/user"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.get("/methodlevelSecure/admin"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());

    }
}
