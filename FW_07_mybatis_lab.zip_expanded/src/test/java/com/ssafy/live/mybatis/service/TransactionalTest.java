package com.ssafy.live.mybatis.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Propagation;

import com.ssafy.live.model.service.TxStartService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class TransactionalTest {

    @Autowired
    TxStartService sService;

    // TODO: 17. Transaction의 전파 속성을 살펴보자.

    @Test
    public void propagationTest1() {
        sService.start(Propagation.REQUIRED, 1);
        log.debug("-----------------------------------");
        Assertions.assertThrows(UnexpectedRollbackException.class, () -> sService.start(Propagation.REQUIRED, 0));
    }

    @Test
    public void propagationTest2() {
        sService.start(Propagation.REQUIRES_NEW, 1);
        log.debug("-----------------------------------");
        sService.start(Propagation.REQUIRES_NEW, 0);

    }

    @Test
    public void propagationTest3() {
        sService.start(Propagation.NESTED, 1);
        log.debug("-----------------------------------");
        sService.start(Propagation.NESTED, 0);
    }
}
