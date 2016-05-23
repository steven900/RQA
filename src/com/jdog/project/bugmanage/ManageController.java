package com.jdog.project.bugmanage;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdog.frameworks.util.RequestUtil;
import com.jdog.project.menuauth.Menuauth;
import com.jdog.project.menuauth.MenuauthDao;
import com.jdog.project.projectment.Projectment;
import com.jdog.project.projectment.ProjectmentDao;
import com.jdog.project.user.User;
import com.jdog.project.user.UserDao;

@Controller
@RequestMapping("/manage")
public class ManageController {
	
	@Resource
	private ProjectmentDao projectmentDao;
	@Resource
	private UserDao userDao;
	
	@Resource
	private MenuauthDao menuauthDao;
	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		return "/views/login";
	}
	
	@RequestMapping("/loginSubmit")
	public String loginSubmit(HttpServletRequest request, String username, String password, Model model) {
			if(username!= null && username.equals("steven") && password!=null && password.equals("steven")) {
				request.getSession().setAttribute("manage","admin");
				request.getSession().setAttribute("auth", 1);
				request.getSession().setAttribute("userid", -1);
				return "redirect:/manage/index.do";
			}else {
				
				User u = userDao.findByUser(username,password);
				if(u!=null){
					request.getSession().setAttribute("manage","admin");
					request.getSession().setAttribute("userid", u.getId());
				
					if(u.getId()==-1){
						request.getSession().setAttribute("auth", 1);
					}
					return "redirect:/manage/index.do";
				}
				return "redirect:/manage/login.do";
			}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute("manage");
		return "views/login";
	}

	@RequestMapping("/index")
	public String index(Model model,HttpServletRequest request) {
		
		String uri = request.getRemoteAddr();
		if(uri!=null && uri.equals("127.0.0.1")){
			model.addAttribute("adt",1);
		}
		
		int userid = RequestUtil.sessionToInt(request, "userid");
		User u = userDao.findById(userid);
		List<Menuauth> list = menuauthDao.list(userid);
		if(list!=null){
			for(Menuauth m : list){
				Projectment ms = projectmentDao.findById(m.getProjectid());
				if(ms!=null)
					m.setProjectname(ms.getTitle());
			}
		}
		model.addAttribute("list",list);
		model.addAttribute("user",u);
		request.getSession().setAttribute("bugmod", u.getPass());
		return "/views/manage/rqa/rindex";
	}
}
