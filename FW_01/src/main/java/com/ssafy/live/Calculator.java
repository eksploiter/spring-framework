package com.ssafy.live;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Calculator {
	
	private final static Logger log = LoggerFactory.getLogger(Calculator.class);
	
	public int add(int a, int b) {
		int result = a +b;
		log.trace("a : {}, b : {}, result : {}", a, b, result);
		return result;
	}
}
