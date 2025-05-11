package com.ssafy.live.accessory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ssafy.live.accessory.config.AccessoryConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AccessoryConfig.class)
public class AccessoryTest {
	@Autowired
	Bag bag;
	
	@Test
	public void testBag() {
		Assertions.assertNotNull(bag);
	}
}
