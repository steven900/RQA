package wx.weixin.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jsoup.helper.StringUtil;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

import net.sf.json.JSONObject;
import wx.weixin.bean.Article;
import wx.weixin.bean.Message;
import wx.weixin.bean.message.BaseMessage;
import wx.weixin.bean.message.Image;
import wx.weixin.bean.message.ImageMessage;
import wx.weixin.bean.message.LinkMessage;
import wx.weixin.bean.message.MusicMessage;
import wx.weixin.bean.message.NewsMessage;
import wx.weixin.bean.message.TextMessage;
import wx.weixin.bean.message.Video;
import wx.weixin.bean.message.VideoMessage;
import wx.weixin.bean.message.VoiceMessage;
import wx.weixin.data.MessageTypeRef;
import wx.weixin.data.ParametersAPI;
import wx.weixin.data.UrlRef;

//weixin message util
public class MessageUtil {

	public static final String REQ_MESSAGE_TYPE_TEXT = "text";
	public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
	public static final String REQ_MESSAGE_TYPE_VOiCE = "voice";
	public static final String REQ_MESSAGE_TYPE_VIDEO = "video";
	public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
	
	  /** 
     * 请求消息类型：链接 
     */  
    public static final String REQ_MESSAGE_TYPE_LINK = "link";  
  
  
    /** 
     * 请求消息类型：音频 
     */  
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";  
  
    /** 
     * 请求消息类型：推送 
     */  
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";  
  
    /** 
     * 事件类型：subscribe(订阅) 
     */  
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";  
  
    /** 
     * 事件类型：unsubscribe(取消订阅) 
     */  
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";  
  
    /** 
     * 事件类型：CLICK(自定义菜单点击事件) 
     */  
    public static final String EVENT_TYPE_CLICK = "click";  
    /** 
     * 事件类型：扫二维码 
     */ 
	public static final String EVENT_TYPE_SCAN = "scan";

