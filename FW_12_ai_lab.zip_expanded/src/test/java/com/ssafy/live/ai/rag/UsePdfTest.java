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
public class UsePdfTest {

    @Autowired
    VectorStore store;

    @Autowired
    AiChatService chatService;

    void clearStore(String category) {
        store.delete("category == '" + category + "'");
    }

    @Value("classpath:rag_data/re_reading.pdf")
    Resource simplePdf;

    @BeforeEach
    public void pdfReaderTest() {
        clearStore("pdf");
        // TODO: 11-3. simplePdf 문서를 읽어서 DB에 저장하자. 
        PagePdfDocumentReader pdfReader = new PagePdfDocumentReader(simplePdf,
                PdfDocumentReaderConfig.builder()
                        .withPageTopMargin(0)    // 페이지의 상단 여백 설정: 처음부터 사용하며 header를 무시할 경우 값 증가
                        .withPagesPerDocument(1) // 하나의 Document에 포함될 PDF 객체의 페이지 수
                        .build());

        List<Document> list = pdfReader.read();
        list.forEach(doc -> doc.getMetadata().put("category", "pdf"));

        store.add(list);
    }

    @Test
    void simpleTest() {
        String user = "model 사용 시 다시 읽기 기법을 사용하면 어떻게 성능이 개선되는 거지?";
        Consumer<AdvisorSpec> spec = a -> a.param(QuestionAnswerAdvisor.FILTER_EXPRESSION, "category=='pdf'");
        String result1 = chatService.ragGeneration(user, true, spec);
        log.debug("개선될까?: {}", result1);
    }
}
