package com.ssafy.live.mybatis.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.live.model.dao.AddressDao;
import com.ssafy.live.model.dao.MemberDao;
import com.ssafy.live.model.dto.Address;
import com.ssafy.live.model.dto.Member;
import com.ssafy.live.model.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class MemberServiceTest {
    @Autowired
    private MemberService mService;
    @Autowired
    private MemberDao mDao;
    @Autowired
    private AddressDao aDao;
    // TODO: 16-1. T.X 테스트를 위한 환경을 살펴보자.
    private Address tAddr;
    private Member tMember;

    @BeforeEach
    public void setup() {
        tMember = Member.builder().email("some@email.com").name("some").password("1234").build();
        mDao.insert(tMember);
        tAddr = Address.builder().mno(tMember.getMno()).title("title").address("addr").detailAddress("detail").build();
        aDao.insert(tAddr);
    }

    @AfterEach
    public void cleanup() {
        aDao.delete(tAddr.getAno());
        mDao.delete(tMember.getMno());
    }

    @Test
    // TODO: 16-2. 테스트를 진행해보고 올바른 결과가 나올 수 있도록 Service에서 T.X 처리를 해보자.
    public void deleteTest() {
        try {
            mService.delete(tAddr.getMno());
            log.debug("예외 없음");
            Assertions.assertNull(aDao.select(tAddr.getAno()));
            Assertions.assertNull(mDao.select(tMember.getEmail()));
        } catch (Exception ignore) {
            log.debug("예외 발생: {}", ignore.getMessage());
            Assertions.assertNotNull(aDao.select(tAddr.getAno()));
            Assertions.assertNotNull(mDao.select(tMember.getEmail()));
        }
    }
}
