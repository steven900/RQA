package com.jdog.frameworks.upload.service;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.jdog.frameworks.util.FileUtil;
import com.jdog.frameworks.util.RequestUtil;

@Service
public class UploadService {
	
	private static String REALPATH = "/" + RequestUtil.getWebRoot() + "uploadImages/";
	private static FileUtil fileUtil = new FileUtil();
	
	
	/**
	 * 上传文件(原始图)单张检查大小
	 * 
	 * @param width
	 * @param height
	 * @param scale
	 * @param bigPic
	 * @param smallPath
	 * @param smallFilename
	 */
	public int uploadBigCheck(File srcFile, int width, int height, int size,
			boolean fixedSize, String extName, String path, String filename) {

		// 检查图片
		int flag = this.checkImageFile(srcFile, path, filename, width, height,
				size, fixedSize, extName);
		if (flag > 0) {
			// 删除暂时文件
			srcFile.delete();
			return flag;
		}
		return flag;
	}
	
	/**
	 * 上传文件(原始图、缩略图)检查原始图片大小(调用上面)
	 * 
	 * @param width
	 * @param height
	 * @param scale
	 * @param bigPic
	 * @param smallPath
	 * @param smallFilename
	 */
	public int uploadBigSmallCheck(File srcFile, int bigWidth, int bigHeight,
			int bigSize, boolean bigFixedSize, int smallWidth, int smallHeight,
			int smallSize, boolean scale, boolean scaleBg, String extName,
			String bigPicPath, String smallPicPath, String filename,
			int waterMarkMode) {

		// 检查图片
		int flag = this.checkImageFile(srcFile, bigPicPath, filename, bigWidth,
				bigHeight, bigSize, bigFixedSize, extName);
		if (flag > 0) {
			// 删除暂时文件
			srcFile.delete();
			return flag;
		}

		// 大图路径
		String bigPic = REALPATH + bigPicPath + "/"+filename;

		// 生成缩略图
		this.imageResize(smallWidth, smallHeight, scale, scaleBg, bigPic,
				smallPicPath, "s_" + filename);
		return flag;
	}
	
	/**
	 * 生成微缩图（提高图片质量）
	 * 
	 * @param width
	 * @param height
	 * @param scale
	 * @param bigPic
	 * @param smallPath
	 * @param smallFilename
	 */
	private boolean imageResize(int width, int height, boolean scale,
			boolean scale_bg, String srcFile, String desPath, String desFilename) {
		// long time = 0, firstTime = System.currentTimeMillis();
		FileInputStream fis = null;
		try {
			
			fileUtil.mkdir(desPath, REALPATH);
			String desFile = REALPATH + desPath +"/"+ desFilename;
//		System.out.println(desFile);
			fis = new FileInputStream(srcFile);
			BufferedImage fromBI = ImageIO.read(fis);
			BufferedImage toBI = null;

			// time = this.printTime("irs1", firstTime);
			int w = 0, h = 0;
			// 同比例缩小
			if(scale == true) {
				float xy = (float) width / (float) height;
				if (((float) fromBI.getWidth() / fromBI.getHeight()) > xy) {
					xy = (float) width / (float) fromBI.getWidth();
				} else {
					xy = (float) height / (float) fromBI.getHeight();
				}
				if (xy > 1) {// 图片变大则用原图大小
					w = fromBI.getWidth();
					h = fromBI.getHeight();
				} else {
					w = (int) (xy * fromBI.getWidth());
					h = (int) (xy * fromBI.getHeight());
				}
			} else {
				w = width;
				h = height;
			}

			// time = this.printTime("irs2", time);

			if (scale == true && scale_bg == true) {
				// 生成白底色
				// 计算居中位置
				int ww = (width - w) / 2 < 0 ? 0 : (width - w) / 2;
				int hh = (height - h) / 2 < 0 ? 0 : (height - h) / 2;

				toBI = new BufferedImage(width, height,
						BufferedImage.TYPE_INT_RGB);
				Graphics2D g2 = toBI.createGraphics();
				// time = this.printTime("irs3a", time);
				Color bg = new Color(255, 255, 255);
				g2.setColor(bg);
				g2.fillRect(0, 0, width, height);
				// time = this.printTime("irs4a", time);
				g2.drawImage(fromBI.getScaledInstance(w, h, Image.SCALE_SMOOTH), ww, hh, null);
				g2.dispose();
				// time = this.printTime("irs5a", time);
			} else {
				// 生成原始小图
				toBI = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
				Graphics2D g2 = toBI.createGraphics();
				// time = this.printTime("irs3b", time);
				g2.drawImage(fromBI.getScaledInstance(w, h, Image.SCALE_SMOOTH), 0, 0, null);
				g2.dispose();
				// time = this.printTime("irs4b", time);
			}

			ImageIO.write(toBI, "JPG", new File(desFile));
			fis.close();
			// time = this.printTime("5", time);
			// this.printTime("total", firstTime);

			return true;
		} catch (Exception e) {
			if (fis != null) {
				try {
					fis.close();
				} catch (Exception ep) {
				}
			}
			// e.printStackTrace();
			System.out.println("imageResize Error : " + desPath +"/"+ desFilename);
			return false;
		}
	}

