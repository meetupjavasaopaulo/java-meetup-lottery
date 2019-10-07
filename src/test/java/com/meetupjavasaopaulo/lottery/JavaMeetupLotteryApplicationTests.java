package com.meetupjavasaopaulo.lottery;

import com.meetupjavasaopaulo.lottery.util.RandomUtil;
import org.junit.Test;

import org.junit.Assert ;

public class JavaMeetupLotteryApplicationTests {

	@Test
	public void testRandom() {
		
		for (int a=0;a<100000;a++) {
			Assert.assertTrue(RandomUtil.getRandomNumberInRange(0, a*10+1)>=0);
		}
		
	}

}
