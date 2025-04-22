package com.ssafy.live.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.live.model.dto.Address;

// TODO: 02-4.DAO의 기본적인 형태를 살펴보자.
@Mapper
public interface AddressDao {
    public int insert(Address address);

    public int delete(int ano);

    public int deleteByMno(int mno);

    public Address select(int ano);

    public Address selectDetail(int ano);

    // map의 key 속성을 where의 조건으로, value를 조건의 값으로  
    public List<Address> queryStructureChange(Map<String, Object> param);

    public List<Address> dynamicSelect(Map<String, Object> param);

    public List<Address> selectUseIn(List<String> titles);

    public int dynamicUpdate(Address address);

    public List<Address> carefulQuery(String addr, int mno);
}
