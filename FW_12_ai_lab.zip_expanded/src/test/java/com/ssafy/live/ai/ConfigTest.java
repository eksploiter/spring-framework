package com.ssafy.live.ai;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ssafy.live.ai.config.AiConfig;

@SpringBootTest
public class ConfigTest {
    @Autowired
    VectorStore vs;
    
    @Test
    void vectorStoreTest() {
        Assertions.assertNotNull(vs);
    }

}
