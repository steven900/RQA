package com.jdog.frameworks.db;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.Converter;

public class UtilDateConverter implements Converter {
	public Object convert(@SuppressWarnings("rawtypes") Class type, Object value) {
		if (value == null) {
			return value;
		}
		if (value instanceof Date) {
			return value;
		}
		if (value instanceof String) {
			String v = (String) value;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(v.length() > 10) {
				v = v.substring(0, v.length() - 2);
			} else {
				sdf = new SimpleDateFormat("yyyy-MM-dd");
			}
			try {
				return sdf.parse(v);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
