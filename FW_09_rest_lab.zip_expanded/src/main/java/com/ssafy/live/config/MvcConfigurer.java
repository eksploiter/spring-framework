package com.ssafy.live.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.live.interceptor.PerformanceInterceptor;
import com.ssafy.live.interceptor.SessionInterceptor;

@Configuration
public class MvcConfigurer implements WebMvcConfigurer {
    @Autowired
    PerformanceInterceptor pi;

    @Autowired
    SessionInterceptor si;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // registry.addInterceptor(pi);
        registry.addInterceptor(si).addPathPatterns("/auth/**");
    }

    @Value("${spring.servlet.multipart.location}")
    String filePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("file:" + filePath + "/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/auth/help").setViewName("help/auth-help");
    }

    // TODO: 08. /api/v1/ 하위의 모든 요청에 대해 CORS를 설정해주자.

    // END

    // TODO: 09-2. RestTemplat 타입의 빈을 설정하세요.

    // END
}
