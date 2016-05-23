package com.jdog.project.bugmanage;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdog.frameworks.db.ListPage;
import com.jdog.frameworks.util.RequestUtil;
import com.jdog.frameworks.util.StringUtil;
import com.jdog.project.menuauth.Menuauth;
import com.jdog.project.menuauth.MenuauthDao;
import com.jdog.project.projectment.Projectment;
import com.jdog.project.projectment.ProjectmentDao;
import com.jdog.project.user.User;
import com.jdog.project.user.UserDao;

@Controller
@RequestMapping("/manage/bug")
public class ManageBugController {

	@Resource
	private BugDao bugDao;
	
	@Resource
	private UserDao userDao;
	
	@Resource
	private ProjectmentDao projectmentDao;
	@Resource
	private MenuauthDao menuauthDao;
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response, Model model, Bug t) {
		int projectid = RequestUtil.toInt(request, "projectid");
		int page = RequestUtil.toInt(request, "page");
		int pageSize = 1566;
		t.setProjectid(projectid);
		
		int userid = RequestUtil.sessionToInt(request, "userid");
		boolean au = menuauthDao.checkUserAuth(projectid, userid);
		if(!au)
			return "/views/manage/user/error";
		
		ListPage<Bug> list = bugDao.list(t, page, pageSize);
		
		if(list!=null && list.getItems()!=null){
			for(Bug b : list.getItems()){
				int uid = b.getSentTo();
				User u = userDao.findById(uid);
				b.setUser(u);
			}
			
		}
		list.setUrl(request);
		model.addAttribute("forwardUrl", RequestUtil.encodeUrl(request));
		model.addAttribute("list", list);
		model.addAttribute("t", t);
		return "/views/manage/bug/buglist";
	}
	
	@RequestMapping("updateMyreport")
	public String updateMyreport(HttpServletRequest request, HttpServletResponse response, Model model, Bug t,
			String forwardUrl) {
		int projectid = RequestUtil.toInt(request, "projectid");
		int userid = RequestUtil.sessionToInt(request, "userid");
		boolean au = menuauthDao.checkUserAuth(projectid, userid);
		if(!au)
			return "/views/manage/user/error";
		
		bugDao.updateB(t);
		t.setState(0);
		return this.myreport(request, response, model, t);
	}
	
	@RequestMapping("/myreport")
	public String myreport(HttpServletRequest request, HttpServletResponse response, Model model, Bug t) {
		int projectid = RequestUtil.toInt(request, "projectid");
		int userid = RequestUtil.sessionToInt(request, "userid");
		int page = RequestUtil.toInt(request, "page");
		int pageSize = 1566;
		t.setProjectid(projectid);
		t.setSentTo(userid);
		boolean au = menuauthDao.checkUserAuth(projectid, userid);
		if(!au)
			return "/views/manage/user/error";
		ListPage<Bug> list = bugDao.list(t, page, pageSize);
		if(list!=null && list.getItems()!=null){
			for(Bug b : list.getItems()){
				int uid = b.getSentTo();
				User u = userDao.findById(uid);
				b.setUser(u);
			}
			
		}
		list.setUrl(request);
		model.addAttribute("forwardUrl", RequestUtil.encodeUrl(request));
		model.addAttribute("list", list);
		model.addAttribute("t", t);
		return "/views/manage/bug/myreport";
	}
	@RequestMapping("/report")
	public String report(HttpServletRequest request, HttpServletResponse response, Model model, Bug t) {
		int projectid = RequestUtil.toInt(request, "projectid");
		int userid = RequestUtil.sessionToInt(request, "userid");
		int page = RequestUtil.toInt(request, "page");
		int pageSize = 1566;
		boolean au = menuauthDao.checkUserAuth(projectid, userid);
		if(!au)
			return "/views/manage/user/error";
		t.setProjectid(projectid);
		t.setSentTo(userid);
		ListPage<Bug> list = bugDao.list2(t, page, pageSize);
		if(list!=null && list.getItems()!=null){
			for(Bug b : list.getItems()){
				int uid = b.getSentTo();
				User u = userDao.findById(uid);
				b.setUser(u);
			}
			
		}
		list.setUrl(request);
		model.addAttribute("forwardUrl", RequestUtil.encodeUrl(request));
		model.addAttribute("list", list);
		model.addAttribute("t", t);
		return "/views/manage/bug/report";
	}
	
	
	@RequestMapping("edit")
	public String edit(HttpServletRequest request, HttpServletResponse response, Model model, Bug t,
			String forwardUrl) {
		int projectid = RequestUtil.toInt(request, "projectid");
		int userid = RequestUtil.sessionToInt(request, "userid");
		boolean au = menuauthDao.checkUserAuth(projectid, userid);
		if(!au)
			return "/views/manage/user/error";
		if (t.getId() != 0) {
			t = bugDao.findById(t.getId());
			if(t == null){
				t= new Bug();
			}
		} 
		List<User> list = userDao.listAll(projectid);
		
		model.addAttribute("t", t);
		model.addAttribute("list", list);
		model.addAttribute("forwardUrlBack", StringUtil.hex2str(forwardUrl));
		return "/views/manage/bug/bugedit";
	}
	
	@RequestMapping("updateBug")
	public String updateBug(HttpServletRequest request, HttpServletResponse response, Model model, Bug t,
			String forwardUrl) {
		bugDao.updateB(t);
		return "redirect:" + StringUtil.hex2str(forwardUrl);
	}
	
	@RequestMapping("bugInfo")
	public String bugInfo(HttpServletRequest request, HttpServletResponse response, Model model, Bug t,
			String forwardUrl) {
		int userid = RequestUtil.sessionToInt(request, "userid");
		User u = userDao.findById(userid);
		List<Menuauth> list = menuauthDao.list(userid);
		if(list!=null){
			for(Menuauth m : list){
				Projectment ms = projectmentDao.findById(m.getProjectid());
				if(ms!=null){
					m.setProjectname(ms.getTitle());
					int num = bugDao.countXf(userid,0,ms.getId());
					m.setNum(num);
				}
			}
		}
		model.addAttribute("list",list);
		int countxf = bugDao.countXf(userid,1,0);
		int wcountxf = bugDao.countXf(userid,0,0);
		model.addAttribute("countxf", countxf);
		model.addAttribute("wcountxf", wcountxf);
		return "/views/manage/user/userCenter";
	}
	
	@RequestMapping("bugCheck")
	public String bugCheck(HttpServletRequest request, HttpServletResponse response, Model model, Bug t,
			String forwardUrl) {
		if (t.getId() != 0) {
			t = bugDao.findById(t.getId());
			if(t==null){
				t= new Bug();
			}
		} 
		
		int uid = t.getSentTo();
		User u = userDao.findById(uid);
		if(u!=null){
			t.setSentToUser(u.getName());
		}
		model.addAttribute("t", t);
		model.addAttribute("forwardUrlBack", StringUtil.hex2str(forwardUrl));
		return "/views/manage/bug/bugCheck";
	}
	
	@RequestMapping("update")
	public String update(HttpServletRequest request, HttpServletResponse response, Model model, Bug t,
			String forwardUrlBack) {
		bugDao.updateB(t);
		return "redirect:" + forwardUrlBack;
	}
	
	@RequestMapping("save")
	public String save(HttpServletRequest request, HttpServletResponse response, Model model, Bug t,
			String forwardUrlBack) {
		int projectid = RequestUtil.toInt(request, "projectid");
		int userid = RequestUtil.sessionToInt(request, "userid");
		boolean au = menuauthDao.checkUserAuth(projectid, userid);
		if(!au)
			return "/views/manage/user/error";
		
		if(t.getId()>0){
			bugDao.updateBug(t);
		}else{
		bugDao.save(t);
		}
		return "redirect:" + forwardUrlBack;
	}
	
}
