package com.jdog.project;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import wx.weixin.bean.Article;
import wx.weixin.bean.Message;
import wx.weixin.service.BussinessCoreService;

@Service
public class wxBus implements BussinessCoreService {

	public String sendTextMsgToWX(String to, String server, String content) {
		return null;
	}

	public String sendNewsMessage(String to, String server, List<Article> articles) {
		return null;
	}

	public boolean wxpayNotify(String total_fee, String keycrp) {
		return false;
	}

	public Map<String, String> wxpay(HttpServletRequest request) {
		return null;
	}

	public Map<String, Object> getUserInfo(HttpServletRequest request) {
		return null;
	}

	public String transprotText(String txt, String server, String user) {
		return null;
	}

	public String transprotSubmit(Message msg, String server, String user) {
		return null;
	}

	public String transprotMenu(String event, String server, String user) {
		// TODO Auto-generated method stub
		return null;
	}

	public String enterprisepay(HttpServletRequest request, Model model) {
		// TODO Auto-generated method stub
		return null;
	}

	public String enterprisepayAuth(Map<String, String> ret) {
		// TODO Auto-generated method stub
		return null;
	}

	public String transprotScan(String event, String server, String user) {
		// TODO Auto-generated method stub
		return null;
	}

}
