package wx.weixin.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import wx.weixin.bean.Article;
import wx.weixin.bean.Message;

public interface BussinessCoreService {

	/**
	 * 微信与项目的中间过程
	 * @param requestData
	 * @param wxuser 
	 * @param wxserver 
	 * @return
	 * 
	 * msgrel
	 * 1  // respContent = "您发送的是文本消息！";
	   2  //respContent = "您发送的是图片消息！";
	 * 7 //respContent = "谢谢您的关注！";
	 * 8 // respContent = "点击的菜单KEY:
	 * wxserver 微信服务器id
	 * wxuser  微信用户
	 */
	//public String coreBussiness(Message requestData, String wxserver, String wxuser);
	
	/**
	 * 获取发送微信文本信息给 返回为加密后的信息
	 * @param to
	 * @param from
	 * @param content
	 * @return
	 */
	public String sendTextMsgToWX(String to,String server,String content);
	
	/**
	 * 获取发送微信文章信息， 返回加密后的信息
	 * @param from
	 * @param to
	 * @param articles
	 * @return
	 */
	public String sendNewsMessage(String to,String server,List<Article> articles);

	/**
	 * 微信支付回调数据验证
	 * @param total_fee //费用
	 * @param keycrp  //支付时的加密信息
	 */
	public boolean wxpayNotify(String total_fee, String keycrp);

	/**
	 * 微信支付方法  WxpayMain.wxpay() 支付时调用该方法，参数返回该接口
	 * @param request
	 * @return
	 */
	public Map<String, String> wxpay(HttpServletRequest request);
	
	/**
	 * 获取微信开发者信息
	 */
	Map<String, Object> getUserInfo(HttpServletRequest request);

	/**
	 * 文本消息接受
	 * @param user 
	 * @param server 
	 * @param msg 
	 * @return
	 */
	public String transprotText(String txt, String server, String user);

	/**
	 * 消息关注
	 * @param user 
	 * @param server 
	 * @param msg 
	 * @return
	 */
	public String transprotSubmit(Message msg, String server, String user);

	/**
	 * 菜单事件
	 * @param user 
	 * @param server 
	 * @param event 
	 * @return
	 */
	public String transprotMenu(String event, String server, String user);

	/**
	 * 微信企业向用户支付
	 * @param request
	 * @param model
	 * @return
	 */
	public String enterprisepay(HttpServletRequest request, Model model);
	
	
	/**
	 * Enterprise
	 * 支付回调地址
	 * 微信企业支付数据验证
	 * @param ret
	 * @return
	 * 
	 * 
		<xml>
			<return_code><![CDATA[SUCCESS]]></return_code>
			<return_msg><![CDATA[]]></return_msg>
			<mch_appid><![CDATA[wxec38b8ff840bd989]]></mch_appid>
			<mchid><![CDATA[10013274]]></mchid>
			<device_info><![CDATA[]]></device_info>
			<nonce_str><![CDATA[lxuDzMnRjpcXzxLx0q]]></nonce_str>
			<result_code><![CDATA[SUCCESS]]></result_code>
			<partner_trade_no><![CDATA[10013574201505191526582441]]></partner_trade_no>
			<payment_no><![CDATA[1000018301201505190181489473]]></payment_no>
			<payment_time><![CDATA[2015-05-19 15：26：59]]></payment_time>
		</xml>
	 * 
	 */
	public String enterprisepayAuth(Map<String,String> ret);

	public String transprotScan(String event, String server, String user);
	
}
