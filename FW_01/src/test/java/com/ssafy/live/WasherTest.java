package com.ssafy.live;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ssafy.live.washer.bean.Washer;
import com.ssafy.live.washer.bean.WasherUser;
import com.ssafy.live.washer.bean.config.WasherConfig;

public class WasherTest {

	ApplicationContext ctx; 
	
	@BeforeEach
	public void setup() {
		ctx = new AnnotationConfigApplicationContext(WasherConfig.class);
	}
	
	@Test
	public void userTest() {
		WasherUser user = ctx.getBean(WasherUser.class);
		user.userWasher("양말");
		
		// 동일 타입의 빈 - 이름 기반으로 필터링 
		Washer washer = ctx.getBean("sWasher", Washer.class);
		washer.wash("양말");
		
		Assertions.assertNotNull(user);
	}
	
	@Test
	public void singletonTest() {
		WasherUser user = ctx.getBean(WasherUser.class);
		Washer washer = ctx.getBean("sWasher", Washer.class);
		// 나도 모르게 singleton!! 
		Assertions.assertSame(user.getWasher(), washer);
	}
	
}
