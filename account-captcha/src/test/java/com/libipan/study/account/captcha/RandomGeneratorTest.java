package com.libipan.study.account.captcha;

import static org.junit.Assert.assertFalse;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class RandomGeneratorTest {
	
	/**
	 * 简单的检查，100次内生成的值是否随机
	 * @throws Exception
	 */
	@Test
	public void testGetRandomString() throws Exception {
		Set<String> randoms = new HashSet<String>(100);

		for (int i = 0; i < 100; i++) {
			String random = RandomGenerator.getRandomString();

			assertFalse(randoms.contains(random));

			randoms.add(random);
		}
	}
}
