package com.ssafy.live.ai.rag;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.ChatClient.AdvisorSpec;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.document.Document;
import org.springframework.ai.evaluation.EvaluationRequest;
import org.springframework.ai.evaluation.EvaluationResponse;
import org.springframework.ai.evaluation.RelevancyEvaluator;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.filter.Filter.Expression;
import org.springframework.ai.vectorstore.filter.FilterExpressionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.live.ai.service.AiChatService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class UseSimpleTxtTest {

    @Autowired
    VectorStore store;

    @Autowired
    AiChatService chatService;

    @Autowired
    ChatModel model;

    // category에 해당하는 데이터 초기화
    void clearStore(String category) {
        store.delete("category == '" + category + "'");
    }

    @Test
    public void simpleTextTest() {
        clearStore("simple");
        List<Document> documents = List.of(
                new Document("The Spring AI project aims to streamline the development of applications that incorporate artificial intelligence functionality without unnecessary complexity",
                        Map.of("category", "simple", "meta_txt", "hong", "meta_num", 2000)),
                new Document("Spring AI provides abstractions that serve as the foundation for developing AI applications. ", Map.of("category", "simple", "meta_txt", "jang", "meta_num", 2001)),
                new Document("Spring is new beginning", Map.of("category", "simple", "meta_num", 2001)));

        // VectorStore에 데이터 저장하기
        store.add(documents);

        // TODO: 09-2. store에 저장된 데이터에 대한 유사도 검사를 진행해보자. 
        //  Spring이라는 단어와 유사한 데이터를 유사도 0.75에서 3개 찾아보세요.
        //  category가 simple이면서 meta_txt가 hong 또는 jang이고 meta_num이 2000 이상으로 한정지어 위 테스트를 진행하세요.
        FilterExpressionBuilder builder = new FilterExpressionBuilder();
        Expression expression = builder.and(builder.eq("category", "simple"), builder.and(builder.in("meta_txt", "hong", "jang"), builder.gte("meta_num", 2000))).build();

        List<Document> result2 = store.similaritySearch(searchRequestBuilder.filterExpression(expression).build());

        log.debug("use expression filter: {}", result2.size());
        result2.forEach(doc -> System.out.println(doc));

        // @@COMMENT: 위 검색 내용을 string expression으로 변경해보자.

        // END
    }

    @Test
    void simpleTest1() {
        String user = "Spring AI와 관련된 내용을 자세히 알려줘";
        Consumer<AdvisorSpec> spec = a -> a.param(QuestionAnswerAdvisor.FILTER_EXPRESSION, "category=='simple'");
        // TODO: 10-4. RAG서비스를 사용하면서 RAG의 개념과 filtering을 테스트 해보세요.

        // END
    }

}
