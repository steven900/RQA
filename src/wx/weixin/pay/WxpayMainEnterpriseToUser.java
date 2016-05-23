package wx.weixin.pay;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyStore;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.jdog.frameworks.encode.MD5;

import wx.weixin.data.ParametersAPI;
import wx.weixin.data.UrlRef;
import wx.weixin.util.MessageUtil;
import wx.weixin.util.WxLog;

/**
 * 微信支付企业向微信个人送钱，这叫钱多，送我也行
 * 
 * @author Administrator
 *
 */
public class WxpayMainEnterpriseToUser {

	
	public static void main(String[] args) throws Exception {
		
		String openid = ParametersAPI.testopenid;
		String partner_trade_no = "1102";
		String amount = "100";
		String re_user_name = "王明月";
		
		Map<String,String>  ret = enterprisePay(partner_trade_no, amount, re_user_name, openid);
		if(ret!=null){
		 for (Map.Entry entry : ret.entrySet()) {
	            System.out.println(entry.getKey() + ", " + entry.getValue());
	        }
		}
	}
	
	/**
	 * 
	 * Response content: <xml>
		<return_code><![CDATA[SUCCESS]]></return_code>
		<return_msg><![CDATA[]]></return_msg>
		<mch_appid><![CDATA[wxb5d02bb84c3b9f56]]></mch_appid>
		<mchid><![CDATA[1261985801]]></mchid>
		<device_info><![CDATA[1001]]></device_info>
		<nonce_str><![CDATA[1442380340]]></nonce_str>
		<result_code><![CDATA[SUCCESS]]></result_code>
		<partner_trade_no><![CDATA[1102]]></partner_trade_no>
		<payment_no><![CDATA[1000018301201509160719172781]]></payment_no>
		<payment_time><![CDATA[2015-09-16 13:15:11]]></payment_time>
		</xml>
	 * 
	 * 
	 */
	/**
	 * 支付回调教研
	 * @param ret
	 * @return
	 */
	public static boolean payAuth(Map<String,String> ret){
		if(ret ==null){
			return false;
		}
		if(ret.get("return_code")!=null && ret.get("return_code").equals("SUCCESS") && ret.get("mchid")!=null && ret.get("mchid").equals(ParametersAPI.mch_id)){
			return true;
		}
		return false;
	}
	
	public static String payMchid(Map<String,String> ret){
		if(ret!=null){
			return ret.get("mchid");
		}
		return null;
	}
	
	public static String payPartner_trade_no(Map<String,String> ret){
		if(ret!=null){
			return ret.get("partner_trade_no");
		}
		return null;
	}
	
	public static String payPayment_no(Map<String,String> ret){
		if(ret!=null){
			return ret.get("payment_no");
		}
		return null;
	}
	
	//return_msg
	public static String payReturn_msg(Map<String,String> ret){
		if(ret!=null){
			return ret.get("return_msg");
		}
		return null;
	}
	
	public static Map<String,String> enterprisePay(String partner_trade_no,String amount,String re_user_name,String openid) throws Exception{
		
		WxEnterprisePay wx = new WxEnterprisePay();
		String nonce_str = WxpayToolsUtil.create_timestamp();
		wx.setNonce_str(nonce_str);
		wx.setDevice_info(ParametersAPI.device_info);
		wx.setPartner_trade_no(partner_trade_no);
		wx.setAmount(amount);
		wx.setCheck_name("NO_CHECK");
		wx.setRe_user_name(re_user_name);
		wx.setOpenid(openid);
		wx.setDesc("微信提现");
		String xml = WxEnterprisePay.createXml(wx);
		Map<String, String>  ret = WxEnterprisePay.sendPayMsg(xml);
		return ret;
	}
	
	
}

class WxEnterprisePay{
	private String mch_appid;
	private String mchid;
	private String device_info;
	private String nonce_str;
	private String sign;
	private String partner_trade_no;
	private String openid;
	private String check_name;
	private String re_user_name;
	private String amount;
	private String desc;
	private String spbill_create_ip;
	
