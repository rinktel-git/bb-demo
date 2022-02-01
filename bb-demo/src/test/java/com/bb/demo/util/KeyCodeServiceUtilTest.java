package com.bb.demo.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class KeyCodeServiceUtilTest {

	@Test
	void testGenerateRandomDigits() {
		assertTrue(KeyCodeServiceUtil.generateRandomDigits() != 0);
	}

	@Test
	void testGenerateRandomDigitsSize() {
		assertTrue((Long.toString(KeyCodeServiceUtil.generateRandomDigits())).length() == 10);
	}
	
}
