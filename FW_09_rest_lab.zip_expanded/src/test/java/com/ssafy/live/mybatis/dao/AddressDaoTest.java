package com.ssafy.live.mybatis.dao;

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

    @Test
    public void insertTest() {
        Address addr = Address.builder().mno(1).address("addr").detailAddress("detail").x("x").y("y").build();
        int result = aDao.insert(addr);
        Assertions.assertEquals(1, result);
    }

    @Test
    public void deleteTest() {
        int result = aDao.delete(1);
        Assertions.assertEquals(1, result);
        result = aDao.delete(-1);
        Assertions.assertEquals(0, result);
    }

    @Test
    @DisplayName("Member no에 소속된 Address 삭제. 1번 회원은 2개의 Address를 갖는다.")
    public void deleteByMnoTest() {
        int result = aDao.deleteByMno(1);
        Assertions.assertEquals(2, result);
        result = aDao.deleteByMno(-1);
        Assertions.assertEquals(0, result);
    }

    @Test
    public void selectTest() {
        int ano = 1;
        Address address = aDao.select(ano);
        Assertions.assertEquals("기본", address.getTitle());
        Assertions.assertEquals("멀티캠퍼스", address.getDetailAddress());
    }

    @Test
    public void selectDetailTest() {
        int ano = 1;
        Address address = aDao.selectDetail(ano);
        Assertions.assertEquals("멀티캠퍼스", address.getDetailAddress());
        Assertions.assertEquals("admin@ssafy.com", address.getMember().getEmail());
    }

    @Test
    public void queryStructureChangeTest() {
        List<Address> address = aDao.queryStructureChange(Map.of("key", "title", "word", "기본"));
        Assertions.assertEquals(1, address.size());
        address = aDao.queryStructureChange(Map.of("key", "mno", "word", "100"));
        Assertions.assertEquals(0, address.size());
        address = aDao.queryStructureChange(Map.of("key", "1=1 or mno", "word", "100"));
        Assertions.assertEquals(2, address.size());
    }

    @Test
    public void dynamicSelectTest() {
        List<Address> address = aDao.dynamicSelect(Map.of("title", "기본"));
        Assertions.assertEquals(1, address.size());
        address = aDao.dynamicSelect(Map.of("mno", "100"));
        Assertions.assertEquals(0, address.size());
        address = aDao.dynamicSelect(Map.of("title", "기본", "mno", "100"));
        Assertions.assertEquals(0, address.size());
    }

    @Test
    public void selectUseInTest() {
        List<Address> address = aDao.selectUseIn(List.of("기본", "집"));
        Assertions.assertEquals(2, address.size());
    }

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
