package wx.weixin.pay;

import java.util.Map;
import java.util.UUID;

import com.jdog.frameworks.encode.MD5;

import wx.weixin.data.ParametersAPI;
import wx.weixin.util.CommonUtil;
import wx.weixin.util.MessageUtil;

/**
 * 微信下单接口
 * @author Administrator
 *
 */
public class WxpayOrderUtil {


		/**
		 * 支付下单 oifqzuAZGMBgLU-qzZutWYdtujcY
		 * 
		 * @param args
		 *            908030B770C0533707A63ECEF0EBC05C 1439258104 <xml>
		 *            <appid>wx2421b1c4370ec43b</appid> <attach>支付测试</attach>
		 *            <body>JSAPI支付测试</body> <mch_id>10000100</mch_id>
		 *            <nonce_str>1add1a30ac87aa2db72f57a2375d8fec</nonce_str>
		 *            <notify_url
		 *            >http://wxpay.weixin.qq.com/pub_v2/pay/notify.v2.php
		 *            </notify_url> <openid>oUpF8uMuAJO_M2pxb1Q9zNjWeS6o</openid>
		 *            <out_trade_no>1415659990</out_trade_no>
		 *            <spbill_create_ip>14.23.150.211</spbill_create_ip>
		 *            <total_fee>1</total_fee> <trade_type>JSAPI</trade_type>
		 *            <sign>0CB01533B8C1EF103065174F50BCA001</sign> </xml>
		 */
		public static void main(String[] args) {

//			String appid = "wxad3652f31f1954fb";
//			String mch_id = "1248164601";
//			String openid = "oifqzuAZGMBgLU-qzZutWYdtujcY"; //
//			String key = "jiaxingjiantaoshichang8266778800"; // 商户id
			
			String  openid = "ofT_PvxMPteGcmMuWSctqn6I-U7Q";
			String appid="wxb5d02bb84c3b9f56";
			String mch_id = "1261985801";
			String key = "jiaxingjiantaoshichang8266778800"; // 商户id
				
			
			String total_fee = "1"; // 费用
			String out_trade_no = "91129604"; // 商户交易号 加密可解密
			String body = "body_tesy";
			String device_info = "1000";
			String nonce_str = create_timestamp();
			String notify_url = "http://121.orz-tech.com/wxpay/node.do";
			String spbill_create_ip = "101.227.248.117"; // 创建ip
			String attach = "微信支付";
			downorderStr(appid, body, device_info, mch_id, nonce_str, out_trade_no,
					spbill_create_ip, total_fee, key, notify_url, openid, attach,0);
			
		}

		
		/**
		 * 下单方法
		 * @param appid
		 * @param body
		 * @param device_info
		 * @param mch_id
		 * @param nonce_str
		 * @param out_trade_no
		 * @param spbill_create_ip
		 * @param total_fee
		 * @param key
		 * @param notify_url
		 * @param openid
		 * @param attach
		 * @return
		 */
		public static String order( String out_trade_no, String total_fee,String openid,String body, String attach){
			
			String appid = ParametersAPI.APPID;
			String nonce_str = create_timestamp();
			String device_info = ParametersAPI.device_info;
			String key = ParametersAPI.key;
			String mch_id = ParametersAPI.mch_id;
			String spbill_create_ip = ParametersAPI.spbill_ip;
			String notify_url = ParametersAPI.notify_url;
			
			String[] str =  downorderStr(appid, body, device_info, mch_id, nonce_str, out_trade_no,
					spbill_create_ip, total_fee, key, notify_url, openid, attach,0);
			if(str !=null && str.length ==3){
				return str[1];
			}
			return null;
		}
		
		
		
		
		public static String[] downorderStr(String appid, String body,
				String device_info, String mch_id, String nonce_str,
				String out_trade_no, String spbill_create_ip, String total_fee,
				String key, String notify_url, String openid, String attach,int flag){
			
			String tradeType = "NATIVE"; //JSAPI
			if(flag==0){
				tradeType = "JSAPI"; //JSAPI
			}
			String[] data = new String[3];
			String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
			String sign = createordersign(appid, attach, body, device_info, mch_id,
					nonce_str, notify_url, openid, out_trade_no, spbill_create_ip,
					total_fee, tradeType, key,flag);
			String xml = getPayXml(appid, attach, body, mch_id, nonce_str,
					notify_url, openid, out_trade_no, spbill_create_ip, total_fee,
					tradeType, sign,flag);
			 System.out.println(xml);
			String s = CommonUtil.httpsRequestXml(url, "POST", xml);
			Map<String, String> ret = MessageUtil.praseXml(s);
		   for (Map.Entry entry : ret.entrySet()) {
	            System.out.println(entry.getKey() + ", " + entry.getValue());
	        }
			String payid = ret.get("prepay_id");
			String code_url = ret.get("code_url"); //支付链接
			
			data[0]=sign;
			data[1]=payid;
			data[2]=code_url;
			return data;
		}
		/**
		 * 下订单主方法
		 * 
		 * @param appid
		 * @param body
		 * @param device_info
		 * @param mch_id
		 * @param nonce_str
		 * @param out_trade_no
		 * @param ip
		 * @param fee
		 * @param key
		 * @return
		 */
		public static String downorder(String appid, String body,
				String device_info, String mch_id, String nonce_str,
				String out_trade_no, String spbill_create_ip, String total_fee,
				String key, String notify_url, String openid, String attach) {
			String[] data = downorderStr(appid, body, device_info, mch_id, nonce_str, out_trade_no, spbill_create_ip, total_fee, key, notify_url, openid, attach,1);
			return data[1];
		}

