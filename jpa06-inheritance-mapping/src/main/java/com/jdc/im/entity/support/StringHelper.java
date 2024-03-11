package com.jdc.im.entity.support;

public class StringHelper {
	
	public static boolean isOk(String data) {
		return null != data && !data.isEmpty() && !data.isBlank();
	}

}
