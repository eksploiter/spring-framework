package com.ssafy.live;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculatorUnitTest {
	
	private Calculator calc;
	
	@BeforeEach
	public void setup() {
 		calc = new Calculator();
	}

	@Test
	@DisplayName("계산 결과 확인: ex) 10 + 20 = 30")
	public void 계산기의_더하기_결과가_나오는지_검증하는_테스트() {
		int a = 10;
		int b = 20;
		
		int result = calc.add(a,  b);
		// assertion(단정문)
		// 메서드의 호출 결과가 예상한 값과 동일한지 판단하는 문장
		// assertion 메서드들이 static 하게 제공됨 
		Assertions.assertEquals(30, result);
	}
}
