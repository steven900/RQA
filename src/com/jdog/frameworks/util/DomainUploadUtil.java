package com.jdog.frameworks.util;

public class DomainUploadUtil {

	/**
	 * 处理跨域上传的图片名
	 * 
	 * @param pic
	 * @param getSmall
	 * @return
	 */
	public static String getPicture(String pic, boolean getSmall) {
		if (pic == null || "".equals(pic))
			return null;

		if (getSmall == true) {
			// big --> small
			String temp = pic;
			temp = temp.replaceFirst("http://", "");
			int index = temp.indexOf("/");
			if (index != -1) {
				temp = temp.substring(temp.indexOf("/"));
				String[] ss = temp.split("/");
				try {
					String vv = ss[ss.length - 1];
					if (vv.substring(0, 2).equals("s_")) {
						return pic;
					} else {
						return pic = pic.replaceFirst(vv, "s_" + vv);
					}
				} catch (Exception e) {
					System.out.println("pic==========" + pic);
					return null;
				}
			} else {
				return pic;
			}

		} else {
			return pic.replaceFirst("s_", "");
		}
	}
	
	/**
	 * 处理从数据库中获取的图片路径，返回子域名上的图片新路径
	 * 
	 * @param pic
	 *            数据库中存储的图片路径
	 * @param folder
	 *            图片种类，形如 user/userPhoto
	 * @param getSmall
	 *            是否获取小图
	 * @return
	 */
	public static String getUrl(String pic, boolean getSmall) {
		if (pic == null || "".equals(pic))
			return null;
		int index = pic.indexOf("/archive/");

		// 新数据
		if (index == -1) {
			return getPicture(pic, getSmall);
			// 老数据
		} else {
			// 获取小图
			if (getSmall) {
				return pic.replace("/archive/big/", "/archive/small/");
				// 获取大图
			} else {
				return pic.replace("/archive/small/", "/archive/big/");
			}
		}
	}
	
}
