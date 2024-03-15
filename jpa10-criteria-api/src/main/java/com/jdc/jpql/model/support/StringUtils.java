package com.jdc.jpql.model.support;

public class StringUtils {
	
	public static boolean hasLength(String data) {
		return null != data && data.trim().length() > 0;
	}
	
	public static boolean hasText(String data) {
		return null != data && !data.trim().isEmpty();
	}

}
