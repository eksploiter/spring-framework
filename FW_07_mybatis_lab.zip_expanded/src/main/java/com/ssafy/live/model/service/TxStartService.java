package com.ssafy.live.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.live.model.dao.MemberDao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TxStartService {
    @Autowired
    MemberDao mDao;

    @Autowired
    BasicSimpleService sService;

    @Transactional
    public void start(Propagation prop, int mod) {
        mDao.delete(-1);
        try {
            switch (prop) {
            case REQUIRED -> sService.required(mod);
            case REQUIRES_NEW -> sService.requiresNew(mod);
            case NESTED -> sService.nested(mod);
            }
        } catch (RuntimeException e) {
            log.debug("exception: {}", e.getMessage());
        }
    }
}
