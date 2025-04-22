package com.ssafy.live.ai;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.metadata.Usage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.MimeTypeUtils;

import com.ssafy.live.ai.service.AiChatService;
import com.ssafy.live.ai.service.AiChatService.Quiz;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class ChatClientTest {

    @Autowired
    AiChatService aiService;

    // TODO: 03-2. aiService.simpleGeneration의 동작을 확인하세요.
    @Test
    void simpeleGenerationTest() throws Exception {
        String userInput = "봄에 속한 달을 설명해줘.";
        Object result = aiService.simpleGeneration(userInput);

        if (result instanceof ChatResponse resp) {
            Usage usage = resp.getMetadata().getUsage();
            log.debug("token 수: 프롬프트: {}, 결과: {}, 전체: {}", usage.getPromptTokens(), usage.getCompletionTokens(), usage.getTotalTokens());
        }

        log.debug("content: {}", result);
    }

    // TODO: 04-2. aiService.quiz의 동작을 확인하세요.
    @Test
    void quizTest() throws Exception {
        String category = "주식";
        Quiz quiz = aiService.quiz(category);
        log.debug("content: {}", quiz);
    }

    // TODO: 05-2. multi modal 타입의 리소스들을 처리해보자. 
    @Test
    public void multiModalTest() {
        try {
            Resource res = new ClassPathResource("/static/img/multimodal.test.png");
            String result = aiService.multiModal("이 그림에  대해서 설명해줘.", MimeTypeUtils.IMAGE_PNG, res);
            log.debug("result: {}", result);

            // Resource res = new ClassPathResource("/static/img/100.mp4");
            // String result = aiService.multiModal("이 비디오에  대해서 설명해줘.",MimeType.valueOf("video/mp4"), res);
            // log.debug("result: {}", result);

            // Resource res = new ClassPathResource("/static/img/sound.mp3");
            // String result = aiService.multiModal("이 오디오에  대해서 설명해줘.", MimeType.valueOf("audio/mpeg"), res);
            // log.debug("result: {}", result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // TODO: 06-5 aiService.reReadingGeneration의 동작을 확인하세요.
    @Test
    public void rereadingTest() throws Exception {
        String user = "어제는 2024.12.31 이었어. 내일은 몇 일인지 MM/DD/YYYY형태로 알려줘.";
        Object result1 = aiService.simpleGeneration(user);
        Object result2 = aiService.reReadingGeneration(user);
        System.out.println("--------------------------------------------------------------------");
        log.debug("content(rereading x): {}", result1);
        log.debug("content(rereading o): {}", result2);
    }

    // TODO: 07-1. 대화의 맥락 유지를 확인하세요.
    @Test
    public void messageChatMemoryAdvisorTest() throws Exception {
        String user1 = "노벨 문학상에 대해 한 줄로 설명해줘.";
        Object answer1 = aiService.simpleGeneration(user1);
        String user2 = "한강에 대해 한 줄로 설명해줘.";
        Object answer2 = aiService.simpleGeneration(user2);
        String user3 = "그녀의 작품 중 하나만 이야기해봐.";
        Object answer3 = aiService.simpleGeneration(user3);
        List<Object> conversation1 = List.of(user1, answer1, user2, answer2, user3, answer3);
        System.out.println("--------------------------------------------------------------------");
        Object answer4 = aiService.advisedGeneration(user1);
        Object answer5 = aiService.advisedGeneration(user2);
        Object answer6 = aiService.advisedGeneration(user3);
        List<Object> conversation2 = List.of(user1, answer4, user2, answer5, user3, answer6);
        conversation1.forEach(System.out::println);
        System.out.println("-------------");
        conversation2.forEach(System.out::println);
    }

}
