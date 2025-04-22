package com.ssafy.live.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.live.model.dto.Member;
import com.ssafy.live.model.dto.SearchCondition;

@Mapper
public interface MemberDao {
    int insert(Member member);

    public int update(Member member);

    public int delete(int mno);

    public int updateProfile(String email, byte[] profile);

    Member select(String email);

    List<Member> searchAll();

    public Member selectDetail(String email);

    public int getTotalCount(SearchCondition condition);

    public List<Member> search(SearchCondition condition);

}
