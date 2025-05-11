package com.ssafy.live.washer.bean;

// 비즈니스 로직 (POJO)
public class WasherUser {
	private Washer washer;
	
	public WasherUser(Washer washer) {
		this.washer = washer;
	}
	
	public void userWasher(String cloths) {
		washer.wash(cloths);
	}
	
	public Washer getWasher() {
		return this.washer;
	}
}
