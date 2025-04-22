package com.ssafy.live.model.service;

import java.util.List;

import com.ssafy.live.model.dto.Member;
import com.ssafy.live.model.dto.Page;
import com.ssafy.live.model.dto.SearchCondition;

public interface MemberService {
    int registMember(Member emp);

    Member login(String email, String password);

    /**
     * 사용자의 상세 정보를 가져온다고 치자.
     * 
     * @param email
     * @return
     * @
     */
    Member selectDetail(String email);

    Page<Member> search(SearchCondition condition);

    List<Member> searchAll();

    void delete(int mno);

    void update(Member member);

    void updateProfile(String email, byte[] profile);
}
