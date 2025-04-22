package com.ssafy.live.ai.rag;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient.AdvisorSpec;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.document.Document;
import org.springframework.ai.document.MetadataMode;
import org.springframework.ai.reader.EmptyJsonMetadataGenerator;
import org.springframework.ai.reader.JsonMetadataGenerator;
import org.springframework.ai.reader.JsonReader;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.filter.Filter.Expression;
import org.springframework.ai.vectorstore.filter.FilterExpressionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.live.ai.service.AiChatService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class UseJsonTest {

    @Autowired
    VectorStore store;

    @Autowired
    AiChatService chatService;

    void clearStore(String category) {
        store.delete("category == '" + category + "'");
    }

    @Value("classpath:rag_data/국민연금공단_기금_포트폴리오_20241130.json")
    Resource simpleJson;

    @BeforeEach
    public  void simpleJsonReaderTest() throws StreamReadException, DatabindException, IOException {
        clearStore("json");
        // TODO: 11-1. simpleJson 문서를 읽어서 DB에 저장하자. 
        // 이때 meta field로 category=json, meta_num=[json의 year]를 설정한다.
        JsonMetadataGenerator gen = new JsonMetadataGenerator() {
            @Override
            public Map<String, Object> generate(Map<String, Object> jsonMap) {
                return Map.of("category", "json", "meta_num", jsonMap.get("year"));
            }
        };

        JsonReader jReader = new JsonReader(simpleJson, gen, "year", "전체 자산(시장가)", "복지 부문", "금융 부문(국내주식)", "금융 부문(해외주식)", "금융 부문(국내채권)", "금융 부문(해외채권)", "금융 부문(대체투자)", "금융 부문(단기자금)", "기타 부문");
        List<Document> list = jReader.get();
        store.add(list);
    }

    @Test
    void simpleTest() {
        String user = "context에 전달된 국민연금 공단의 기금 포트폴리오를 분석해서 금융 부문 해외 주식이 240894이상인 경우를 알려줘. 이에 따라 개인 투자자는 어떤 투자 방식을 취해야 할까?";
        Consumer<AdvisorSpec> spec = a -> a.param(QuestionAnswerAdvisor.FILTER_EXPRESSION, "category=='json'");
        String result1 = chatService.ragGeneration(user, true, spec);
        log.debug("금융 부문 해외 주식 현황: {}", result1);
    }

}
