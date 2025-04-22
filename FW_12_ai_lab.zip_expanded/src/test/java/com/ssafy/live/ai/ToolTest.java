package com.ssafy.live.ai;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.live.ai.service.AiChatService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class ToolTest {

    @Autowired
    AiChatService service;

    @Test
    public void dateToolTest() {
        String result = service.timeToolGeneration("올해 크리스마스까지 얼마 남았지?");
        log.debug("result: {}", result);

        result = service.timeToolGeneration("1분 후로 알람 설정해주고 언제 울릴껀지 알려줘.");
        log.debug("result: {}", result);
    }

}
