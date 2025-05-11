package com.ssafy.live;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalculatorTest {
	
	private static final Logger log = LoggerFactory.getLogger(CalculatorTest.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		log.trace("level: {}", "trace level");
//		log.debug("level: {}", "debug level");
//		log.info("level: {}", "info level");
//		log.warn("level: {}", "warn level");
//		log.error("level: {}", "error level");
		
		Calculator c = new Calculator();
		int result = c.add(10, 20);
		System.out.println(result);
		// 이렇게 했는데 로그가 출력되지 않는다? - 이것은 로그의 문제가 아니다.
		// 대부분 안 나오는 이유는 로그의 레벨 때문이다.
	}

}
