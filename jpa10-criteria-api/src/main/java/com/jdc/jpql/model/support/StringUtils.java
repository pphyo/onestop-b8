package com.jdc.jpql.model.support;

public class StringUtils {
	
	public static boolean hasLength(String data) {
		return (null != data && !data.isEmpty() && !data.isBlank()) && data.length() > 0;
	}

}