	/**
	 * 获取文件对象
	 * 
	 * @param multipartFile
	 * @param path
	 * @param filename
	 * @return
	 */
	public File getFile(MultipartFile multipartFile, String path,
			String filename) {
		fileUtil.mkdir(path, REALPATH);
		File file = new File(REALPATH + path + "/" + filename);
		
		try {
			FileCopyUtils.copy(multipartFile.getBytes(), file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return file;
	}
	
	/**
	 * 返回文件的大小字节数
	 * 
	 * @param path
	 * @param filename
	 * @return
	 */
	public Map<String, Object> getSize(String path, String filename) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String str = REALPATH + path + "/" + filename;

			// 字节数
			File file_temp = new File(str);
			map.put("size", file_temp.length());

			// 宽度、高度
			FileInputStream fis = new FileInputStream(str);
			BufferedImage bi = ImageIO.read(fis);
			if (bi != null) {
				map.put("width", bi.getWidth());
				map.put("height", bi.getHeight());
			}
			fis.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}
	
	/**
	 * 检查文件拓展名
	 * 
	 * @param filename
	 * @param extName
	 * @return true:通过 false:不通过
	 */
	public boolean checkFileExtName(String filename, String extName) {
		if (extName == null) {
			extName = "*.gif|*.jpg|*.png|*.jpeg|*.doc|*.docx";
		}

		// 检查扩展名
		String[] ff = filename.split("\\.");
		String ext = "";
		if (ff.length > 0) {
			ext = "*." + ff[ff.length - 1];
		} else {
			return false;
		}

		String[] ext_list = extName.split("\\|");
		boolean in = false;
		for (int i = 0; i < ext_list.length; i++) {
			if (ext.toLowerCase().equals(ext_list[i].toLowerCase())) {
				in = true;
			}
		}
		if (in == false)
			return false;

		return true;
	}
	
	/**
	 * 检查图片
	 * 
	 * @param srcFile
	 * @param path
	 * @param filename
	 * @param width
	 * @param height
	 * @param size
	 * @param fixedSize
	 * @param extName
	 * @return 0：符合要求； 1：尺寸大小未符合； 2：图片异常
	 */
	private int checkImageFile(File srcFile, String path, String filename,
			int width, int height, int size, boolean fixedSize, String extName) {
		FileInputStream fis = null;
		
		
		try {
			// 上传临时图片
			filename = REALPATH + path + "/" + filename;
			
			fis = new FileInputStream(filename);
			BufferedImage bi = ImageIO.read(fis);
			int w = bi.getWidth(), h = bi.getHeight();
			fis.close();
			// 检查图片大小
			if (srcFile.length() > size * 1024)
				return 1;
			if (this.checkFileExtName(filename, extName) == false)
				return 1;
			// 检查宽度、高度
			if (fixedSize == false) {
				// 　判断图片是否在指定大小之内
				if (w > width)
					return 1;
				if (h > height)
					return 1;
			} else {
				// 判断图片是否为指定大小
				if (w != width)
					return 1;
				if (h != height)
					return 1;
			}

			return 0;
		} catch (Exception e) {
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (Exception ep) {
			}
			 e.printStackTrace();
			System.out.println("checkImageFile Error : " + "/uploadImages/" + path + "/" + filename);
		}
		return 2;
	}
}
