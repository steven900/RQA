package com.jdog.frameworks.util;

import java.math.BigDecimal;

public class DigitUtil {


	/**
	 * 保留N位小数
	 * @param f 数值
	 * @param figure 保留N位
	 * @return
	 */
	public static double toFixedN(Double f, int figure) {
		BigDecimal bg = new BigDecimal(f);
		double f1 = bg.setScale(figure, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}
	
}
