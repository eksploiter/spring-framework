package com.ssafy.live.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.live.exception.RecordNotFoundException;
import com.ssafy.live.model.dao.AddressDao;
import com.ssafy.live.model.dao.MemberDao;
import com.ssafy.live.model.dto.Member;
import com.ssafy.live.model.dto.Page;
import com.ssafy.live.model.dto.SearchCondition;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BasicMemberService implements MemberService {
    private final MemberDao mDao;
    private final AddressDao aDao;

    @Override
    public int registMember(Member member) {
        int result = mDao.insert(member);
        return result;
    }

    @Override
    public Member login(String email, String password) {
        Member member = mDao.select(email);
        if (member != null && member.getPassword().equals(password)) {
            return member;
        } else {
            throw new RecordNotFoundException("id/pass 확인");
        }
    }

    @Override
    public Member selectDetail(String email) {
        return mDao.selectDetail(email);
    }

    public List<Member> searchAll() {
        return mDao.searchAll();
    }

    @Override
    public Page<Member> search(SearchCondition condition) {
        // 전체 회원 수 조회
        int totalItems = mDao.getTotalCount(condition);
        // 현재 페이지의 회원 목록 조회
        List<Member> members = mDao.search(condition);
        // 페이지네이션 정보 생성
        Page<Member> page = new Page<>(condition, totalItems, members);
        return page;
    }

    @Override
    
    public void delete(int mno) {
        aDao.deleteByMno(mno);
        //int i = 1 / 0;
        mDao.delete(mno);
    }

    @Override
    public void update(Member member) {
        mDao.update(member);
    }

    @Override
    public void updateProfile(String email, byte[] profile) {
        mDao.updateProfile(email, profile);
    }
}
