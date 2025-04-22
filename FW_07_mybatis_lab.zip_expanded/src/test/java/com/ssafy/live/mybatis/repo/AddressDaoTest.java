package com.ssafy.live.mybatis.repo;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.live.model.dao.AddressDao;
import com.ssafy.live.model.dto.Address;

@SpringBootTest
@Transactional
public class AddressDaoTest {

    @Autowired
    AddressDao aDao;

    // TODO: 05-2. Address에 대한 insert, delete, deleteByMno를 테스트 해보세요.

    // END

    @Test
    // TODO: 06-4. Address에 대한 select를 작성 후 테스트 해보세요.
    public void selectTest() {
        int ano = 1;
        Address address = aDao.select(ano);
        Assertions.assertEquals("멀티캠퍼스", address.getDetailAddress());
    }

    // TODO: 08-2. Address에 대한 selectDetail를 작성 후 테스트 해보세요.
    @Test
    public void selectDetailTest() {
        int ano = 1;
        Address address = aDao.selectDetail(ano);
        Assertions.assertEquals("멀티캠퍼스", address.getDetailAddress());
        Assertions.assertEquals("admin@ssafy.com", address.getMember().getEmail());
    }

    // TODO: 10-2. queryStructureChange를 작성 후 테스트 해보세요.
    @Test
    public void queryStructureChangeTest() {
        List<Address> address = aDao.queryStructureChange(Map.of("key", "title", "word", "기본"));
        Assertions.assertEquals(1, address.size());
        address = aDao.queryStructureChange(Map.of("key", "mno", "word", "100"));
        Assertions.assertEquals(0, address.size());
        address = aDao.queryStructureChange(Map.of("key", "1=1 or mno", "word", "100"));
        Assertions.assertEquals(2, address.size());
    }

    // TODO: 11-2. dynamicSelect를 작성 후 테스트 해보세요.
    @Test
    public void dynamicSelectTest() {
        List<Address> address = aDao.dynamicSelect(Map.of("title", "기본"));
        Assertions.assertEquals(1, address.size());
        address = aDao.dynamicSelect(Map.of("mno", "100"));
        Assertions.assertEquals(0, address.size());
        address = aDao.dynamicSelect(Map.of("title", "기본", "mno", "100"));
        Assertions.assertEquals(0, address.size());
    }

    // TODO: 12-2. selectUseIn를 작성 후 테스트 해보세요.
    @Test
    public void selectUseInTest() {
        List<Address> address = aDao.selectUseIn(List.of("기본", "집"));
        Assertions.assertEquals(2, address.size());
    }

    // TODO: 13-2. dynamicUpdate를 작성 후 테스트 해보세요.
    @Test
    public void dynamicUpdateTest() {
        Address addr = aDao.select(1);
        addr.setTitle("수정됨");
        addr.setDetailAddress(null);
        Assertions.assertEquals(1, aDao.dynamicUpdate(addr));

        Address selected = aDao.select(1);
        Assertions.assertEquals("수정됨", selected.getTitle());
        Assertions.assertEquals("멀티캠퍼스", selected.getDetailAddress());
    }

    @Test
    public void carefulQueryTest() {
        List<Address> list = aDao.carefulQuery("서울", 100);
        Assertions.assertEquals(1, list.size());
    }
}
