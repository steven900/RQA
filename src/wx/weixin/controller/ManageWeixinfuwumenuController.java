package wx.weixin.controller;

import java.io.Writer;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jdog.frameworks.util.RequestUtil;
import com.jdog.frameworks.util.StringUtil;

import wx.weixin.bean.Buttonw;
import wx.weixin.bean.ClickButton;
import wx.weixin.bean.ComplexButton;
import wx.weixin.bean.Menu;
import wx.weixin.bean.Message;
import wx.weixin.bean.ViewButton;
import wx.weixin.bean.Weixinfuwumenu;
import wx.weixin.data.ParametersAPI;
import wx.weixin.service.BussinessCoreService;
import wx.weixin.service.WeixinfuwumenuService;
import wx.weixin.util.MessageUtil;
import wx.weixin.util.WeixinUtil;

@Controller
@RequestMapping("/manage/weixinfuwumenu")
public class ManageWeixinfuwumenuController {
	
	String views = ParametersAPI.VIEWS;
	
	@Resource
	private WeixinfuwumenuService weixinfuwumenuService;

	@Resource
	private BussinessCoreService bussinessCoreService;

	/**
	 * 微信开发请求数据模拟
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/weixin")
	public String weixin(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<Weixinfuwumenu> list = weixinfuwumenuService.menulist();
		model.addAttribute("forwardUrl", RequestUtil.encodeUrl(request));
		model.addAttribute("list", list);
		return views+"/weixindep";
	}

	@RequestMapping(value = "/msg", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String msg(HttpServletRequest request) {
		Message msg = new Message();
		String text = RequestUtil.toString(request, "text", "");
		int msgrel = RequestUtil.toInt(request, "msgrel");
		String eventkey = RequestUtil.toString(request, "eventkey", "");
		String to = RequestUtil.toString(request, "to", "");
		String from = RequestUtil.toString(request, "from", "");
		msg.setMsgrel(msgrel);
		msg.setFromUserName(from);
		msg.setToUserName(to);
		msg.setContent(text + "#");
		msg.setEventKey(eventkey);
		System.out.println(msgrel + ":" + text + ":" + from + ":eventkey:"
				+ eventkey);
		// 文字返回
		String res = this.msgTransport(msg);
		System.out.println(res);
		if (res == null) {
			return "服务器反馈为空";
		}
		Message requestData = MessageUtil.getTransData(res);
		return requestData.getContent();
	}

	
	public String msgTransport(Message msg){
		
		int transMsgType = msg.getMsgrel();
		String server = msg.getFromUserName();
		String user = msg.getToUserName();
		
		if(transMsgType==1){
			String txt = msg.getContent();
			return bussinessCoreService.transprotText(txt,server,user);
		}
		
		if(transMsgType==7){
			return bussinessCoreService.transprotSubmit(msg,server,user);
		}
		
		if(transMsgType ==8){
			String event = msg.getEventKey();
			return bussinessCoreService.transprotMenu(event,server,user);
		}
		
		return null;
	}
	
	@RequestMapping("/list")
	public String mweixinfuwumenuList(HttpServletRequest request,
			HttpServletResponse response, Model model,
			Weixinfuwumenu weixinfuwumenu) {
		List<Weixinfuwumenu> list = weixinfuwumenuService.menulist();
		model.addAttribute("forwardUrl", RequestUtil.encodeUrl(request));
		model.addAttribute("list", list);
		return views+"/weixinfuwumenulist";
	}

	@RequestMapping("/delete")
	public String weixinfuwumenuDelete(HttpServletRequest request,
			HttpServletResponse response, Model model,
			Weixinfuwumenu weixinfuwumenu, String forwardUrl) {
		weixinfuwumenuService.delete(weixinfuwumenu);
		return "redirect:" + StringUtil.hex2str(forwardUrl);
	}

	@RequestMapping("/edit")
	public String weixinfuwumenuEdit(HttpServletRequest request,
			HttpServletResponse response, Model model,
			Weixinfuwumenu weixinfuwumenu, String forwardUrl) {
		Integer pid = weixinfuwumenu.getPid();
		if (pid == null)
			pid = 0;
		if (weixinfuwumenu.getId() != null) {
			Weixinfuwumenu data = weixinfuwumenuService.findById(weixinfuwumenu
					.getId());
			model.addAttribute("weixinfuwumenu", data);
		} else {
			Weixinfuwumenu data = new Weixinfuwumenu();
			data.setPid(pid);
			model.addAttribute("weixinfuwumenu", data);
		}

		model.addAttribute("forwardUrlBack", StringUtil.hex2str(forwardUrl));
		return views+"/weixinfuwumenuedit";
	}

	@RequestMapping("/save")
	public String weixinfuwumenuSave(HttpServletRequest request,
			HttpServletResponse response, Model model,
			Weixinfuwumenu weixinfuwumenu, String forwardUrlBack) {
		if (weixinfuwumenu.getId() != null) {
			weixinfuwumenuService.update(weixinfuwumenu);
		} else {
			weixinfuwumenuService.save(weixinfuwumenu);
		}
		return "redirect:" + forwardUrlBack;
	}

	@RequestMapping("/dorder")
	public String dorder(HttpServletRequest request,
			HttpServletResponse response, Model model,
			Weixinfuwumenu weixinfuwumenu, String forwardUrl) {
		Weixinfuwumenu data = new Weixinfuwumenu();
		data.setId(weixinfuwumenu.getId());
		data.setDorder(weixinfuwumenu.getDorder());
		weixinfuwumenuService.update(weixinfuwumenu);
		return "redirect:" + StringUtil.hex2str(forwardUrl);
	}
	
	/**
	 * 微信创建按钮
	 * 
	 * @param model
	 * @param writer
	 * @return
	 */
	@RequestMapping("/createMenu.do")
	public String createMenu(Model model, Writer writer, String forwardUrl) {

		
		List<Weixinfuwumenu> list = weixinfuwumenuService.menulist();
		Menu menu = new Menu();
		int lsize = list.size();
		if (lsize != 0) {
			Buttonw[] btall = new Buttonw[lsize];
			
			for (int j = 0; j < lsize; j++) {
				Weixinfuwumenu m = list.get(j);
				int msize = m.getList().size();
				Buttonw[] bt = new Buttonw[msize];
				if (msize != 0) {
					int i;
					for (i = 0; i < msize; i++) {
						Weixinfuwumenu mz = m.getList().get(i);
							if (mz.getWtype() != null && mz.getWtype().equals("view")){
								bt[i] = new ViewButton(mz.getName(), mz.getWtype(),mz.getUrl());
							}
							if (mz.getWtype() != null && mz.getWtype().equals("click")){
								bt[i] = new ClickButton(mz.getName(), mz.getWtype(),mz.getUrl());
							}
					}
					btall[j] = new ComplexButton(m.getName(), bt);
				}else{
					if (m.getWtype() != null && m.getWtype().equals("view")){
						btall[j] = new ViewButton(m.getName(), m.getWtype(),m.getUrl());
					}
					if (m.getWtype() != null && m.getWtype().equals("click")){
						btall[j] = new ClickButton(m.getName(), m.getWtype(),m.getUrl());
					}
				}
				
			}
			menu.setButton(btall);
		}
		String result = WeixinUtil.MenuCreate(menu);
		model.addAttribute("result", result);
		return views+"/result";
	}

}
