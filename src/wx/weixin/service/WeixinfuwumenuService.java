package wx.weixin.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jdog.frameworks.db.ListPage;

import wx.weixin.bean.Weixinfuwumenu;
import wx.weixin.dao.WeixinfuwumenuDao;

@Service
public class WeixinfuwumenuService {
	@Resource
	private WeixinfuwumenuDao weixinfuwumenuDao;

	public ListPage<Weixinfuwumenu> list(Weixinfuwumenu weixinfuwumenu,
			int page, int pageSize) {
		return weixinfuwumenuDao.list(weixinfuwumenu, page, pageSize);
	}

	public List<Weixinfuwumenu> list(Weixinfuwumenu weixinfuwumenu) {
		return weixinfuwumenuDao.list(weixinfuwumenu);
	}

	public List<Weixinfuwumenu>  menulist() {
		Weixinfuwumenu w = new Weixinfuwumenu();
		w.setPid(0);
		List<Weixinfuwumenu> list = weixinfuwumenuDao.list(w);
		if(list ==null){
			list = new ArrayList<Weixinfuwumenu>();
		}
		for(Weixinfuwumenu wm :list){
			int pid = wm.getId();
			Weixinfuwumenu data = new Weixinfuwumenu();
			data.setPid(pid);
			List<Weixinfuwumenu> mlist = weixinfuwumenuDao.list(data);
			wm.setList(mlist);
		}
		return list;
	}
	
	public void update(Weixinfuwumenu weixinfuwumenu) {
		weixinfuwumenuDao.updateByPriKey(weixinfuwumenu);
	}

	public void delete(Weixinfuwumenu weixinfuwumenu) {
		weixinfuwumenuDao.deleteById(weixinfuwumenu);
	}

	public void save(Weixinfuwumenu weixinfuwumenu) {
		weixinfuwumenuDao.save(weixinfuwumenu);
	}

	public void updateByFields(Weixinfuwumenu condition, Weixinfuwumenu where) {
		weixinfuwumenuDao.updateByFields(condition, where);
	}

	public Weixinfuwumenu findByFields(Weixinfuwumenu weixinfuwumenu) {
		if (weixinfuwumenu == null)
			weixinfuwumenu = new Weixinfuwumenu();
		return weixinfuwumenuDao.findByFields(weixinfuwumenu);
	}

	public Weixinfuwumenu findByFieldsNullReturn(Weixinfuwumenu weixinfuwumenu) {
		return weixinfuwumenuDao.findByFields(weixinfuwumenu);
	}

	public Weixinfuwumenu findById(Integer id) {
		Weixinfuwumenu weixinfuwumenu = weixinfuwumenuDao.findById(id);
		if (weixinfuwumenu == null)
			weixinfuwumenu = new Weixinfuwumenu();
		return weixinfuwumenu;
	}

	public Weixinfuwumenu findByIdNullReturn(Integer id) {
		Weixinfuwumenu weixinfuwumenu = weixinfuwumenuDao.findById(id);
		return weixinfuwumenu;
	}
}
