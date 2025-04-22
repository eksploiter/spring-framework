package com.ssafy.live.ai.rag;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient.AdvisorSpec;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.ExtractedTextFormatter;
import org.springframework.ai.reader.JsonReader;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.filter.Filter.Expression;
import org.springframework.ai.vectorstore.filter.FilterExpressionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.live.ai.service.AiChatService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class UseTextTest {

    @Autowired
    VectorStore store;

    @Autowired
    AiChatService chatService;

    void clearStore(String category) {
        store.delete("category == '" + category + "'");
    }

    @Value("classpath:rag_data/simpleText.txt")
    Resource simpleText;

    @BeforeEach
    public void textReaderTest() throws IOException {
        // TODO: 11-2. simpleText 문서를 읽어서 DB에 저장하자. 
        // 이때 meta field로 category=json을 설정한다.
        clearStore("text");
        TextReader textReader = new TextReader(simpleText);
        textReader.getCustomMetadata().put("category", "text");

        TokenTextSplitter splitter = new TokenTextSplitter();
        List<Document> list = splitter.apply(textReader.get());

        store.add(list);
    }

    @Test
    void simpleTest() {
        String user = "임의 결석을 4회 했을 때 수강생의 운명은 어떻게 될까?";
        Consumer<AdvisorSpec> spec = a -> a.param(QuestionAnswerAdvisor.FILTER_EXPRESSION, "category=='text'");
        String result1 = chatService.ragGeneration(user, true,  spec);
        log.debug("과연 그는: {}", result1);
    }

}
