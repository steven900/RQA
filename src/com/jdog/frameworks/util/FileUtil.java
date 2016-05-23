package com.jdog.frameworks.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {

	public void mkdir(String path, String realPath) {
		// String[] ss = path.split("/");
		// String t_path = "";
		File file = new File(realPath + path);
		if (!file.exists()) {
			file.mkdirs();
		}
		// for (int i = 0; i < ss.length; i++) {
		// t_path += ss[i] + "/";
		// File file = new File(realPath + t_path);
		// System.out.println("for each::::::::::::::" + file.getName());
		// if (!file.exists())
		// file.mkdir();
		// }
	}

	/**
	 * 建立全路径
	 * 
	 * @param path
	 */
	public void mkdir(String path) {
		String[] ss = path.split("\\\\");
		String t_path = "";

		for (int i = 1; i < ss.length; i++) {
			t_path += "\\" + ss[i];
			File file = new File(ss[0] + t_path);
			if (!file.exists())
				file.mkdir();
		}
	}

	/**
	 * 建立全路径（包含文件名）
	 * 
	 * @param path
	 */
	public void mkdirWithFilename(String filename) {
		String[] ss = filename.split("/");
		String t_path = "";

		for (int i = 1; i < ss.length - 1; i++) {
			t_path += "/" + ss[i];

			// System.out.println("path:" + ss[0] + t_path);

			File file = new File(ss[0] + t_path);
			if (!file.exists())
				file.mkdir();
		}
	}

	public void delete(String filename, String path, String realPath) {
		String filePath = realPath + path + filename;
		delete(filePath);
	}

	public void delete(String filename) {
		File file = new File(filename);
		file.delete();
	}

	public void savefilenow(String filename, String path, String buffer,
			String realPath) {
		savefilenow(filename, path, buffer, realPath, "UTF-8");
	}

	public void savefilenow(String filename, String path, String buffer,
			String realPath, String encode) {
		byte[] bytes;
		try {
			bytes = buffer.getBytes(encode);
			buffer = new String(bytes, encode);
		} catch (Exception e) {
		}

		String p = realPath + path + filename;
		BufferedWriter bw = null;
		try {
			this.mkdir(path, realPath);
			bw = new BufferedWriter(new FileWriter(p));
			bw.write(buffer);
			bw.flush();
			bw.close();
		} catch (IOException e) {
			System.out.println(p + "写入错误");
		}
	}

	public void savefile(String filename, String path, String buffer,
			String realPath) {
		savefile(filename, path, buffer, realPath, "utf-8");
	}

	public void savefile(String filename, String path, String buffer,
			String realPath, String encode) {
		String p = realPath + path + filename;
		try {
			this.mkdir(path, realPath);
			FileOutputStream o = new FileOutputStream(p);
			o.write(buffer.getBytes(encode));
			o.flush();
			o.close();
		} catch (IOException e) {
			System.out.println(p + "写入错误");
		}
	}

}
