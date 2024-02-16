package com.jdc.app.support;

public class Validators {
	
	public static boolean isIncorrect(String str) {
		return null == str || str.isBlank() || str.isEmpty();
	}

}
