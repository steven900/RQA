package com.jdog.frameworks.converter;

import java.nio.charset.Charset;

import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;

public class SpringStringHttpMessageConverter extends StringHttpMessageConverter {

	private static final MediaType UTF8 = new MediaType("text", "plain", Charset.forName("UTF-8"));

	protected MediaType getDefaultContentType(String dumy) {
		System.out.println("-----------------------------------------------" + dumy);
		return UTF8;
	}
	
}
