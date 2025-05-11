package com.ssafy.live.washer.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ssafy.live.washer.bean.Washer;
import com.ssafy.live.washer.bean.WasherUser;
import com.ssafy.live.washer.bean.config.WasherConfig;

public class WasherClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ctx = new AnnotationConfigApplicationContext(WasherConfig.class);
		// 이렇게까지만 하면 spring 이 동작하게 된다. 
		
		WasherUser user = ctx.getBean(WasherUser.class);
		user.userWasher("양말");
		
		// 동일 타입의 빈 - 이름 기반으로 필터링 
		Washer washer = ctx.getBean("sWasher", Washer.class);
		washer.wash("양말");
	}

}
