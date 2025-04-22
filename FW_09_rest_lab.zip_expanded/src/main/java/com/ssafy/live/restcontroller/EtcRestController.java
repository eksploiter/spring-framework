package com.ssafy.live.restcontroller;

import java.net.URI;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/etc")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "EtcRestController", description = "기타 유틸리티성 기능 제공")
public class EtcRestController implements RestControllerHelper {

	 private RestTemplate restTemplate;
    @Value("${api.key_vworld}")
    private String vworldKey;
    
    // TODO: 09-3. RestTemplate의 getForObject를 이용해서 sido 정보를 조회 후 반환하세요.

    // END
}
