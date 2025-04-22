package com.ssafy.live.security;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.ssafy.live.security.config.CustomSecurityConfig;
import com.ssafy.live.security.controller.SecureController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebMvcTest(value = SecureController.class)
@Import(CustomSecurityConfig.class) // 필요한 config만 로딩
public class UserDetailsServiceTest {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    PasswordEncoder passEncoder;

    @Autowired
    MockMvc mockMvc;

    @Test
    void passwordEncoderTest() {
        String pass = "1234";
        String encoded1 = passEncoder.encode(pass);
        String encoded2 = passEncoder.encode(pass);

        log.debug("encoded1: {}", encoded1);
        log.debug("encoded2: {}", encoded2);
        Assertions.assertTrue(passEncoder.matches(pass, encoded1));
        Assertions.assertTrue(passEncoder.matches(pass, encoded2));
    }

    @Test
    void 사용자생성_테스트() {
        UserDetails user = userDetailsService.loadUserByUsername("admin@ssafy.com");

        Assertions.assertNotNull(user);
        Assertions.assertTrue(passEncoder.matches("1234", user.getPassword()));
        Assertions.assertTrue(user.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")));
    }

    @Test
    void 인증없는_상태에서_여러_엔드포인트_접근_테스트() throws Exception {
        // given
        var req = MockMvcRequestBuilders.get("/");
        // when & then
        mockMvc.perform(req).andExpect(MockMvcResultMatchers.status().isOk());

        req = MockMvcRequestBuilders.get("/secured/user");
        mockMvc.perform(req).andExpect(MockMvcResultMatchers.status().is3xxRedirection())
        .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/member/login-form")); // login customize 후
    }

    //
    @Test
    @WithMockUser(roles = "USER") // 테스트용 권한의 사용자
    void STAFF_권한이_있는사용자가_권한별_페이지에_접근하는경우_확인() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/secured/user"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.get("/secured/admin"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }
}
