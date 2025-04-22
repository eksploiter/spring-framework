package com.ssafy.live.model.service;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.live.model.dao.MemberDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BasicSimpleService implements SimpleService {

    private final MemberDao mDao;
    private final DataSourceTransactionManager tm;

    @Override
    public Map<String, Integer> getGugu(int dan) {
        if (dan < 1 || dan > 10) {
            throw new RuntimeException("1~9까지만 처리 가능: " + dan);
        }
        Map<String, Integer> map = new TreeMap<>();
        for (int i = 1; i < 10; i++) {
            map.put(dan + " * " + i + " = ", dan * i);
        }
        return map;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void required(int mod) {
        int i = 1 / mod;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void requiresNew(int mod) {
        int i = 1 / mod;
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public void nested(int mod) {
        int i = 1 / mod;
    }
}
