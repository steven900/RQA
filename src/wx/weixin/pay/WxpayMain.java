package wx.weixin.pay;

import java.util.Map;

import com.jdog.frameworks.encode.MD5;

import wx.weixin.data.ParametersAPI;

/**
 * 微信支付主要方法调用，其他几个方法不需要关心
 * @author Administrator
 *
 */
public class WxpayMain {

	public static void main(String[] args) {
		
		wxpay("1002","1", "ofT_PvxMPteGcmMuWSctqn6I-U7Q", "1231", "1232131");
	}
	
	/**
	 * 微信支付
	 * @param out_trade_no 订单号 唯一不重复
	 * @param total_fee  支付费用， 1 为 0.01元
	 * @param openid  支付时微信id
	 * @param body  主要商品
	 * @param attach 描述
	 * @return
	 */
	public static Map<String,String> wxpay(String out_trade_no,String total_fee,String openid,String body,String attach){
		
		
		//第一步下单  获取下单号
		String payid = WxpayOrderUtil.order(out_trade_no, total_fee, openid, body, attach);
		String keycrp= MD5.digest(ParametersAPI.mch_id+out_trade_no);//保存数据库，作为回调时的记录
		Map<String,String> ret = WxpayUtil.pay(payid);
		ret.put("keycrp", keycrp);
		if(ParametersAPI.log){
			 for (String key : ret.keySet()) {
		           System.out.println(key + "___" + ret.get(key));
		     }
		}
//		map.put("sign", sign);
//		map.put("timeStamp", timeStamp);
//		map.put("nonceStr", nonce_str);
//		map.put("payid",pk);
		return ret;
	}
	
	
	
}
