package com.jdog.frameworks.util;

import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import net.sf.json.JSONObject;



public class StringUtil {

	public static boolean isBlank(String str) {
		return StringUtils.isBlank(str);
	}

	public static String encode(String str, String fromEncoding,
			String toEncoding) {
		if (str != null) {
			try {
				return new String(str.getBytes(fromEncoding), toEncoding);
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
		}
		return str;
	}

	public static String ISO88591ToGBK(String str) {
		return encode(str, "ISO-8859-1", "GBK");
	}

	public static String GBKToIso88591(String str) {
		return encode(str, "GBK", "ISO-8859-1");
	}

	public static String ISO88591ToUTF(String str) {
		return encode(str, "ISO8859_1", "UTF-8");
	}

	public static String GBKToUTF(String str) {
		return encode(str, "GBK", "UTF-8");
	}

	/**
	 * 瀛楃涓茶浆鎹㈡垚16杩涘埗瀛楃涓�
	 * 
	 * @param str
	 * @return
	 */
	public static String str2hex(String str) {
		if (str == null)
			return null;
		byte bytes[] = str.getBytes();
		StringBuffer retString = new StringBuffer();
		for (int i = 0; i < bytes.length; ++i) {
			retString.append(Integer.toHexString(0x0100 + (bytes[i] & 0x00FF))
					.substring(1).toUpperCase());
		}
		return retString.toString();
	}

	/**
	 * 灏�16杩涘埗瀛楃涓茶浆鎹㈡垚瀛楃涓�
	 * 
	 * @param hex
	 * @return
	 */
	public static String hex2str(String hex) {
		try {
			if (hex == null)
				return null;
			byte[] bts = new byte[hex.length() / 2];
			for (int i = 0; i < bts.length; i++) {
				bts[i] = (byte) Integer.parseInt(
						hex.substring(2 * i, 2 * i + 2), 16);
			}
			return new String(bts);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 瀵硅薄杞琂SON
	 * 
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj) {
		JSONObject jsonObject = JSONObject.fromObject(obj);
		return jsonObject.toString();
	}

	/**
	 * 涓嫳鏂囨贩鍚堝瓧绗︽埅鍙栨柟娉曪紙鍏ㄨ绠�1涓級
	 * 
	 * @param str
	 * @param toCount
	 * @param more
	 * @return
	 */
	public static String subStringByChinese(String str, int toCount, String more) {
		toCount = toCount * 2;
		int reInt = 0;
		String reStr = "";
		if (str == null)
			return "";
		char[] tempChar = str.toCharArray();
		for (int kk = 0; (kk < tempChar.length && toCount > reInt); kk++) {
			String s1 = String.valueOf(tempChar[kk]);
			byte[] b = s1.getBytes();
			reInt += b.length;
			reStr += tempChar[kk];
		}
		if (toCount == reInt || (toCount == reInt - 1))
			reStr += more;
		return reStr;
	}

	public static int toInt(String str) {
		return NumberUtils.toInt(str + "", 0);
	}

	public static int toInt(String str, int defaultValue) {
		return NumberUtils.toInt(str + "", defaultValue);
	}

	public static int areaIdToCounty(String site, String areaId) {
		int county = 0;
		String[] siteSplit = site.split("\\.");
		String[] areaIdSplit = areaId.split("\\.");
		if (areaIdSplit.length > siteSplit.length) {
			county = NumberUtils.toInt(areaIdSplit[siteSplit.length], 0);
		}
		return county;
	}

	public static int areaIdToArea(String site, String areaId) {
		int area = 0;
		String[] siteSplit = site.split("\\.");
		String[] areaIdSplit = areaId.split("\\.");
		if (areaIdSplit.length > siteSplit.length + 1) {
			area = NumberUtils.toInt(areaIdSplit[siteSplit.length + 1], 0);
		}
		return area;
	}

	public static boolean hasText(Object s) {
		if (s == null || s.toString().equals("")
				|| s.toString().trim().equals(""))
			return false;
		return true;
	}

	public static int StringLength(String value) {
		int valueLength = 0;
		String chinese = "[\u0391-\uFFE5]";
		/* 鑾峰彇瀛楁鍊肩殑闀垮害锛屽鏋滃惈涓枃瀛楃锛屽垯姣忎釜涓枃瀛楃闀垮害涓�2锛屽惁鍒欎负1 */
		for (int i = 0; i < value.length(); i++) {
			/* 鑾峰彇涓�涓瓧绗� */
			String temp = value.substring(i, i + 1);
			/* 鍒ゆ柇鏄惁涓轰腑鏂囧瓧绗� */
			if (temp.matches(chinese)) {
				/* 涓枃瀛楃闀垮害涓�2 */
				valueLength += 2;
			} else {
				/* 鍏朵粬瀛楃闀垮害涓�1 */
				valueLength += 1;
			}
		}
		return valueLength;
	}

	public static String html2Text(String inputString) {
		String htmlStr = inputString; // 鍚玥tml鏍囩鐨勫瓧绗︿覆
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;

		java.util.regex.Pattern p_html1;
		java.util.regex.Matcher m_html1;

		try {
			String regEx_script = "<[//s]*?script[^>]*?>[//s//S]*?<[//s]*?///[//s]*?script[//s]*?>"; // 瀹氫箟script鐨勬鍒欒〃杈惧紡{鎴�<script[^>]*?>[//s//S]*?<///script>
			String regEx_style = "<[//s]*?style[^>]*?>[//s//S]*?<[//s]*?///[//s]*?style[//s]*?>"; // 瀹氫箟style鐨勬鍒欒〃杈惧紡{鎴�<style[^>]*?>[//s//S]*?<///style>
			String regEx_html = "<[^>]+>"; // 瀹氫箟HTML鏍囩鐨勬鍒欒〃杈惧紡
			String regEx_html1 = "<[^>]+";
			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 杩囨护script鏍囩

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 杩囨护style鏍囩

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 杩囨护html鏍囩

			p_html1 = Pattern.compile(regEx_html1, Pattern.CASE_INSENSITIVE);
			m_html1 = p_html1.matcher(htmlStr);
			htmlStr = m_html1.replaceAll(""); // 杩囨护html鏍囩

			textStr = htmlStr;

		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}

		return textStr;// 杩斿洖鏂囨湰瀛楃涓�
	}
}
