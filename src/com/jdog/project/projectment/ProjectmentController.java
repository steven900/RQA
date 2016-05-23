package com.jdog.project.projectment;

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

@Controller
@RequestMapping("/manage/projectment")
public class ProjectmentController {
	@Resource
	private ProjectmentDao projectmentDao;
	@Resource
	private MenuauthDao menuauthDao;
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response, Model model, Projectment t) {
		
		int page = RequestUtil.toInt(request, "page");
		int pageSize = 15;
		ListPage<Projectment> list = projectmentDao.list(t, page, pageSize);
		list.setUrl(request);
		model.addAttribute("forwardUrl", RequestUtil.encodeUrl(request));
		model.addAttribute("list", list);
		model.addAttribute("t", t);
		return "/views/manage/projectment/listprojectment";
	}
	
	
	@RequestMapping("/userAuthlist")
	public String userAuthlist(HttpServletRequest request, HttpServletResponse response, Model model, Projectment t) {
		
		int page = RequestUtil.toInt(request, "page");
		int userid = RequestUtil.toInt(request, "userid");
		int pageSize = 15;
		ListPage<Projectment> list = projectmentDao.list(t, page, pageSize);
		list.setUrl(request);
		if(list !=null && list.getItems()!=null){
			for(Projectment p : list.getItems()){
				int projectid = p.getId();
						
				Menuauth m = menuauthDao.findById(projectid,userid);
				p.setMenuauth(m);
			}
			
		}
		
		model.addAttribute("forwardUrl", RequestUtil.encodeUrl(request));
		model.addAttribute("list", list);
		model.addAttribute("t", t);
		model.addAttribute("userid",userid);
		return "/views/manage/user/userAuthlist";
	}
	
	@RequestMapping("edit")
	public String edit(HttpServletRequest request, HttpServletResponse response, Model model, Projectment t,
			String forwardUrl) {
		if (t.getId() != 0) {
			t = projectmentDao.findById(t.getId());
			if(t == null){
				t= new Projectment();
			}
		} 
		model.addAttribute("t", t);
		model.addAttribute("forwardUrlBack", StringUtil.hex2str(forwardUrl));
		return "/views/manage/projectment/editprojectment";
	}
	
	@RequestMapping("save")
	public String save(HttpServletRequest request, HttpServletResponse response, Model model, Projectment t,
			String forwardUrlBack) {
		if(t.getId()>0){
			projectmentDao.updateBug(t);
		}else{
			projectmentDao.save(t);
		}
		return "redirect:" + forwardUrlBack;
	}
	
	@RequestMapping("delete")
	public String delete(HttpServletRequest request, HttpServletResponse response, Model model, int id,
			String forwardUrl) {
		projectmentDao.delete(id);
		return "redirect:" + StringUtil.hex2str(forwardUrl);
	}
}

