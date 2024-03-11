package com.jdc.im.entity.support;

import static com.jdc.im.entity.support.StringHelper.isOk;

import java.util.Arrays;
import java.util.stream.Collectors;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ProductConverter implements AttributeConverter<String, String> {

	@Override
	public String convertToEntityAttribute(String dbData) {
		if(isOk(dbData)) {
			return Arrays.stream(dbData.split(",")).collect(Collectors.joining(" "));
		}
		return null;
	}

	@Override
	public String convertToDatabaseColumn(String attribute) {
		if(isOk(attribute)) {
			var arr = attribute.split(" ");
			return Arrays.stream(arr).collect(Collectors.joining(","));
		}
		return null;
	}

}
