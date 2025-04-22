package com.ssafy.live.aop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import com.ssafy.live.aop.bean.HeavyWorkBean;
import com.ssafy.live.aop.bean.PointcutTestBean;

@Configuration
@EnableAsync
public class CustomConfiguration {

    @Bean
    PointcutTestBean ptb() {
        return new PointcutTestBean();
    }

    @Bean
    HeavyWorkBean heavyWorkBean() {
        return new HeavyWorkBean(ptb());
    }
}
