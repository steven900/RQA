package com.jdog.frameworks.upload.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jdog.frameworks.upload.service.UploadService;
import com.jdog.frameworks.util.DateUtil;
import com.jdog.frameworks.util.RequestUtil;

import net.sf.json.JSONObject;

/**
 * 图片上传专用Controller
 * @author Jdog.Asher
 * @Date 2014-2-12
 */
@Controller
@RequestMapping("/manage/upload")
public class UploadController {
	
	@Resource
	private UploadService uploadService;

	private static String MSG1 = "请选择图片";
	private static String MSG2 = "非法服务器请求";
	private static String MSG3 = "请上传指定格式的文件";
	private static String MSG4 = "上传成功";
	private static String MSG5 = "选择的图片未符合要求的宽度、高度和文件大小";
	private static String MSG6 = "您上传的图片太大，超过我们的最大限制４ＭＢ";
	private static String MSG7 = "图片存在异常，请确认图片是否能正常显示";
	public static String domain="120.76.145.40";
	
	@RequestMapping("/uploadBig")
	public String uploadBig(@RequestParam("upload") MultipartFile multipartFile, String folder, Model model, HttpServletRequest request) {
		// 获取文件名和路径
		String path = getPath(folder);
		String filename = getFilename(multipartFile);
		if (filename == null) {
			return getFilenameJson(model, "", "", MSG1);
		}
		
//		// 安全域名检查
//		if (checkDomain(request) == false) {
//			return getFilenameJson(model, "", "", MSG2);
//		}
		
		// 安全文件拓展名检查
		if (uploadService.checkFileExtName(filename, null) == false) {
			return getFilenameJson(model, "", "", MSG3);
		}
		
		// 上传
		uploadService.getFile(multipartFile, path, filename);
		
		return getFilenameJson(model, path, filename, MSG4);
	}
	
	/**
	 * 上传大图（原始尺寸）并检查尺寸
	 */
	@RequestMapping("/uploadBigCheck")
	public String uploadBigCheck(@RequestParam("upload") MultipartFile multipartFile, String folder, int width, int height, int size, int fixed, Model model, HttpServletRequest request) {
		// 获取文件名和路径
		String path = getPath(folder);
		String filename = getFilename(multipartFile);
		if (filename == null) {
			return getFilenameJson(model, "", "", MSG1);
		}

		// 调试信息
//		DebugInfo(multipartFile, request, path + filename);
		// 安全域名检查
//		if (checkDomain(request) == false) {
//			return getFilenameJson(model, "", "", MSG2);
//		}

		// 安全文件拓展名检查
		if (uploadService.checkFileExtName(filename, null) == false) {
			return getFilenameJson(model, "", "", MSG3);
		}

		// 上传
		File file = uploadService.getFile(multipartFile, path, filename);
		int result = uploadService.uploadBigCheck(file, width, height, size, fixed == 1 ? true : false, null, path, filename);
		// 返回
		String json = "";
		switch (result) {
		case 0:json = getFilenameJson(model, path, filename, MSG4);break;
		case 1:json = getFilenameJson(model, "", "", MSG5);break;
		case 2:json = getFilenameJson(model, "", "", MSG7);break;
		}

		return json;
	}
	
	
	
