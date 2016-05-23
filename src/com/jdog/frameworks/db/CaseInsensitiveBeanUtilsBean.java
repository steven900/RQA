package com.jdog.frameworks.db;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;

public class CaseInsensitiveBeanUtilsBean extends BeanUtilsBean {

	public CaseInsensitiveBeanUtilsBean() {
		super(new OwnConvertUtilsBean(), new PropertyUtilsBean());
	}

	private static Map<String, Map<String, String>> fieldsMap;

	static {
		fieldsMap = new HashMap<String, Map<String, String>>();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void populate(Object bean, Map properties)
			throws IllegalAccessException, InvocationTargetException {
		if ((bean == null) || (properties == null)) {
			return;
		}

		Map<String, String> propertiesMap = fieldsMap.get(bean.getClass()
				.getName());

		Class<?> cls = bean.getClass();

		if (fieldsMap.get(cls.getName()) == null) {

			propertiesMap = new HashMap<String, String>();

			while (Object.class.equals(cls) == false) {
				Field[] fields = cls.getDeclaredFields();

				for (Field field : fields) {
					propertiesMap.put(field.getName().toLowerCase(),
							field.getName());
				}
				cls = cls.getSuperclass();
			}

			fieldsMap.put(bean.getClass().getName(), propertiesMap);
		}

		Iterator names = properties.keySet().iterator();
		while (names.hasNext()) {

			String name = (String) names.next();
			if (name == null) {
				continue;
			}

			Object value = properties.get(name);

			String lc = name.toLowerCase();

			if (propertiesMap.containsKey(lc)) {
				setProperty(bean, propertiesMap.get(lc), value);
			}

		}

	}
}
