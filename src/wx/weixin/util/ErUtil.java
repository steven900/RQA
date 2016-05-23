package wx.weixin.util;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;

import net.sf.json.JSONObject;

/**
 * 二维码
 * @author Moon 
 *
 */
public class ErUtil {

	
	public static String createWithEr(int code) {
		String access_token = WeixinUtil.getAccessTokenFromServers();
		String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+access_token;
		
		Map<String,Object> data = new HashMap<String,Object>();
		//{"action_name": "QR_LIMIT_SCENE", "action_info": {"scene": {"scene_id": 123}}}
		data.put("action_name", "QR_LIMIT_SCENE");
		
		Map<String,Object> data1 = new HashMap<String,Object>();
		data1.put("scene_id", code);
		Map<String,Object> data2 = new HashMap<String,Object>();
		data2.put("scene", data1);
		
		data.put("action_info", data2);
		
		
		System.out.println(JSONArray.toJSONString(data));
		
		
		JSONObject jsonObject = CommonUtil.httpsRequest(url, "POST",JSONArray.toJSONString(data));
		String ticket =null;
		if (null != jsonObject) {
			ticket = jsonObject.getString("ticket");
System.out.println(jsonObject);		
			return createWithErHHH(ticket);
		}
		return null;
	}
	
	

	public static String createWithErHHH(String  ticket) {
		String url = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+ticket;
		return url;
	}
	
	//https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET

	
	public static void main(String[] args) {
		System.out.println(createWithEr(239));
		//showqrcode
		//String code ="gQF88DoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL0FFVFlyOFBsV0VnaF80WC16R2k1AAIEKPwcVgMEAAAAAA==";
		//createWithErHHH(code);
		//https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQFN8DoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xLzNrVHg3UWZsZGtnUG8xdTY1V2k1AAIEBAodVgMEAAAAAA==
	}
}
