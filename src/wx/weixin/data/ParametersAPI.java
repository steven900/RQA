package wx.weixin.data;

import java.util.Date;

import org.mortbay.jetty.security.Credential.MD5;

public class ParametersAPI{

	
	/** 微信配置参数**/
	
	/**
	 * 正式参数
	 */

	public static String TOKEN="my121token";
	public static String APPID="wx0cdbb36791e86803";
	public  static String APPSECRET="caa56ef09d29db20ea7c0445b52b6c40";
	
	
	//微信支付
	public  static String mch_id = "1261985801";
	public static String key = "jiaxingjiantaoshichang8266778800"; // 商户id
	public static String spbill_ip = "101.227.248.117";  //生成支付的ip地址
	public static String notify_url="/node.do"; //完成支付的通知地址
	public static String device_info="1001";
	
	public static String wxpayview="/views/wxpay/test";//test.ftl 微信支付参数页面
	
	
	/**  微信参数 **/
	public  static String ACCESS_TOKEN = "";
	public  static long ACCESS_TOKEN_TIME = new Date().getTime();
	public static String testopenid;
	
	
	/**
	 * 配置菜单目录 ftl
	 * 
	 */
	public static final String  VIEWS = "/views/weixin";
	
	public static final boolean wxstatus = true; //false 为测试阶段，上线后需要变成true
	public static final boolean log = false; //测试阶段 为true  上线后false；打印输出
	public static final boolean pay = true; //测试阶段 为false  上线后true所有支付都为1分钱；
	
	//企业支付证书地址
	//D:\programer\workspace8\121shopping\src\main\webapp\WEB-INF\classes
	public static final String certpath = "/usr/project/shopping/WEB-INF/classes/apiclient_cert.p12";
	//public static final String certpath = "D:/programer/workspace8/121shopping/src/main/webapp/WEB-INF/classes/apiclient_cert.p12";
	
	/**
	 * 微信端加密
	 * @param input
	 * @return
	 */
	public static String keycrp(String input){
		return MD5.digest(mch_id+input);
	}
	

}
