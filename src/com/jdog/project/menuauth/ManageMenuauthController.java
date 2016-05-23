package com.jdog.project.menuauth;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdog.frameworks.util.RequestUtil;
import com.jdog.frameworks.util.StringUtil;

@Controller
@RequestMapping("/manage/menuauth")
public class ManageMenuauthController {

	@Resource
	private MenuauthDao menuauthDao;
	
	@RequestMapping("rqa")
	public String delete(HttpServletRequest request, HttpServletResponse response, Model model,int projectid,int userid,
			String forwardUrl) {
		int checkid = RequestUtil.toInt(request, "rqa");
		
		menuauthDao.updateRqa(projectid,checkid,userid);
		return "redirect:" + StringUtil.hex2str(forwardUrl);
	}
	
	@RequestMapping("price")
	public String price(HttpServletRequest request, HttpServletResponse response, Model model,int projectid,int userid,
			String forwardUrl) {
		int checkid = RequestUtil.toInt(request, "price");
		
		menuauthDao.updateprice(projectid,checkid,userid);
		return "redirect:" + StringUtil.hex2str(forwardUrl);
	}
}
