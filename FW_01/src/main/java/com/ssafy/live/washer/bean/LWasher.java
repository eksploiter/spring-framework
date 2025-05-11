package com.ssafy.live.washer.bean;

// 비즈니스 로직 (POJO)
public class LWasher implements Washer {

	@Override
	public void wash(String cloths) {
		// TODO Auto-generated method stub
		System.out.println(cloths + "를 세탁한다.");
	}

}
