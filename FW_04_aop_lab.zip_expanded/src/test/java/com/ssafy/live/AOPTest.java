package com.ssafy.live;

import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ClassUtils;

import com.ssafy.live.aop.bean.HeavyWorkBean;
import com.ssafy.live.aop.bean.PointcutTestBean;
import com.ssafy.live.aop.config.CustomConfiguration;
import com.ssafy.live.model.dto.Member;
import com.ssafy.live.model.service.BasicMemberService;
import com.ssafy.live.model.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class AOPTest {
    // TODO: 01. MemberService를 주입받고 selectDetail 메서드의 동작을 테스트해보자.

    // END

    @Autowired
    PointcutTestBean ptBean;

    @Test
    // TODO: 03-2. pointcut이 변경될 때 적용되는 메서드를 확인하세요.
    public void pointCutTest() {
        Member member = Member.builder().name("hong").password("1234").email("abc@def.net").build();
        ptBean.save(member);
        ptBean.select(member.getEmail());
        ptBean.factorial(0);
        ptBean.add(0, 0);
    }

    @Test
    // TODO: 04-02.advice가 적용된 결과 비밀번호가 변경된 것을 확인하세요. 
    public void beforetest() {
        Member member = Member.builder().email("abc@def.net").password("1234").name("hong").build();
        ptBean.save(member);
        Assertions.assertEquals("4321", member.getPassword());
    }

    @Test
    // TODO: 05-02.advice가 적용된 결과 비빌번호가 변경된 것을 확인하세요.
    public void afterReturningTest() {
        Member member = ptBean.select("abc@def.net");
        Assertions.assertEquals("*", member.getPassword());
    }

    @Test
    // TODO: 06-02.advice가 적용된 결과 예외 발생 시 email 전송을 확인하세요.
    public void afterThrowingTest() {
        Assertions.assertThrows(RuntimeException.class, () -> ptBean.factorial(-1));
    }

    // TODO: 07-02. factorial을 호출하고 cache가 적절히 동작하는지 확인하세요.
    @Test
    public void aroundTest() {
        long result = ptBean.factorial(5);
        result = ptBean.factorial(5);
        Assertions.assertEquals(120, result);
    }

    // TODO: 08. @Configuration이 적용된 Application이 proxy 타입인지 확인해보자.
    @Autowired
    CustomConfiguration config;

    @Test
    public void isProxy() {
        log.debug(config.getClass().getName()); // com.ssafy.live.AOPTest com.ssafy.live.aop.config.CustomConfiguration$$SpringCGLIB$$0
        log.debug("isproxyclass: {}", AopUtils.isAopProxy(config.getClass())); // false
        log.debug("contains: {}", config.getClass().getName().contains(ClassUtils.CGLIB_CLASS_SEPARATOR)); // true
        Assertions.assertTrue(config.getClass().getName().contains(ClassUtils.CGLIB_CLASS_SEPARATOR));
    }

    // TODO: 09. heavyWork를 동작시키면서 빈의 타입과 비동기 동작을 확인해보자.
    @Autowired
    HeavyWorkBean hbean;

    @Test
    public void asyncTest() throws InterruptedException {
        Assertions.assertTrue(AopUtils.isAopProxy(hbean));
        for (int i = 0; i < 10; i++) {
            hbean.heavyWork(i);
        }
    }
}
