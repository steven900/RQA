package com.jdog.frameworks.upload.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jdog.frameworks.util.AppSender;
import com.jdog.frameworks.util.DateUtil;
import com.jdog.frameworks.util.FileUtil;
import com.jdog.frameworks.util.RequestUtil;

/**
 * 远程图片上传
 * 
 * @author steven
 *
 */
@Controller
@RequestMapping("/filemanage/upload")
public class FileUpload {

	private static String domain = "http://120.76.145.40";
	private static FileUtil fileUtil = new FileUtil();
	private static String REALPATH = "/" + RequestUtil.getWebRoot();

	@RequestMapping("/fileupload")
	@ResponseBody
	public String fileupload(@RequestParam("file") MultipartFile multipartFile, Model model,
			HttpServletRequest request) {

		Map<String, Object> data = new HashMap<String, Object>();
		int rel = 0;
		String msg = null;

		String path = "/appfile/" + DateUtil.format(new Date(), "yyyy-MM/dd");
		String filename = this.getFilename(multipartFile, false);
		if (filename == null ) {
			rel = 0;
			msg = "文件格式错误";
		} else {
			rel = 1;
			msg = "";
			// 上传
			this.getFile(multipartFile, path, filename);
			// 域名
			System.out.println(filename);
			String res = domain + path + "/" + filename;
			data.put("result", res);
		}
		System.out.println(AppSender.msg(rel, data, msg));
		return AppSender.msg(rel, data, msg);
	}

	private String getFilename(MultipartFile multipartFile, boolean random) {

		String o_filename = multipartFile.getOriginalFilename();
		System.out.println(o_filename);
		if (o_filename.equals(""))
			return null;
		String fileend = "";
		if (o_filename.endsWith(".docx")) {
			fileend=".docx";
		}

		String[] arr = o_filename.split("\\.");
		String fileExt = ("." + arr[arr.length - 1]).toLowerCase();
		String filename = String.valueOf(new Date().getTime()) + fileExt;
		if (random == true) {
			int num = (int) (Math.random() * 1000);
			filename = num + "_" + String.valueOf(new Date().getTime()) + fileExt;
		}
		return filename;

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
			extName = "*.gif|*.jpg|*.png|*.jpeg|*.mp3|*.docx";
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
	 * 获取文件对象
	 * 
	 * @param multipartFile
	 * @param path
	 * @param filename
	 * @return
	 */
	public File getFile(MultipartFile multipartFile, String path, String filename) {
		fileUtil.mkdir(path, REALPATH);
		File file = new File(REALPATH + path + "/" + filename);

		try {
			FileCopyUtils.copy(multipartFile.getBytes(), file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return file;
	}

}
