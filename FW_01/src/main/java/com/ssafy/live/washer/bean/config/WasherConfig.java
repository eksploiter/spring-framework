package com.ssafy.live.washer.bean.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ssafy.live.washer.bean.LWasher;
import com.ssafy.live.washer.bean.SWasher;
import com.ssafy.live.washer.bean.WasherUser;

// 설정 파일 
@Configuration // class level 사용 
public class WasherConfig {

	@Bean
	public SWasher sWasher() {
		return new SWasher();
	}
	
	@Bean
	public LWasher lWasher() {
		return new LWasher();
	}
	
	@Bean
	public WasherUser washerUser() {
		WasherUser user = new WasherUser(sWasher());
		return user;
	}
}
