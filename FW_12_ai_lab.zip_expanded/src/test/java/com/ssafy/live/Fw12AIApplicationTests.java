package com.ssafy.live;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ssafy.live.model.dao.MemberDao;

@SpringBootTest
class Fw12AIApplicationTests {

    @Autowired
    PasswordEncoder pe;

    @Autowired
    MemberDao mdao;

    //
    //@Test
    void updatePass() {
        mdao.searchAll().forEach(m -> {
            m.setPassword(pe.encode("1234"));
            System.out.println(m.getPassword().length());
            mdao.update(m);
        });
    }
}
