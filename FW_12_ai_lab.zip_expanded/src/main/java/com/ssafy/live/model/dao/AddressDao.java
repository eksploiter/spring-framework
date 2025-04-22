package com.ssafy.live.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.live.model.dto.Address;

@Mapper
public interface AddressDao {
    public int insert(Address address);

    public int delete(int ano);

    public int deleteByMno(int mno);

    public Address select(int ano);

    public Address selectDetail(int ano);

    public List<Address> queryStructureChange(Map<String, Object> param);

    public List<Address> dynamicSelect(Map<String, Object> param);

    public List<Address> selectUseIn(List<String> titles);

    public int dynamicUpdate(Address address);

    public List<Address> carefulQuery(String addr, int mno);
}