		/**
		 * 生成
		 * 
		 * @param notify_url
		 * @return
		 */
		public static String createordersign(String appid, String attach,
				String body, String device_info, String mch_id, String nonce_str,
				String notify_url, String openid, String out_trade_no, String ip,
				String fee, String trade_type, String key,int flag) {
		//
			String openkey = "&openid=" + openid; //JSAPI支付
			if(flag!=0){
				openkey = "";
			}
			String xpkey = "appid=" + appid + "&attach=" + attach + "&body=" + body
					+ "&mch_id=" + mch_id + "&nonce_str=" + nonce_str
					+ "&notify_url=" + notify_url + openkey+"&out_trade_no=" + out_trade_no + "&spbill_create_ip=" + ip
					+ "&total_fee=" + fee + "&trade_type=" + trade_type + "&key="
					+ key;
			// System.out.println(xpkey);
			String sign = MD5.digest(xpkey).toUpperCase();
			// System.out.println("sign:"+sign);
			return sign;
		}

		public static String getPayXml(String appid, String attach, String body,
				String mch_id, String nonce_str, String notify_url, String openid,
				String out_trade_no, String spbill_create_ip, String total_fee,
				String trade_type, String sign,int flag) {

			StringBuffer buf = new StringBuffer("<xml>");
			xmlappender("appid", appid, buf);
			xmlappender("attach", attach, buf);
			xmlappender("body", body, buf);
			xmlappender("mch_id", mch_id, buf);
			xmlappender("nonce_str", nonce_str, buf);
			xmlappender("notify_url", notify_url, buf);
			if(flag==0){
				xmlappender("openid", openid, buf);
			}
			xmlappender("out_trade_no", out_trade_no, buf);
			xmlappender("spbill_create_ip", spbill_create_ip, buf);
			xmlappender("total_fee", total_fee, buf);
			xmlappender("trade_type", trade_type, buf);
			xmlappender("sign", sign, buf);
			buf.append("</xml>");
			return buf.toString();
		}

		public static void xmlappender(String name, String value, StringBuffer buf) {
			if (value != null) {

				buf.append("<" + name + ">" + value + "</" + name + ">\n");
			}
		}

		public static String create_nonce_str() {
			return UUID.randomUUID().toString();
		}

		public static String create_timestamp() {
			return Long.toString(System.currentTimeMillis() / 1000);
		}

	
}