	/**
	 * 解析微信发送来的请求
	 */
	public static Map<String, String> parseXml(HttpServletRequest request) {
		InputStream inputStream = null;
		try {
			Map<String, String> map = new HashMap<String, String>();
			inputStream = request.getInputStream();
			SAXReader reader = new SAXReader();
			Document document = reader.read(inputStream);
			Element root = document.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> elementList = root.elements();
			for (Element e : elementList) {
				map.put(e.getName().toLowerCase(), e.getText());
			}

			inputStream.close();
			inputStream = null;
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				inputStream = null;
			}
		}
		return null;
	}

	public static Map<String, String> praseXml(String xml) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Document document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement();
			List<Element> elist = root.elements();

			for (Element e : elist) {
				map.put(e.getName().toLowerCase(), e.getText());
			}
			return map;
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		return null;
	}

	private static XStream xstream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				boolean cdata = true;

				public void startNode(String name,
						@SuppressWarnings("rawtypes") Class clazz) {
					super.startNode(name, clazz);
				}

				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});

	
	/**
	 * 发送文字信息
	 * @param from
	 * @param to
	 * @param content
	 * @return
	 */
	public static String sendTextMessage(String to, String from, String content) {
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(to);
		textMessage.setFromUserName(from);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(REQ_MESSAGE_TYPE_TEXT);
		textMessage.setContent(content);
		String resultStr = MessageUtil.messageToXml(textMessage);
		return resultStr;
	}
	
	/**
	 * 发送TextMessage
	 */
	public static void sendTextMessage(HttpServletResponse response,
			String from, String to, String content) {
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(to);
		textMessage.setFromUserName(from);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(REQ_MESSAGE_TYPE_TEXT);
		textMessage.setContent(content);
		String resultStr = MessageUtil.messageToXml(textMessage);
		try {
			response.getWriter().print(resultStr);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 发送图片消息
	 */
	public static void sendImageMessage(HttpServletResponse response, String from, String to, String mediaId) {
		ImageMessage i = new ImageMessage();
		i.setToUserName(to);
		i.setFromUserName(from);
		i.setCreateTime(new Date().getTime());
		i.setMsgType(MessageTypeRef.REQ_MESSAGE_TYPE_IMAGE);
		i.setImage(new Image(mediaId));
		String resultStr = MessageUtil.messageToXml(i);
		System.out.println("resultStr::" + resultStr);
		try {
			response.getWriter().print(resultStr);
			response.getWriter().flush();
			response.getWriter().close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 发送链接消息
	 */
	public static void sendLinkMessage(HttpServletResponse response, String from, String to, String url) {
		LinkMessage l = new LinkMessage();
		l.setToUserName(to);
		l.setFromUserName(from);
		l.setCreateTime(new Date().getTime());
		l.setMsgType(MessageTypeRef.REQ_MESSAGE_TYPE_LINK);
		l.setTitle("游戏充值");
		l.setUrl(url);
		String resultStr = MessageUtil.messageToXml(l);
		System.out.println("resultStr::" + resultStr);
		try {
			response.getWriter().print(resultStr);
			response.getWriter().flush();
			response.getWriter().close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 发送视频消息
	 */
	public static void sendVideoMessage(HttpServletResponse response, String from, String to, String mediaId, String title, String desc) {
		VideoMessage v = new VideoMessage();
		v.setToUserName(to);
		v.setFromUserName(from);
		v.setCreateTime(new Date().getTime());
		v.setMsgType(MessageTypeRef.REQ_MESSAGE_TYPE_VIDEO);
		v.setVideo(new Video(mediaId, title, desc));
		String resultStr = MessageUtil.messageToXml(v);
		try {
			response.getWriter().print(resultStr);
			response.getWriter().flush();
			response.getWriter().close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 发送文章信息
	 * @param from
	 * @param to
	 * @param articles
	 * @return
	 */
	 public static String sendNewsMessage(String from, String to, List<Article> articles) {
	 	NewsMessage newsMessage = new NewsMessage();
    	newsMessage.setToUserName(to);
    	newsMessage.setFromUserName(from);
    	newsMessage.setCreateTime(new Date().getTime());
    	newsMessage.setArticleCount(articles.size());
    	newsMessage.setArticles(articles);
    	newsMessage.setMsgType(MessageTypeRef.RESP_MESSAGE_TYPE_NEWS);
    	String resultStr = MessageUtil.messageToXml(newsMessage);
    	return resultStr;
	 } 
		 
	
	 /**
     * 发送图文消息列表
     */
    public static void sendNewsMessage(HttpServletResponse response, String from, String to, List<Article> articles) {
    	NewsMessage newsMessage = new NewsMessage();
    	newsMessage.setToUserName(to);
    	newsMessage.setFromUserName(from);
    	newsMessage.setCreateTime(new Date().getTime());
    	newsMessage.setArticleCount(articles.size());
    	newsMessage.setArticles(articles);
    	newsMessage.setMsgType(MessageTypeRef.RESP_MESSAGE_TYPE_NEWS);
    	String resultStr = MessageUtil.messageToXml(newsMessage);
		try {
			response.getWriter().print(resultStr);
			response.getWriter().flush();
			response.getWriter().close();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    public static String messageToXml(TextMessage textMessage) {
    	xstream.alias("xml", textMessage.getClass());
    	return xstream.toXML(textMessage);
    }
    
    public static String messageToXml(ImageMessage imageMessage) {
    	xstream.alias("xml", imageMessage.getClass());
    	return xstream.toXML(imageMessage);
    }
    
    public static String messageToXml(VoiceMessage voiceMessage) {
    	xstream.alias("xml", voiceMessage.getClass());
    	return xstream.toXML(voiceMessage);
    }
    
    public static String messageToXml(VideoMessage videoMessage) {
    	xstream.alias("xml", videoMessage.getClass());
    	return xstream.toXML(videoMessage);
    }
    
    public static String messageToXml(MusicMessage musicMessage) {
    	xstream.alias("xml", musicMessage.getClass());
    	return xstream.toXML(musicMessage);
    }
    
    public static String messageToXml(LinkMessage linkMessage) {
    	xstream.alias("xml", linkMessage.getClass());
    	return xstream.toXML(linkMessage);
    }
    
    public static String messageToXml(NewsMessage newsMessage) {
    	xstream.alias("xml", newsMessage.getClass());
    	xstream.alias("item", new Article().getClass());
    	return xstream.toXML(newsMessage);
    }
    
    public static BaseMessage xmlToMessage(String xml) {
    	return (BaseMessage) xstream.fromXML(xml);
    }
	

    /**
	 * 获取微信传递信息
	 * @param xml
	 * @return
	 */
	public static Message getTransData(String xml){
		
		Message u = new Message();
		Map<String, String> dataMap = MessageUtil.praseXml(xml);
		u.setFromUserName( dataMap.get("fromusername"));// 开发者微信帐号
		u.setToUserName(dataMap.get("tousername"));// 添加帐号
		u.setCreateTime (dataMap.get("createtime"));// 消息id
		u.setMsgType(dataMap.get("msgtype"));
		u.setMsgId(dataMap.get("msgid"));
		u.setContent(dataMap.get("content"));
		u.setEventKey(dataMap.get("eventkey"));
		u.setEventType(dataMap.get("event"));
		return u;
	}
    
	
	public static String articleMessageToXml(Message msg,List<Article> arts){
		NewsMessage nsmsg = new NewsMessage();
		nsmsg.setFromUserName(msg.getToUserName());
		nsmsg.setToUserName(msg.getFromUserName());
		nsmsg.setCreateTime(new Date().getTime());
		nsmsg.setMsgId(msg.getMsgId());
		nsmsg.setMsgType("news");
		nsmsg.setArticleCount(arts.size());
		nsmsg.setArticles(arts);
		return messageToXml(nsmsg);
	}
	
	public static String textMessageToXml(Message msg,String content) {
		TextMessage txtmsg = new TextMessage();
		txtmsg.setFromUserName(msg.getToUserName());
		txtmsg.setToUserName(msg.getFromUserName());
		txtmsg.setMsgType("text");
		txtmsg.setContent(content);
		txtmsg.setCreateTime(new Date().getTime());
		return MessageUtil.messageToXml(txtmsg);
	}
	
	/**
	 * 初次订阅
	 * @param msg
	 * @param headUrl
	 * @param articleUrl
	 * @param content
	 * @return
	 */
	public static String subscribe(Message msg,String picUrl,String articleUrl,String content){
		
			NewsMessage nsmsg = new NewsMessage();
			nsmsg.setFromUserName(msg.getToUserName());
			nsmsg.setToUserName(msg.getFromUserName());
			nsmsg.setCreateTime(new Date().getTime());
			nsmsg.setMsgId(msg.getMsgId());
			nsmsg.setMsgType("news");
			nsmsg.setArticleCount( 1);
			List<Article> articles = new ArrayList<Article>();
			Article article = new Article();
			article.setTitle("");
			article.setPicUrl(picUrl);
			article.setUrl(articleUrl);
			article.setDescription(content);
			articles.add(article);
			nsmsg.setArticles(articles);
			return messageToXml(nsmsg);
	}
	
	/**
	 * 客服消息发送，文字模块
	 * 当用户主动发消息给公众号的时候（包括发送信息、点击自定义菜单、订阅事件、扫描二维码事件、
	 * 支付成功事件、用户维权），微信将会把消息数据推送给开发者，开发者在一段时间内（目前修改为48小时）
	 * 可以调用客服消息接口，通过POST一个JSON数据包来发送消息给普通用户，在48小时内不限制发送次数。
	 * 此接口主要用于客服等有人工消息处理环节的功能，方便开发者为用户提供更加优质的服务。 
	 */
	public static void sendMessagePro(String content,String to){
		String access_token=WeixinUtil.getAccessTokenFromServers();
		//accessToken = "NvTR29Olq8Bhqz7WAyn1jZ9zToz1PUBnPTom_9YkQY_k6G279NO5nosBnBrIwTmMrRYIpAE9QXajX-O2RbOCHJa0kHI9Mgeo-MLrKmC6UJg";
		//https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN
		String requestUrl = WeixinUtil.getWeixinUrl(UrlRef.CUSTOM_SEND, access_token);
		String msg="{\"touser\":\""+to+"\",\"msgtype\":\"text\",\"text\":{ \"content\":\""+content+"\"}}";
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", msg);
		if (null != jsonObject) {
			System.out.println(jsonObject);
		}
	}
	
	/**
	 * 在关注者与公众号产生消息交互后，公众号可获得关注者的OpenID（加密后的微信号，每个用户对每个公众号的OpenID是唯一的。
	 * 对于不同公众号，同一用户的openid不同）。
	 * 公众号可通过本接口来根据OpenID获取用户基本信息，包括昵称、头像、性别、所在城市、语言和关注时间。
	 * 请注意，如果开发者有在多个公众号，或在公众号、移动应用之间统一用户帐号的需求，
	 * 需要前往微信开放平台（open.weixin.qq.com）绑定公众号后，才可利用UnionID机制来满足上述需求。 
	 * 
	 * {sex=1, subscribe=1, remark=, nickname=cv, province=昆士兰, openid=oA7hUsziR4xK0pY-M7Pf2JpTkGyk, groupid=0, language=zh_CN, headimgurl=http://wx.qlogo.cn/mmopen/ZOsziauJCibkd3oXZlaW3o18Y4lyYiagb9IcPTZU0zKnU3ia3icpaXLnmbkSdudN2m8oAticHYbLOOmfsc7Q2UYJ6loUPMAibcYtLIQ/0, subscribe_time=1438137629, country=澳大利亚, city=凯恩斯}
	 */
	public static Map<String, Object> getUserInfo(String openid){
		
		String access_token=WeixinUtil.getAccessTokenFromServers();
		//https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
		String requestUrl = WeixinUtil.getWeixinUrl(UrlRef.USER_INFO, access_token);
		requestUrl=requestUrl.replace("OPENID", openid);
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", null);
		if (null != jsonObject) {
			Map<String, Object> resultMap = new HashMap<String, Object>();  
	        Iterator<String> iter = jsonObject.keys();  
	        String key=null;  
	        Object value=null;  
	        while (iter.hasNext()) {  
	            key=iter.next();  
	            value=jsonObject.get(key);  
	            resultMap.put(key, value);  
	        }  
	        
			 
			 String nickname = (String) resultMap.get("nickname");
			 if(!StringUtil.isBlank(nickname)){
			//	 nickname =  EmojiFilter.filterEmoji(nickname);
				 resultMap.put("nickname", nickname);
			 }
	         return resultMap;
		}
		return null;
	}
	
	/**
	 * 根据code获取信息
	 */
	public static String loadWeiXinSessions(HttpServletRequest request
			) {

		String access_token = WeixinUtil.getPrasedAccessToken();
		String code = request.getParameter("code");
		if(code ==null)
			return null;
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		requestUrl=requestUrl.replace("ACCESS_TOKEN",access_token);
		requestUrl=requestUrl.replace("CODE", code);
		requestUrl=requestUrl.replace("APPID", ParametersAPI.APPID);
		requestUrl=requestUrl.replace("SECRET", ParametersAPI.APPSECRET);
		JSONObject jsonObject=CommonUtil.getAskJSONObject(requestUrl);
	
		System.out.println(jsonObject.toString()+"_____________________0"+code);
		System.out.println("--------------------------");
		if(jsonObject!=null && !jsonObject.equals("")){
			String openid = jsonObject.getString("openid");
			return openid;
		}
		return null;
	}
	
	public static void main(String[] args) {
		
		 Map<String, Object> ret = getUserInfo("ofT_Pv0iUfrU_QAovTQwi1M1vZRk");
		 for (String key : ret.keySet()) {
	           System.out.println(key + "___" + ret.get(key));
	     }
		 
		 
	}
}
