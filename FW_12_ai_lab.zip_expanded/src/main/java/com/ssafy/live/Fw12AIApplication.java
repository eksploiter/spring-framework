package com.ssafy.live;

import org.springframework.ai.autoconfigure.vectorstore.redis.RedisVectorStoreAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = RedisVectorStoreAutoConfiguration.class)
public class Fw12AIApplication {

    public static void main(String[] args) {
        SpringApplication.run(Fw12AIApplication.class, args);
    }

}