	/**
	 * 企业支付订单提交
	 * @param xml
	 * @return
	 */
	public static Map<String, String> sendPayMsg(String xml) throws Exception{
		
	       
	      	//指定读取证书格式为PKCS12
	        KeyStore keyStore = KeyStore.getInstance("PKCS12");
	        //读取本机存放的PKCS12证书文件
	        //D:\programer\workspace8\121shopping\src\main\webapp\WEB-INF\classes
	        ///usr/project/shopping/WEB-INF/classes
	        String path = ParametersAPI.certpath;
	        FileInputStream instream = new FileInputStream(new File(path));
	        try {
	        //指定PKCS12的密码(商户ID)
	        keyStore.load(instream, ParametersAPI.mch_id.toCharArray());
	        } finally {
	        instream.close();
	        }
	        SSLContext sslcontext = SSLContexts.custom()
	        .loadKeyMaterial(keyStore, ParametersAPI.mch_id.toCharArray()).build();
	        //指定TLS版本
	        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
	        sslcontext,new String[] { "TLSv1" },null,
	        SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
	        //设置httpclient的SSLSocketFactory
	        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
			String url = UrlRef.ENTERPRISEPAY;
	        HttpPost httppost = new HttpPost(url);  
	       
	        try {  
	            StringEntity entitys = new StringEntity(xml, ContentType.create("plain/text", Consts.UTF_8));
	            httppost.setEntity(entitys);

	            CloseableHttpResponse response = httpclient.execute(httppost);  
	            String retxml = "";
	            try {  
	                HttpEntity entity = response.getEntity();  
	                if (entity != null) {  
	                	
	                	retxml =retxml+EntityUtils.toString(entity, "UTF-8");
	                    System.out.println("--------------------------------------");  
	                    System.out.println("Response content: " +retxml);  
	                    System.out.println("--------------------------------------");  
	                    
	                }  
	                if(ParametersAPI.log){
	                	System.out.println("retxml:"+retxml);
	                }
	                Map<String, String> ret = MessageUtil.praseXml(retxml);
                    return ret;
	            } finally {  
	                response.close();  
	            }  
	        } catch (ClientProtocolException e) {  
	            e.printStackTrace();  
	        } catch (UnsupportedEncodingException e1) {  
	            e1.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } finally {  
	            // 关闭连接,释放资源    
	            try {  
	                httpclient.close();  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	            }  
	        }  
	        	
//			String url = UrlRef.ENTERPRISEPAY;
//			String s = CommonUtil.httpsRequestXml(url, "POST", xml);
//			if(s !=null){
//			Map<String, String> ret = MessageUtil.praseXml(s);
//			return ret;
//			}
			//return null;
			
			return null;
	}
	/**
	 * 生成发送的xml 
	 * @param wx
	 * @return
	 */
	public static String createXml(WxEnterprisePay wx){
		String mch_appid= ParametersAPI.APPID;
		String mchid = ParametersAPI.mch_id;
		String ip = ParametersAPI.spbill_ip;
		String sign = createSign(wx);
		String xml = "<xml>\n" +
					"<amount>"+wx.getAmount()+"</amount>\n" +
					"<check_name>"+wx.getCheck_name()+"</check_name>\n" +
					"<device_info>"+wx.getDevice_info()+"</device_info>\n" +
					"<desc>"+wx.getDesc()+"</desc>\n" +
					"<mch_appid>"+mch_appid+"</mch_appid>\n"+
					"<mchid>"+mchid+"</mchid>\n" +
					"<nonce_str>"+wx.getNonce_str()+"</nonce_str>\n" +
					"<openid>"+wx.getOpenid()+"</openid>\n" +
					"<partner_trade_no>"+wx.getPartner_trade_no()+"</partner_trade_no>\n" +
					"<re_user_name>"+wx.getRe_user_name()+"</re_user_name>\n" +
					"<spbill_create_ip>"+ip+"</spbill_create_ip>\n" +
					"<sign>"+sign+"</sign>\n" +
					"</xml>";
		WxLog.p(xml);
		
		return xml;
	}
	
	/**
	 * 获取加密sign
	 * @param wx
	 * @return
	 */
	public static String createSign(WxEnterprisePay wx){
		
		String amount = wx.getAmount();
		String check_name = wx.getCheck_name();
		String device_info = wx.getDevice_info();
		String desc = wx.getDesc();
		String nonce_str = wx.getNonce_str();
		String mch_appid = ParametersAPI.APPID;
		String mchid = ParametersAPI.mch_id;
		String openid = wx.getOpenid();
		String partner_trade_no = wx.getPartner_trade_no();
		String re_user_name = wx.getRe_user_name();
		String spbill_create_ip =ParametersAPI.spbill_ip;
		String key = ParametersAPI.key;
	
		String stringA = "amount="+amount+"&check_name="+check_name+
				"&desc="+desc+"&device_info="+device_info +"&mch_appid="+mch_appid+"&mchid="+mchid+"&nonce_str="+nonce_str
				+"&openid="+openid+"&partner_trade_no="+partner_trade_no+"&re_user_name="+re_user_name+"&spbill_create_ip="+spbill_create_ip;
		
		String stringSignTemp = stringA + "&key=" + key;
		String sign = MD5.digest(stringSignTemp).toUpperCase();
		WxLog.p(stringSignTemp);
		WxLog.p(sign);
		return sign;
	}
	
	public String getMch_appid() {
		return mch_appid;
	}
	public void setMch_appid(String mch_appid) {
		this.mch_appid = mch_appid;
	}
	public String getMchid() {
		return mchid;
	}
	public void setMchid(String mchid) {
		this.mchid = mchid;
	}
	public String getDevice_info() {
		return device_info;
	}
	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getPartner_trade_no() {
		return partner_trade_no;
	}
	public void setPartner_trade_no(String partner_trade_no) {
		this.partner_trade_no = partner_trade_no;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getCheck_name() {
		return check_name;
	}
	public void setCheck_name(String check_name) {
		this.check_name = check_name;
	}
	public String getRe_user_name() {
		return re_user_name;
	}
	public void setRe_user_name(String re_user_name) {
		this.re_user_name = re_user_name;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}
	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}
	
	
}
