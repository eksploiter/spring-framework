package com.ssafy.live;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ssafy.live.model.dao.MemberDao;

@SpringBootTest
class Fw10Sec1ApplicationTests {

    @Autowired
    PasswordEncoder pe;

    @Autowired
    MemberDao mdao;

    //@Test
    // TODO: 10-2. 비밀번호를 PasswordEncoder로 encoding 하세요.
    void updatePass() {
        mdao.searchAll().forEach(m -> {
            m.setPassword(pe.encode("1234"));
            mdao.update(m);
        });
    }
}
