package com.ssafy.live.security.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.http.Cookie;

// TODO: 03-1. Spring Security를 위한 애너테이션을 설정하세요.

// END
public class CustomSecurityConfig {
    // TODO: 03-2. 사용자의 ROLE 정보를 확인하세요.
    @Bean
    RoleHierarchy roleHierachy() {
        return RoleHierarchyImpl.withDefaultRolePrefix() // role의 기본 prefix 설정: ROLE_
                .role("ADMIN").implies("USER").role("USER").implies("GUEST").build();
    }

    // TODO: 04-1. 비밀번호 인코딩을 위한 PasswordEncoder를 확인하세요.
    @Bean
    PasswordEncoder passEncoder() {
        // 내부적으로 BCryptPasswordEncoder를 사용한다.
        // return new BCryptPasswordEncoder();
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // TODO: 05-1. InMemoryUserDetailsManager를 통해 admin과 user 사용자를 등록해보자.

    // END

    // TODO: 06-1. secured 하위 요청에 대해 지정된 권한이 있는 경우만 접근할 수 있도록 설정해보자.
    //  /secured/user 하위는 USER권한 필요, /secured/admin 하위는 ADMIN 권한 필요, /auth 하위는 로그인 필요, 나머지 경로는 그냥 통과

    // END
    // TODO: 07-3. TODO_06-1에서 csrf 체크를 무력화 시키세요.
    // TODO: 07-4. TODO_06-1에서 formLogin을 재정의 하세요.
    // TODO: 08-1. TODO_06-1에서 logout을 재정의하세요.
    // TODO: 11. remember me를 활성화 시키세요.
}
