package com.ssafy.live.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
// TODO: 07-2. SWAGGER 설정을 확인하세요.
// 문서에 대한 설명
@OpenAPIDefinition(info = @Info(title = "SSAFY HRM API 명세서", description = "API 명세서 테스트 입니다.", version = "v1"))
public class SwaggerConfig {

    // 관련된 API들의 grouping
    @Bean
    GroupedOpenApi memberOpenApi() {
        String[] paths = { "/api/v1/members/**" };
        return GroupedOpenApi.builder().group("Member 관련 API").pathsToMatch(paths).build();
    }

    @Bean
    GroupedOpenApi otherOpenApi() {
        String[] paths = { "/api/etc/**" };
        return GroupedOpenApi.builder().group("기타 API").pathsToMatch(paths).build();
    }
}
