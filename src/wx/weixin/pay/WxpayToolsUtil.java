package wx.weixin.pay;

import java.util.UUID;

/**
 * 工具类
 * @author Administrator
 *
 */
public class WxpayToolsUtil {

	
	public static String create_nonce_str() {
		return UUID.randomUUID().toString();
	}

	public static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}
}
