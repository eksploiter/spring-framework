package com.ssafy.live.mybatis.repo;

import java.lang.reflect.Proxy;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.live.model.dao.MemberDao;
import com.ssafy.live.model.dto.Member;
import com.ssafy.live.model.dto.SearchCondition;

@SpringBootTest

public class MemberDaoTest {

    @Autowired
    MemberDao mDao;

    @Test
    public void insertTest() {
        // TODO: 03-3. MemberDao의 insert를 테스트해보자.

        // END
    }

    // TODO: 04-2. update, delete, updateProfile의 테스트 결과를 확인하세요.
    @Test
    public void updateTest() {
        Member member = Member.builder().email("001@ssafy.com").name("modified").password("4321").role("ADMIN").build();
        int result = mDao.update(member);
        Assertions.assertEquals(1, result);
    }

    @Test
    public void deleteTest() {
        int result = mDao.delete(99);
        Assertions.assertEquals(1, result);
        result = mDao.delete(-1);
        Assertions.assertEquals(0, result);
    }

    @Test
    public void updateProfileTest() {
        byte[] profile = "Hello".getBytes();
        int result = mDao.updateProfile("admin@ssafy.com", profile);
        Assertions.assertEquals(1, result);
        result = mDao.updateProfile("notExist@ssafy.com", profile);
        Assertions.assertEquals(0, result);
    }

    // TODO: 06-2. select의 테스트 결과를 확인하세요.
    @Test
    public void selectTest() {
        String email = "admin@ssafy.com";
        Member selected = mDao.select(email);
        Assertions.assertEquals("관리자", selected.getName());
        selected = mDao.select("notExist");
        Assertions.assertNull(selected);
    }

    // TODO: 07-2. search의 테스트 결과를 확인하세요.
    @Test
    public void searchAllTest() {
        List<Member> members = mDao.searchAll();
        //System.out.println(members.get(0));
        Assertions.assertEquals("100@ssafy.com", members.get(0).getEmail());
    }

    // TODO: 09-2. searchTest의 테스트 결과를 확인하세요.
    @Test
    public void selectDetailTest() {
        Member member = mDao.selectDetail("admin@ssafy.com");
        Assertions.assertEquals(2, member.getAddresses().size());

        member = mDao.selectDetail("099@ssafy.com");
        Assertions.assertEquals(0, member.getAddresses().size());
    }

    // TODO: 15-2. getTotalCount의 테스트 결과를 확인하세요.
    @Test
    public void getTotalCountTest() {
        int cnt = mDao.getTotalCount(new SearchCondition());
        Assertions.assertEquals(101, cnt);

        cnt = mDao.getTotalCount(new SearchCondition("email", "01", 1));
        Assertions.assertEquals(11, cnt);

        cnt = mDao.getTotalCount(new SearchCondition("name", null, 0));
        Assertions.assertEquals(101, cnt);
    }

    // TODO: 15-3. search의 테스트 결과를 확인하세요.
    @Test
    public void searchTest() {
        List<Member> members = mDao.search(new SearchCondition());
        Assertions.assertEquals(5, members.size());

        members = mDao.search(new SearchCondition("email", "01", 1));
        Assertions.assertEquals(5, members.size());
        Assertions.assertEquals("019@ssafy.com", members.get(0).getEmail());

        members = mDao.search(new SearchCondition("name", "01", 2));
        Assertions.assertEquals(5, members.size());
        Assertions.assertEquals("086@ssafy.com", members.get(0).getEmail());
    }
}
