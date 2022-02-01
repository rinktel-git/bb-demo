package com.bb.demo.util;

public class KeyCodeServiceUtil {

	/**
	 * Returns 10 digit random number
	 * @return
	 */
	public static long generateRandomDigits() {
	    
	    long theRandomNum = (long) (Math.random()*Math.pow(10,10));
	    
	    return theRandomNum;
	}
	
	
}