	/**
	 * 上传大图小图（检查图片大小）
	 * 
	 * @param multipartFile
	 * @param bigWidth
	 * @param bigHeight
	 * @param bigSize
	 * @param bigFixed
	 * @param smallWidth
	 * @param smallHeight
	 * @param smallSize
	 * @param smallFixed
	 * @param ext
	 * @param watermark
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/uploadBigSmallCheck")
	public String uploadBigSmallCheck(@RequestParam("upload") MultipartFile multipartFile, 
			int bigWidth, int bigHeight, int bigSize, int bigFixed,
			int smallWidth, int smallHeight, int smallSize, int scale, int scaleBg, int watermark, String folder, Model model, HttpServletRequest request) {

		System.out.println("--------------------------------");
		// 获取文件名和路径
		String path = getPath(folder);
		String filename = getFilename(multipartFile);
		if (filename == null) {
			return getFilenameJson(model, "", "", MSG1);
		}

		// 调试信息
		DebugInfo(multipartFile, request, path + filename);

		// 安全域名检查
//		if (checkDomain(request) == false) {
//			return getFilenameJson(model, "", "", MSG2);
//		}

		// 安全文件拓展名检查
		if (uploadService.checkFileExtName(filename, null) == false) {
			return getFilenameJson(model, "", "", MSG3);
		}
			
		// 上传
		File file = uploadService.getFile(multipartFile, path, filename);
		int result = uploadService.uploadBigSmallCheck(file, bigWidth, bigHeight, bigSize, bigFixed == 1 ? true : false, smallWidth, smallHeight, smallSize,
				scale == 1 ? true : false, scaleBg == 1 ? true : false, null, path, path, filename, watermark);
		// 返回
		String json = "";
		switch (result) {
		case 0:json = getFilenameJson(model, path, filename, MSG4);break;
		case 1:json = getFilenameJson(model, "", "", MSG5);break;
		case 2:json = getFilenameJson(model, "", "", MSG7);break;
		}
		return json;
	}
	
	
	
	@RequestMapping("/fileupload")
	public String fileupload(@RequestParam("upload") MultipartFile multipartFile, 
			int bigWidth, int bigHeight, int bigSize, int bigFixed,
			int smallWidth, int smallHeight, int smallSize, int scale, int scaleBg, int watermark, String folder, Model model, HttpServletRequest request) {

		System.out.println("--------------------------------");
		// 获取文件名和路径
		String path = getPath(folder);
		String filename = getFilename(multipartFile);
		if (filename == null) {
			return getFilenameJson(model, "", "", MSG1);
		}

		// 调试信息
		DebugInfo(multipartFile, request, path + filename);

		// 安全域名检查
//		if (checkDomain(request) == false) {
//			return getFilenameJson(model, "", "", MSG2);
//		}

		// 安全文件拓展名检查
		if (uploadService.checkFileExtName(filename, null) == false) {
			return getFilenameJson(model, "", "", MSG3);
		}
			
		// 上传
		File file = uploadService.getFile(multipartFile, path, filename);
		int result = uploadService.uploadBigSmallCheck(file, bigWidth, bigHeight, bigSize, bigFixed == 1 ? true : false, smallWidth, smallHeight, smallSize,
				scale == 1 ? true : false, scaleBg == 1 ? true : false, null, path, path, filename, watermark);
		// 返回
		String json = "";
		switch (result) {
		case 0:json = getFilenameJson(model, path, filename, MSG4);break;
		case 1:json = getFilenameJson(model, "", "", MSG5);break;
		case 2:json = getFilenameJson(model, "", "", MSG7);break;
		}
		return json;
	}
	
	
	private String getPath(String folder) {
		return folder + "/" + DateUtil.format(new Date(), "yyyy-MM/dd");
	}
	
	private String getFilename(MultipartFile multipartFile) {
		return this.getFilename(multipartFile, false);
	}
	
	private String getFilename(MultipartFile multipartFile, boolean random) {
		String o_filename = multipartFile.getOriginalFilename();

		if (o_filename.equals(""))
			return null;

		String[] arr = o_filename.split("\\.");
		String fileExt = ("." + arr[arr.length - 1]).toLowerCase();
		String filename = String.valueOf(new Date().getTime()) + fileExt;
		if (random == true) {
			int num = (int) (Math.random() * 1000);
			filename = num + "_" + String.valueOf(new Date().getTime()) + fileExt;
		}
		return filename;
	}
	
	private String getFilenameJson(Model model, String path, String filename, String message) {
		Map<String, Object> map = new HashMap<String, Object>();

		// 文件名、路径
		map.put("path", "uploadImages/" + path + "/");
		map.put("filename", filename);

		// 图片大小、宽度、高度
		if(path != null && !path.equals("") && filename != null && !filename.equals("")) {
			Map<String, Object> map_data = uploadService.getSize(path, filename);
			map.put("size", map_data.get("size"));
			map.put("width", map_data.get("width"));
			map.put("height", map_data.get("height"));
		}

		// 域名
		map.put("domain", "http://"+domain+"/");
		map.put("url", "http://"+domain+"/uploadImages/" + path + "/" + filename);
		// 提示信息
		int messageId = 0;
		if (message.equals(MSG1))
			messageId = 1;
		if (message.equals(MSG2))
			messageId = 2;
		if (message.equals(MSG3))
			messageId = 3;
		if (message.equals(MSG4))
			messageId = 4;
		if (message.equals(MSG5))
			messageId = 5;
		if (message.equals(MSG6))
			messageId = 6;
		if (message.equals(MSG7))
			messageId = 7;
		map.put("messageId", messageId);
		map.put("message", message);

		model.addAttribute("msg", JSONObject.fromObject(map).toString());
		return "views/upload/msg";
	}
	
	private String getFilenameJsonNew(Model model, String path, String filename, String message) {
		Map<String, Object> map = new HashMap<String, Object>();

		// 文件名、路径
		map.put("path", "uploadImages/" + path + "/");
		map.put("filename", filename);

		// 图片大小、宽度、高度
		/*if(path != null && !path.equals("") && filename != null && !filename.equals("")) {
			Map<String, Object> map_data = uploadService.getSize(path, filename);
			map.put("size", map_data.get("size"));
			map.put("width", map_data.get("width"));
			map.put("height", map_data.get("height"));
		}*/

