package com.jdog.frameworks.db;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.BigIntegerConverter;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;

public class OwnConvertUtilsBean extends ConvertUtilsBean {
	@Override
	public void deregister() {
		super.deregister();
		register(new BigDecimalConverter(null), BigDecimal.class);
		register(new BigIntegerConverter(null), BigInteger.class);
		register(new UtilDateConverter(), Date.class);
		register(new SqlTimestampConverter(null), Timestamp.class);
	}
}
