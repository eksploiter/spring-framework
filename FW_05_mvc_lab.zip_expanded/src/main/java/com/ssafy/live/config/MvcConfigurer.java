package com.ssafy.live.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;




import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class MvcConfigurer implements WebMvcConfigurer {
    // TODO: 11-2. 성능 측정, 세션 관리를 위한 Interceptor를 생성하고 등록해보자.

    // END

    // TODO: 12-2. /auth/help 요청에 대해 /help/auth-help.jsp를 연결해주자.

    // END

    @Value("${spring.servlet.multipart.location}")
    String filePath;

    // TODO: 14-5. 특정 위치의 리소스를 서버에서 사용할 수 있도록 등록해보자.

    // END

}
