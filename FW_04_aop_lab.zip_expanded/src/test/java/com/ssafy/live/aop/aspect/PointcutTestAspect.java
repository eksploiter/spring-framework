package com.ssafy.live.aop.aspect;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.ssafy.live.model.dto.Member;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class PointcutTestAspect {
    // TODO: 03-1. pointcut이 적용되었을 때 어떤 메서드에 대한 log가 출력되는지 확인하세요.
    //@Before("execution(void *..PointcutTestBean.save(com.ssafy.live.model.dto.Member))")
    //@Before("execution(!String com.ssafy..s*(*))")
    //@Before("execution(long com..*Bean.*(int, ..))")
    //@Before("execution(java.util.List<com.example.MyClass> *(..))")
    public void pointcutTest(JoinPoint jp) {
        log.trace("호출 메서드: {}", jp.getSignature());
    }

    // TODO: 04-01. pointcut을 읽고 적용 대상을 파악한 후 advice의 동작을 확인하세요.
    @Before("execution(void *..PointcutTestBean.save(com.ssafy.live.model.dto.Member)) && args(member)")
    public void encryptPass(Member member) {
        log.debug("member(aspect): {}", member);
        // 파라미터 조작
        member.setPassword(new StringBuilder(member.getPassword()).reverse().toString());
    }

    // TODO: 05-01. pointcut을 읽고 적용 대상을 파악한 후 advice 동작을 확인하세요.
    @AfterReturning(value = "execution(* *..PointcutTestBean.select(String))", returning = "member")
    public void maskingUserPassword(JoinPoint jp, Member member) {
        log.debug("signature: {}, member: {}", jp.getSignature(), member);
        member.setPassword("*"); // return 값 조작!
    }

    // TODO: 06-01. pointcut을 읽고 적용 대상을 파악한 후 advice 동작을 확인하세요.
    @AfterThrowing(value = "execution(* *..PointcutTestBean.factorial(int))", throwing = "e")
    public void notifyError(JoinPoint jp, RuntimeException e) {
        log.debug("signature: {}, exception: {}", jp.getSignature(), e.getMessage());
        System.out.println("시스템 관리자에게 email 전송");
    }

    private final Map<Integer, Long> cache = new HashMap<>();

    // TODO: 07-01. PointcutTestBean의 factorial이 호출될 때 값을 캐싱해보자.

    // END

}
