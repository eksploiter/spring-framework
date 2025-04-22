package com.ssafy.live;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FW04AOPApplication {
    private final static Logger log = LoggerFactory.getLogger(FW04AOPApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(FW04AOPApplication.class, args);
        log.debug("debug");
    }

}
