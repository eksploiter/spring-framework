package com.ssafy.live.aop.bean;

import java.util.stream.IntStream;

import com.ssafy.live.model.dto.Member;

import lombok.extern.slf4j.Slf4j;

// 명시적 빈 등록 형태로 사용됨
@Slf4j
public class PointcutTestBean {

    public void save(Member member) {
        log.debug("member(bean): {}", member);
    }

    public Member select(String email) {
        return Member.builder().email(email).password("1234").name("hong").build();
    }

    public long factorial(int n) {
        if (n < 0) {
            throw new RuntimeException("factorial은 0 이상의 정수 필요");
        }
        return IntStream.rangeClosed(1, n).reduce(1, (ov, nv) -> ov * nv);
    }

    public int add(int a, int b) {
        return a + b;
    }
}
