package com.ssafy.live.aop.bean;

import org.springframework.scheduling.annotation.Async;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//명시적 빈 등록 형태로 사용됨
@Slf4j
@RequiredArgsConstructor
public class HeavyWorkBean {

    private final PointcutTestBean ptb;

    @Async
    public void heavyWork(int i) throws InterruptedException {
        Thread.sleep(1000);
        log.trace("{}에 의해 처리중 {}! = {}", Thread.currentThread(), i, ptb.factorial(i));
    }
}
