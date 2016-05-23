package com.jdog.frameworks.util;

public class BinaryUtil {
	
	
	/**
	 * 把int转成 二进制，获取某一位而进制数字
	 * @param num
	 * @param index
	 * @return
	 */
	public static int  getIndexBinaryFromInt(int num, int index){
		return (num & (0x1 << index)) >> index;
	}
}
