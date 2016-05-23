package wx.weixin.util;

import wx.weixin.bean.Buttonw;
import wx.weixin.bean.ComplexButton;
import wx.weixin.bean.Menu;
import wx.weixin.bean.ViewButton;

public class MenuCreateUtil {

	public static String DOMAIN="http://wxdevelop.6655.la";
	
	
	/*
	 * 
	 * 
	 * 
	 * 		ComplexButton bt1 = new ComplexButton("产品中心", new Buttonw[] {
					new ViewButton("中央空调", "view", DOMAIN+"/listwx.do?cid=1"),
					new ViewButton("家庭采暖", "view", DOMAIN+"/listwx.do?cid=2"),
					new ViewButton("新风系统", "view", DOMAIN+"/listwx.do?cid=3"),
					new ViewButton("中央净水", "view", DOMAIN+"/listwx.do?cid=4"),
					new ViewButton("网上商城", "view", DOMAIN+"/indexwx.do") });
			ComplexButton bt2 = new ComplexButton("贴心服务", new Buttonw[] {
					new ClickButton("每日推荐", "click", "tj"),
					new ClickButton("咨询服务", "click", "zx")  });
			ComplexButton bt3 = new ComplexButton("最新优惠", new Buttonw[] {
					new ClickButton("优惠券", "click", "youhui"),
					new ClickButton("我的优惠","click", "myyouhui"),
					new ViewButton("优惠信息", "view",DOMAIN+"/views/sucai.ftl"),
					new ViewButton("关于我们", "view",DOMAIN+"/aboutUs.do") });
	 */
	public static void main(String[] args) {
		
			ComplexButton bt3 = new ComplexButton("互动城南", new Buttonw[] {
					new ViewButton("投票页面", "view", "http://card.orz-tech.com/xitie/12.png"),
					new ViewButton("新风详细", "view", "http://card.orz-tech.com/xitie/11.png"),
					new ViewButton("新闻列表页", "view", "http://139.196.187.76/images/33.PNG")
					 });
			ComplexButton bt2 = new ComplexButton("服务城南", new Buttonw[] {
					new ViewButton("服务城南", "view", "http://card.orz-tech.com/xitie/11.png"),
					new ViewButton("服务社区", "view", "http://card.orz-tech.com/xitie/11.png"),
					new ViewButton("服务党员", "view", "http://card.orz-tech.com/xitie/11.png"),
					new ViewButton("服务城南", "view", "http://card.orz-tech.com/xitie/11.png")
			});
			
			ComplexButton bt1 = new ComplexButton("魅力城南", new Buttonw[] {
					new ViewButton("红枫苑社区", "view", "http://card.orz-tech.com/xitie/11.png"),
					new ViewButton("城南试验区", "view", "http://card.orz-tech.com/xitie/11.png")
			});
		
			Menu menu = new Menu();
			menu.setButton(new Buttonw[] { bt1, bt2,bt3 });
			String result = WeixinUtil.MenuCreate(menu);
			System.out.println(result);
		}
		

}