		// 域名
		map.put("domain", "http://www.nuanjx.com/");
		map.put("url", "http://www.nuanjx.com/uploadImages/" + path + "/" + filename);
		// 提示信息
		int messageId = 0;
		if (message.equals(MSG1))
			messageId = 1;
		if (message.equals(MSG2))
			messageId = 2;
		if (message.equals(MSG3))
			messageId = 3;
		if (message.equals(MSG4))
			messageId = 4;
		if (message.equals(MSG5))
			messageId = 5;
		if (message.equals(MSG6))
			messageId = 6;
		if (message.equals(MSG7))
			messageId = 7;
		map.put("messageId", messageId);
		map.put("error", messageId);
		map.put("message", message);

		return JSONObject.fromObject(map).toString();
	}
	
	private void DebugInfo(MultipartFile file, HttpServletRequest request, String filename) {
		StringBuffer sb = new StringBuffer();
		sb.append("-------------------------------------\n");
		sb.append(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		sb.append(" ip:" + RequestUtil.getClientIP(request));
		sb.append("\n");
		sb.append("ContentType:" + file.getContentType());
		sb.append(" OriginalFilename:" + file.getOriginalFilename());
		sb.append(" Size:" + file.getSize() / 1024 + "kb");
		sb.append("\n");
		sb.append("Filename:" + filename);
		System.out.println(sb.toString());
	}
	
	private boolean checkDomain(HttpServletRequest request) {
		String domain = request.getServerName();
//		domain = domain.substring(domain.indexOf(".") + 1);
		if ("|127.0.0.1|".indexOf("|" + domain + "|") != -1 || 1==1) {
			return true;
		}

		System.out.println(new Date() + " 非法上传图片域名 " + domain + " " + request.getLocalAddr());
		return false;
	}
	
	/**
	 * 上传方法ajax
	 */
	@ResponseBody
	@RequestMapping(value="/uploadBigCheckNew", produces="text/plain;charset=UTF-8")
	public String uploadBigCheckNew(@RequestParam("upload") MultipartFile multipartFile, String folder, int width, int height, int size, int fixed, Model model, HttpServletRequest request) {
		// 获取文件名和路径
		String path = getPath(folder);
		String filename = getFilename(multipartFile);
		if (filename == null) {
			return getFilenameJson(model, "", "", MSG1);
		}

		// 调试信息
//		DebugInfo(multipartFile, request, path + filename);
		// 安全域名检查
//		if (checkDomain(request) == false) {
//			return getFilenameJson(model, "", "", MSG2);
//		}

		// 安全文件拓展名检查
		if (uploadService.checkFileExtName(filename, null) == false) {
			return getFilenameJson(model, "", "", MSG3);
		}

		// 上传
		File file = uploadService.getFile(multipartFile, path, filename);
		int result = uploadService.uploadBigCheck(file, width, height, size, fixed == 1 ? true : false, null, path, filename);
		// 返回
		String json = "";
		switch (result) {
		case 0:json = getFilenameJsonNew(model, path, filename, MSG4);break;
		case 1:json = getFilenameJsonNew(model, "", "", MSG5);break;
		case 2:json = getFilenameJsonNew(model, "", "", MSG7);break;
		}

		return json;
	}
	
}
