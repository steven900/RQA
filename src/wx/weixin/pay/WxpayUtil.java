package wx.weixin.pay;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.jdog.frameworks.encode.MD5;

import wx.weixin.data.ParametersAPI;

/**
 * 微信支付接口
 * @author Administrator
 *
 */
public class WxpayUtil {

	
	/**
	 * 
	 * 
	 签名算法 签名生成的通用步骤如下：
	 * 第一步，设所有发送或者接收到的数据为集合M，将集合M内非空参数值的参数按照参数名ASCII码从小到大排序（字典序
	 * ），使用URL键值对的格式（即key1=value1&key2=value2…）拼接成字符串stringA。 特别注意以下重要规则： ◆
	 * 参数名ASCII码从小到大排序（字典序）； ◆ 如果参数的值为空不参与签名； ◆ 参数名区分大小写； ◆
	 * 验证调用返回或微信主动通知签名时，传送的sign参数不参与签名，将生成的签名与该sign值作校验。 ◆
	 * 微信接口可能增加字段，验证签名时必须支持增加的扩展字段
	 * 第二步，在stringA最后拼接上key得到stringSignTemp字符串，并对stringSignTemp进行MD5运算
	 * ，再将得到的字符串所有字符转换为大写，得到sign值signValue。
	 * key设置路径：微信商户平台(pay.weixin.qq.com)-->账户设置-->API安全-->密钥设置 举例： 假设传送的参数如下：
	 * appid： wxd930ea5d5a258f4f mch_id： 10000100 device_info： 1000 body： test
	 * nonce_str： ibuaiVcKdpRxkhJA 第一步：对参数按照key=value的格式，并按照参数名ASCII字典序排序如下：
	 * stringA=
	 * "appid=wxd930ea5d5a258f4f&body=test&device_info=1000&mch_id=10000100&nonce_str=ibuaiVcKdpRxkhJA"
	 * ; 第二步：拼接API密钥：
	 * stringSignTemp="stringA&key=192006250b4c09247ec02edce69f6a2d"
	 * sign=MD5(stringSignTemp).toUpperCase()="9A0A8659F005D6984697E2CA0A9CF3B7"
	 * 
	 * 
	 * 支付是加密算法
	 */
	public static void main(String[] args) {

		String appId = "wxad3652f31f1954fb";
		String nonce_str = create_timestamp();
		String pk = "prepay_id=wx201508271620566ba86ba5120156351687";
		String signType = "MD5";
		String timeStamp = create_timestamp();
		String key = "jiaxingjiantaoshichang8266778800";

//		String stringA = "appId=" + appId + "&nonceStr=" + nonce_str
//				+ "&package=" + pk + "&signType=" + signType + "&timeStamp="
//				+ timeStamp;
//		String stringSignTemp = stringA + "&key=" + key;
//		String sign = MD5.digest(stringSignTemp).toUpperCase();
//		System.out.println(sign);
//		System.out.println(create_timestamp());
//		System.out.println(nonce_str);
		Map<String,String> ret=createPaySign(appId, nonce_str, pk, signType, key, timeStamp);
		   for (Map.Entry entry : ret.entrySet()) {
	            System.out.println(entry.getKey() + ", " + entry.getValue());	        }
	}

	/**
	 * 支付实力
	 * @param appId
	 * @param nonce_str
	 * @param pk
	 * @param signType
	 * @param key
	 * @param timeStamp
	 * @return
	 */
	
	public static Map<String,String> pay(String pk){
		String appId = ParametersAPI.APPID;
		String key = ParametersAPI.key;
		String nonce_str = create_timestamp();
		String signType = "MD5";
		String timeStamp = create_timestamp();
		return createPaySign(appId, nonce_str, pk, signType, key, timeStamp);
	}
	
	
	/**
	 * 
	 * @param appId
	 * @param nonce_str
	 * @param pk
	 * @param signType
	 * @param key
	 * @return
	 */
	public static Map<String,String> createPaySign(String appId,String nonce_str,
			String pk,String signType,String key,String timeStamp){
		pk = "prepay_id="+pk;
		Map<String,String> map = new HashMap<String,String>();
		String stringA = "appId=" + appId + "&nonceStr=" + nonce_str
				+ "&package=" + pk + "&signType=" + signType + "&timeStamp="
				+ timeStamp;
		String stringSignTemp = stringA + "&key=" + key;
		String sign = MD5.digest(stringSignTemp).toUpperCase();
		map.put("sign", sign);
		map.put("timeStamp", timeStamp);
		map.put("nonceStr", nonce_str);
		map.put("payid",pk);
		return map;
	}
	
	public static String create_nonce_str() {
		return UUID.randomUUID().toString();
	}

	private static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}

	
}
