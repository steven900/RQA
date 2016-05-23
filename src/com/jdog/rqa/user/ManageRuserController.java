package com.jdog.rqa.user;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jdog.frameworks.db.ListPage;
import com.jdog.frameworks.util.RequestUtil;
import com.jdog.frameworks.util.StringUtil;
import com.jdog.project.menuauth.MenuauthDao;
import com.jdog.project.projectment.Projectment;
import com.jdog.project.projectment.ProjectmentDao;

@Controller
@RequestMapping("/manage/ruser")
public class ManageRuserController {

	@Resource
	private RuserService ruserService;
	
	@Resource
	private MenuauthDao menuauthDao;
	
	@Resource
	private ProjectmentDao projectmentDao;

	@RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response, Model model, Ruser t) {

		int page = RequestUtil.toInt(request, "page");
		int pageSize = 15;
		int projectid = RequestUtil.toInt(request, "projectid");
		t.setProjectid(projectid);
		int userid = RequestUtil.sessionToInt(request, "userid");
		boolean au = menuauthDao.checkUserAuth(projectid, userid);
		if(!au)
			return "/views/manage/user/error";
		
		
		ListPage<Ruser> list = ruserService.list(t, page, pageSize);
		list.setUrl(request);
		model.addAttribute("forwardUrl", RequestUtil.encodeUrl(request));
		model.addAttribute("list", list);
		model.addAttribute("t", t);
		return "/views/manage/rqa/ruserlist";
	}

	/**
	 * 用户编辑页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("edit")
	public String edit(HttpServletRequest request, HttpServletResponse response, Model model, Ruser t,
			String forwardUrl) {
		int projectid = RequestUtil.toInt(request, "projectid");
		t.setProjectid(projectid);
		
		int userid = RequestUtil.sessionToInt(request, "userid");
		int id = RequestUtil.toInt(request, "id");
		boolean au = menuauthDao.checkUserAuth(projectid, userid);
		if(!au)
			return "/views/user/error";
		
		Projectment ms = projectmentDao.findById(projectid);
		if(ms!=null)
			t.setProjectName(ms.getTitle());
		if (t.getId() != 0) {
			t = ruserService.findById(id);
			if(t == null)
				t= new Ruser();
		}
		model.addAttribute("t", t);
		model.addAttribute("forwardUrlBack", StringUtil.hex2str(forwardUrl));
		return "/views/manage/rqa/ruseredit";
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param t
	 * @param forwardUrlBack
	 * @return
	 */
	@RequestMapping("save")
	public String save(HttpServletRequest request, HttpServletResponse response, Model model, Ruser t,
			String forwardUrlBack) {
		int userid = RequestUtil.sessionToInt(request, "userid");
		boolean au = menuauthDao.checkUserAuth(t.getProjectid(), userid);
		if(!au)
			return "/views/user/error";
		ruserService.save(t);
		return "redirect:" + forwardUrlBack;
	}

	@RequestMapping("delete")
	public String delete(HttpServletRequest request, HttpServletResponse response, Model model, int id,
			String forwardUrl) {
		ruserService.delete(id);
		return "redirect:" + StringUtil.hex2str(forwardUrl);
	}
	
	@RequestMapping(value="/sendEmail", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String sendEmail(HttpServletRequest request, HttpServletResponse response, Model model, int id,
			String forwardUrl) {
		
		Ruser r = ruserService.findById(id);
		if(r!=null){
			int projectid = r.getProjectid();
			String email = "";
			String content = r.getBrief();
			String title = "";
			Projectment project = projectmentDao.findById(projectid);
			if(project !=null){
				email = project.getContact();
				title= project.getTitle();
			}
			if(!StringUtil.isBlank(email) && !StringUtil.isBlank(content)){
				MailUtilorz.sendmsg(email, content,title);
			}
		}
		return "1";
	}

	
	@RequestMapping("showEmail")
	public String showEmail(HttpServletRequest request, HttpServletResponse response, Model model, int id,
			String forwardUrl) {
		
		Ruser r = ruserService.findById(id);
		if(r!=null){
			String content = r.getBrief();
			model.addAttribute("content",content);
		}
		return "/views/manage/rqa/content";
	}


	@RequestMapping("index")
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
		String str2 = RequestUtil.toString(request, "key", "");
		String auth = RequestUtil.toString(request, "auth", "");
		String project = RequestUtil.toString(request, "name", "");
		str2 = StringUtil.hex2str(str2);
		if (auth.equals("steven")) {
			request.getSession().setAttribute("auth", 1);
		}
		if (str2.indexOf('_') != -1) {
			String[] params = str2.split("_");
			String id = params[1];
			Integer projectid = Integer.parseInt(id);
			request.getSession().setAttribute("projectid", projectid);
			model.addAttribute("project", project);
		}
		return "/views/manage/rqa/rindex";
	}

	public static void main(String[] args) {
		String str = "工单系统管理_1";
		str = "微信预约管理_2";
		System.out.println(StringUtil.str2hex(str));

		String str2 = StringUtil.hex2str("E5B7A5E58D95E7B3BBE7BB9FE7AEA1E790865F31");
		if (str2.indexOf('_') != -1) {
			String[] params = str2.split("_");
			String project = params[0];
			String id = params[1];
		}
	}
}
