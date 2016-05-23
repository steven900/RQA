package com.jdog.frameworks.util;

import org.apache.commons.lang.StringUtils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinUtil {
	
	public static String getFirstLetter(String words) {
		String py = getPinYin(words);
		if(!StringUtils.isBlank(py)) {
			return py.substring(0, 1);
		} else {
			return null;
		}
	}
	
	public static void main(String[] args) {
		String[] as = getFirstLetterArrayByWord("分析解放军");
		for(String a : as){
			System.out.println(a);
		}
		System.out.println(getFirstLetterArrayByWord("分析解放军").length);
	}
	
	public static String[] getFirstLetterArrayByWord(String words) {
		String[] pinyins = getPinyinArrayByWord(words);
		if(pinyins == null) {
			return null;
		} else {
			String[] fletters = new String[pinyins.length];
			for(int i=0; i<pinyins.length; i++) {
				if(!StringUtils.isBlank(pinyins[i])) {
					fletters[i] = pinyins[i].substring(0, 1);
				}
			}
			return fletters;
		}
	}
	
	public static String[] getPinyinArrayByWord(String words) {
		if(StringUtils.isBlank(words)) {
			return null;
		} else {
			String pinyin = getPinYin(words);
			String[] pinyins = pinyin.split(" ");
			return pinyins;
		}
	}
	
	public static String getPinYin(String words) {
		return getPinYin(words, HanyuPinyinCaseType.LOWERCASE);
	}
	
	public static String getPinYin(String words, HanyuPinyinCaseType caseType) {
		return getPinYin(words, caseType, HanyuPinyinToneType.WITHOUT_TONE, HanyuPinyinVCharType.WITH_U_UNICODE);
	}
	
	public static String getPinYin(String words, HanyuPinyinCaseType caseType, 
			HanyuPinyinToneType toneType, HanyuPinyinVCharType vcharType) {
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(caseType);
		format.setToneType(toneType);
		format.setVCharType(vcharType);
		char[] ch = words.trim().toCharArray();
		StringBuffer buffer = new StringBuffer("");

		try {
			for (int i = 0; i < ch.length; i++) {
				// unicode，bytes应该也可以.
				if (Character.toString(ch[i]).matches("[\u4e00-\u9fa5]+")) {
					String[] temp = PinyinHelper.toHanyuPinyinStringArray(
							ch[i], format);
					buffer.append(temp[0]);// :结果"?"已经查出，但是音调是3声时不显示myeclipse8.6和eclipse
					buffer.append(" ");
				} else {
					buffer.append(Character.toString(ch[i]));
				}
			}
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}
}
