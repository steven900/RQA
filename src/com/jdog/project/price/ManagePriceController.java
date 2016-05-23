package com.jdog.project.price;

import java.util.List;

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
import com.jdog.project.pricereport.Pricereport;
import com.jdog.project.pricereport.PricereportDao;

/**
 * 报价管理系统
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/manage/price")
public class ManagePriceController {

	@Resource
	private PriceDao priceDao;

	@Resource
	private PricereportDao pricereportDao;

	@RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response, Model model, Price t) {
		int page = RequestUtil.toInt(request, "page");
		int pageSize = 1566;
		ListPage<Price> list = priceDao.list(t, page, pageSize);

		list.setUrl(request);
		model.addAttribute("forwardUrl", RequestUtil.encodeUrl(request));
		model.addAttribute("list", list);
		model.addAttribute("t", t);
		return "/views/manage/price/pricelist";
	}

	@RequestMapping("/pricereport")
	public String pricereport(HttpServletRequest request, HttpServletResponse response, Model model, Price t) {
		int projectid = RequestUtil.toInt(request, "projectid");
		List<Pricereport> list = pricereportDao.listReport(projectid);
		int money = 0;
		int sumday = 0;
		if (list != null) {
			for (Pricereport p : list) {
				int priceid = p.getPriceid();
				Price price = priceDao.getById(priceid);
				if (price.getAvailable() == 1) {
					p.setPrice(price);
					money = money + price.getPrice();
					sumday = sumday + price.getDays();
				}
			}
		}

		model.addAttribute("moneyall", money);
		model.addAttribute("sumday", sumday);
		model.addAttribute("list", list);
		return "/views/manage/price/pricereport";
	}

	@RequestMapping("/pricelist")
	public String pricelist(HttpServletRequest request, HttpServletResponse response, Model model, Price t) {
		int page = RequestUtil.toInt(request, "page");
		int pageSize = 1566;

		int projectid = RequestUtil.toInt(request, "projectid");
		ListPage<Price> list = priceDao.list(t, page, pageSize);
		int money = 0;
		int sumday = 0;
		if (list != null && list.getItems() != null) {
			for (Price p : list.getItems()) {
				Pricereport pr = pricereportDao.findBy(projectid, p.getId());
				if (pr != null) {
					p.setChecked(1);
					money = money + p.getPrice();
					sumday = sumday + p.getDays();
				}
			}
		}

		list.setUrl(request);
		model.addAttribute("forwardUrl", RequestUtil.encodeUrl(request));
		model.addAttribute("list", list);
		model.addAttribute("t", t);
		model.addAttribute("moneyall", money);
		model.addAttribute("sumday", sumday);
		model.addAttribute("projectid", projectid);
		return "/views/manage/price/pricelistr";
	}

	@RequestMapping(value = "/ajax", produces = "text/plain;charset=utf8")
	@ResponseBody
	public String reportsave(HttpServletRequest request, HttpServletResponse response, Model model, Price t) {

		int projectid = RequestUtil.toInt(request, "projectid");
		int priceid = RequestUtil.toInt(request, "priceid");
		int pricemark = RequestUtil.toInt(request, "pricemark");
		if (pricemark == 0) {
			pricereportDao.delete(projectid, priceid);
		} else {
			pricereportDao.save(projectid, priceid);
		}

		return "";
	}

	@RequestMapping("edit")
	public String edit(HttpServletRequest request, HttpServletResponse response, Model model, Price t,
			String forwardUrl) {

		if (t.getId()!=null && t.getId() != 0) {
			t = priceDao.getById(t.getId());
			if (t == null) {
				t = new Price();
			}
		}
		model.addAttribute("t", t);
		model.addAttribute("forwardUrlBack", StringUtil.hex2str(forwardUrl));
		return "/views/manage/price/priceedit";
	}

	@RequestMapping("save")
	public String save(HttpServletRequest request, HttpServletResponse response, Model model, Price t,
			String forwardUrlBack) {
		if (t.getId() != null && t.getId() > 0) {
			priceDao.updatePrice(t);
		} else {
			priceDao.save(t);
		}
		return "redirect:" + forwardUrlBack;
	}

	@RequestMapping("delete")
	public String delete(HttpServletRequest request, HttpServletResponse response, Model model, int id,
			String forwardUrl) {
		priceDao.delete(id);
		return "redirect:" + StringUtil.hex2str(forwardUrl);
	}

}
