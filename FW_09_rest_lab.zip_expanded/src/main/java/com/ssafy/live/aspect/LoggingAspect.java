package com.ssafy.live.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component // 하나의 빈으로 관리
@Aspect    // 하나 이상의 advice를 포함
@Slf4j
public class LoggingAspect {
    @Before("execution( * com.ssafy..dao.*.*(..))")

    public void logging(JoinPoint jp) {
        log.trace("method call: {}, {}", jp.getSignature(), Arrays.toString(jp.getArgs()));
    }
}
