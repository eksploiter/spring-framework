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

@EnableWebSecurity
@Configuration
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class CustomSecurityConfig {

    @Bean
    RoleHierarchy roleHierachy() {
        return RoleHierarchyImpl.withDefaultRolePrefix() // role의 기본 prefix 설정: ROLE_
                .role("ADMIN").implies("USER").role("USER").implies("GUEST").build();
    }

    @Bean
    PasswordEncoder passEncoder() {
        // 내부적으로 BCryptPasswordEncoder를 사용한다.
        // return new BCryptPasswordEncoder();
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    @ConditionalOnMissingBean(UserDetailsService.class)
    UserDetailsService userDetailsService(PasswordEncoder pe) {
        UserDetails admin = User.builder().username("admin@ssafy.com").password(pe.encode("1234")).roles("ADMIN")
                .build();

        UserDetails staff = User.builder().username("user@ssafy.com").password(pe.encode("1234")).roles("USER").build();

        return new InMemoryUserDetailsManager(staff, admin);
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/secured/user/**").hasRole("USER") // USER role 필요
                .requestMatchers("/secured/admin/**").hasRole("ADMIN") // ADMIN role 필요
                .requestMatchers("/auth/**").authenticated() // auth는 인증만 필요
                .anyRequest().permitAll()); // 이외의 경로는 그냥 통과

        http.csrf(customizer -> customizer.disable()); // csrf는 비활성
        // http.formLogin(Customizer.withDefaults()); // 기본 form login 활용
        http.formLogin(customizer -> customizer.loginPage("/member/login-form")
                .loginProcessingUrl("/member/login")
                .successHandler((req, res, auth) -> {
                    String rememberMe = req.getParameter("remember-me");
                    Cookie loginIdCookie = new Cookie("loginId", "bye");
                    if (rememberMe != null) {
                        loginIdCookie.setValue(auth.getName());
                        loginIdCookie.setMaxAge(60 * 60 * 24 * 365);
                    } else {
                        loginIdCookie.setMaxAge(0);
                    }
                    res.addCookie(loginIdCookie);
                    res.sendRedirect(req.getContextPath() + "/");
                })
                .usernameParameter("email")
                .failureUrl("/member/login-form?error")
                .permitAll());
        //http.logout(Customizer.withDefaults());    // 기본 logout 활용
        http.logout(customizer -> customizer.logoutUrl("/member/logout")
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID", "remember-me"));
        //http.rememberMe(Customizer.withDefaults());
        http.rememberMe((customizer) -> customizer.tokenValiditySeconds(60 * 1));
        return http.build();
    }
}
