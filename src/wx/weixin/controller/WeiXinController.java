package wx.weixin.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jdog.frameworks.encode.MD5;

import wx.weixin.bean.Message;
import wx.weixin.bean.Signature;
import wx.weixin.data.MessageTypeRef;
import wx.weixin.data.ParametersAPI;
import wx.weixin.service.BussinessCoreService;
import wx.weixin.util.MessageUtil;
import wx.weixin.util.WeixinUtil;

/**
 * 
 * @author steven
 * 
 */
@Controller
public class WeiXinController {


	@Resource
	private BussinessCoreService bussinessCoreService;

	/**
	 * 消息真实性校验
	 * 
	 * @param signature
	 * @return
	 */
	@RequestMapping(value = "api.do", method = RequestMethod.GET)
	public void Signature(Signature signature, Writer writer) {

		if (WeixinUtil.ChackSignature(signature)) {
			System.out.println(signature.getEchostr());
			try {
				writer.write(signature.getEchostr());
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	@RequestMapping(value = "api.do", method = RequestMethod.POST)
	@ResponseBody
	public Object api(@RequestBody String xml, Writer writer,
			HttpServletRequest request, HttpServletResponse response) {

		Message requestData = processRequest(xml);
System.out.println(xml);
		String resultStr = this.msgTransport(requestData);
			System.out.println("result:" + resultStr);
		try {
			writer.write(resultStr);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String msgTransport(Message msg){
		int transMsgType = msg.getMsgrel();
		String user= msg.getFromUserName();
		String server = msg.getToUserName();
			
		if(transMsgType!=1 && msg.getEventKey().toLowerCase().indexOf("qrscene")!=-1){
			String event = msg.getEventKey();
			String key = event.replace("qrscene_", "");
			return bussinessCoreService.transprotScan(key,server,user);
		}
		
		if(transMsgType==1){
			String txt = msg.getContent();
			String msgz = bussinessCoreService.transprotText(txt,server,user);
			return msgz;
		}
		
		/**
		 * 
		 * 
			<xml><ToUserName><![CDATA[gh_528498c585f2]]></ToUserName>
			<FromUserName><![CDATA[ofT_Pv6-y4KsEwTBN4Dj8yvtBdrk]]></FromUserName>
			<CreateTime>1444744551</CreateTime>
			<MsgType><![CDATA[event]]></MsgType>
			<Event><![CDATA[subscribe]]></Event>
			<EventKey><![CDATA[qrscene_239]]></EventKey>
			<Ticket><![CDATA[gQFN8DoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xLzNrVHg3UWZsZGtnUG8xdTY1V2k1AAIEBAodVgMEAAAAAA==]]></Ticket>
			</xml>
		 * 
		 * 
		 */
		if(transMsgType==7){
				
			return bussinessCoreService.transprotSubmit(msg,server,user);
		}
		
		if(transMsgType ==8){
			String event = msg.getEventKey();
			return bussinessCoreService.transprotMenu(event,server,user);
		}
		
		/**
		 * 
		 * 
			<xml><ToUserName><![CDATA[gh_528498c585f2]]></ToUserName>
			<FromUserName><![CDATA[ofT_Pv6-y4KsEwTBN4Dj8yvtBdrk]]></FromUserName>
			<CreateTime>1444744551</CreateTime>
			<MsgType><![CDATA[event]]></MsgType>
			<Event><![CDATA[subscribe]]></Event>
			<EventKey><![CDATA[qrscene_239]]></EventKey>
			<Ticket><![CDATA[gQFN8DoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xLzNrVHg3UWZsZGtnUG8xdTY1V2k1AAIEBAodVgMEAAAAAA==]]></Ticket>
			</xml>
		 * 
		 * 
		 */
		if(transMsgType ==9 || msg.getEventType().equals("SCAN")){
			String event = msg.getEventKey();
			return bussinessCoreService.transprotScan(event,server,user);
		}
		
		if(msg.getEventType().toLowerCase().indexOf("qrscene")!=-1){
			String event = msg.getEventType();
			String key = event.replace("qrscene_", "");
			return bussinessCoreService.transprotScan(key,server,user);
		}
		return null;
	}
	
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return xml
	 */
	public Message processRequest(String xml) {
		// xml格式的消息数据
		int transMsgType = 0;
		// 调用parseXml方法解析请求消息
		Message requestData = null;
		try {
			requestData = MessageUtil.getTransData(xml);

			String msgType = requestData.getMsgType();
			if (msgType != null)
				msgType = msgType.toLowerCase();
			// 文本消息
			if (msgType.equals(MessageTypeRef.REQ_MESSAGE_TYPE_TEXT)) {
				// respContent = "您发送的是文本消息！";
				transMsgType = 1;
			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				// respContent = "您发送的是图片消息！";
				transMsgType = 2;
			}
			// 语音消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOiCE)) {
				// respContent = "您发送的是语音消息！";
				transMsgType = 3;
			}
			// 视频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
				// respContent = "您发送的是视频消息！";
				transMsgType = 4;
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				// respContent = "您发送的是地理位置消息！";
				transMsgType = 5;
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				// respContent = "您发送的是链接消息！";
				transMsgType = 6;
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestData.getEventType();
				
				if(eventType ==null)
					eventType = "";
				
				if (eventType != null) {
					eventType = eventType.toLowerCase();
					// 关注
					if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
						// respContent = "谢谢您的关注！";
						transMsgType = 7;
					}
					// 取消关注
					else if (eventType
							.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
						// TODO 取消订阅后用户不会再收到公众账号发送的消息，因此不需要回复
					}
					// 扫描带参数二维码
					else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
						// TODO 处理扫描带参数二维码事件
					}
					// 上报地理位置
					else if (eventType
							.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
						// TODO 处理上报地理位置事件
					}
					
					else if (eventType.toLowerCase().equals(MessageUtil.EVENT_TYPE_SCAN.toLowerCase())) {
						transMsgType = 9;
					}
					// 自定义菜单
					else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
						// TODO 处理菜单点击事件
						// 事件KEY值，与创建自定义菜单时指定的KEY值对应
						String eventKey = requestData.getEventKey();
						if (ParametersAPI.log)
							System.out.println("eventKey:" + eventKey);
						if (eventKey != null) {
							transMsgType = 8;
						}
						// respContent = "点击的菜单KEY:"+eventKey;
					}
				}
				if (ParametersAPI.log)
					System.out.println("eventType:" + eventType);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		requestData.setMsgrel(transMsgType);
		return requestData;
	}
	
	/**
	 * 微信企业向用户付款
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/wxpay/enterprisepay")
	public String enterprisepay(HttpServletRequest request,Model model){
		String url =  bussinessCoreService.enterprisepay(request,model);
		return url;
	}
	
	/**
	 * 微信企业向用户付款Ajax
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/wxpay/enterprisepayAjax")
	public String enterprisepayAjax(HttpServletRequest request,Model model){
		String data =  bussinessCoreService.enterprisepay(request,model);
		return data;
	}
	
	/**
	 * 微信支付
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/wxpay/pay")
	public String pay(HttpServletRequest request,Model model){
		Map<String,String> ret = bussinessCoreService.wxpay(request);
		model.addAttribute("ret",ret);
		model.addAttribute("appid",ParametersAPI.APPID);
		return ParametersAPI.wxpayview;
	}
	
	
	/**
	 * 微信支付完成回调地址
	 * 
	 * @param request
	 * @param model
	 * @param xml
	 * @param writer
	 * @return
	 */
	@RequestMapping(value = "node", method = RequestMethod.POST)
	@ResponseBody
	public String node(HttpServletRequest request, Model model,
			@RequestBody String xml, Writer writer) {

		Map<String, String> map = MessageUtil.praseXml(xml);
		String appId = map.get("appid");
		String mch_id = map.get("mch_id");
		String out_trade_no = map.get("out_trade_no");
		String result_code = map.get("result_code");
		// 加密信息 与数据库比对
		String total_fee = map.get("total_fee");
		String keycrp = MD5.digest(mch_id + out_trade_no);

		if (result_code != null && result_code.toLowerCase().equals("success")
				&& appId != null && appId.equals(ParametersAPI.APPID)
				&& mch_id != null && mch_id.equals(ParametersAPI.mch_id)) {

			/**
			 * 完成数据库更新
			 */
			boolean flag = bussinessCoreService.wxpayNotify(total_fee, keycrp);
			if (flag) {
				try {
					String xmlpay = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
					writer.write(xmlpay);
					writer.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

}
