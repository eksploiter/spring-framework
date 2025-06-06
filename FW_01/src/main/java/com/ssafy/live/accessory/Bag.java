package com.ssafy.live.accessory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Bag {
	Hat hat;
	Watch watch;
	SmartWatch samrtwatch;
	
	@Autowired
	public Bag(Hat hat, @Qualifier("watch") Watch watch) {
		this.hat = hat;
		this.watch = watch;
	}
	
	@Autowired
	public void setSmartWatch(SmartWatch sWatch) {
		this.samrtwatch = sWatch;
	}
}
