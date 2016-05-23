package com.jdog.project.user;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdog.frameworks.db.ListPage;
import com.jdog.frameworks.util.RequestUtil;
import com.jdog.frameworks.util.StringUtil;

@Controller
@RequestMapping("/manage/user")
public class ManageUserController {

	@Resource
	private UserService userService;
	
	/**
	 * 用户列表
	 * @param request
	 * @param response
	 * @param model
	 * @param t
	 * @return
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response, Model model, User t) {

		int page = RequestUtil.toInt(request, "page");
		int pageSize = 15;
		ListPage<User> list = userService.list(t, page, pageSize);
		list.setUrl(request);
		model.addAttribute("forwardUrl", RequestUtil.encodeUrl(request));
		model.addAttribute("list", list);
		model.addAttribute("t", t);
		return "/views/manage/user/listUser";
	}
	
	@RequestMapping("/edit")
	public String edit(HttpServletRequest request, HttpServletResponse response, Model model, User t,String forwardUrl) {
		
		if(t.getId()>0 || t.getId()==-1){
			User u = userService.findById(t.getId());
			if(u==null){
				model.addAttribute("t",new User());
			}else{
				model.addAttribute("t",u);
			}
		}else{
			model.addAttribute("t",t);
		}
		model.addAttribute("forwardUrlBack", StringUtil.hex2str(forwardUrl));
		return "/views/manage/user/useredit";
	}
	
	@RequestMapping("save")
	public String save(HttpServletRequest request, HttpServletResponse response, Model model, User t,
			String forwardUrlBack) {
		if(t.getId()>0){
			userService.updateB(t);
		}else{
			userService.save(t);
		}
		
		return "redirect:" + forwardUrlBack;
	}
	
	@RequestMapping("delete")
	public String delete(HttpServletRequest request, HttpServletResponse response, Model model, int id,
			String forwardUrl) {
		userService.delete(id);
		return "redirect:" + StringUtil.hex2str(forwardUrl);
	}
	
}

