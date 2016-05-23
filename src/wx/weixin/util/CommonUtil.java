package wx.weixin.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;
import wx.weixin.bean.WeixinOauth2Token;

public class CommonUtil {

	private static Logger log = LoggerFactory.getLogger(CommonUtil.class);

	// 获取凭证
	public final static String token_url = "https://aip.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	/**
	 * 发送请求 //
	 */
	public static JSONObject httpsRequest(String requestUrl,
			String requestMethod, String outputStr) {

		JSONObject jsonObject = null;
		try {
			// 创建sslcontext 对象， 并使用我们指定的信任 器初始化

			URL realUrl = new URL(requestUrl);
			HttpURLConnection conn = (HttpURLConnection) realUrl
					.openConnection();

			// 连接超时
			conn.setConnectTimeout(25000);
			// 读取超时 --服务器响应比较慢，增大时间
			conn.setReadTimeout(25000);
			HttpURLConnection.setFollowRedirects(true);
			// 请求方式
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			if (outputStr != null)
				conn.getOutputStream().write(outputStr.getBytes());
			conn.connect();

			StringBuffer bufferRes = new StringBuffer();
			InputStream in = conn.getInputStream();
			BufferedReader read = new BufferedReader(new InputStreamReader(in,
					"UTF-8"));
			String valueString = null;
			while ((valueString = read.readLine()) != null) {
				bufferRes.append(valueString);
			}

			jsonObject = JSONObject.fromObject(bufferRes.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonObject;
	}

	/**
	 * 访问返回xml
	 * @param requestUrl
	 * @param requestMethod
	 * @param outputStr
	 * @return
	 */
	public static String httpsRequestXml(String requestUrl,
			String requestMethod, String outputStr) {

		JSONObject jsonObject = null;
		try {
			// 创建sslcontext 对象， 并使用我们指定的信任 器初始化

			URL realUrl = new URL(requestUrl);
			HttpURLConnection conn = (HttpURLConnection) realUrl
					.openConnection();

			// 连接超时
			conn.setConnectTimeout(25000);
			// 读取超时 --服务器响应比较慢，增大时间
			conn.setReadTimeout(25000);
			HttpURLConnection.setFollowRedirects(true);
			// 请求方式
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			if (outputStr != null)
				conn.getOutputStream().write(outputStr.getBytes());
			conn.connect();

			StringBuffer bufferRes = new StringBuffer();
			InputStream in = conn.getInputStream();
			BufferedReader read = new BufferedReader(new InputStreamReader(in,
					"UTF-8"));
			String valueString = null;
			while ((valueString = read.readLine()) != null) {
				bufferRes.append(valueString);
			}
			//System.out.println(bufferRes.toString());

			// jsonObject=JSONObject.fromObject(bufferRes.toString());

			return bufferRes.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static WeixinOauth2Token getOthMethod(String code, String appi,
			String appsecret) {

		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

		requestUrl = requestUrl.replace("APPID", appi);
		requestUrl = requestUrl.replace("SECRET", appsecret);

		requestUrl = requestUrl.replace("CODE", code);

		WeixinOauth2Token wal = null;
		JSONObject jsonObject = getAskJSONObject(requestUrl);

		if (null != jsonObject) {
			wal = new WeixinOauth2Token();
			wal.setAccessToken(jsonObject.getString("access_token"));
			wal.setExpiresIn(jsonObject.getString("expires_in"));
			wal.setRefreshToken(jsonObject.getString("refresh_token"));
			wal.setOpenId(jsonObject.getString("openid"));
			wal.setScope(jsonObject.getString("scope"));
		}

		return wal;

	}

	public static JSONObject getAskJSONObject(String requestUrl) {

		try {
			URL realUrl = new URL(requestUrl);
			HttpURLConnection conn = (HttpURLConnection) realUrl
					.openConnection();

			// 连接超时
			conn.setConnectTimeout(25000);
			// 读取超时 --服务器响应比较慢，增大时间
			conn.setReadTimeout(25000);
			HttpURLConnection.setFollowRedirects(true);
			// 请求方式
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.connect();

			StringBuffer bufferRes = new StringBuffer();
			InputStream in = conn.getInputStream();
			BufferedReader read = new BufferedReader(new InputStreamReader(in,
					"UTF-8"));
			String valueString = null;
			while ((valueString = read.readLine()) != null) {
				bufferRes.append(valueString);
			}
			JSONObject jsonObject = JSONObject.fromObject(bufferRes.toString());

			return jsonObject;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	// public static UserInfoWeiXin getUserInfo(String accessToken, String
	// openId) {
	//
	// String
	// requestUrl="http://api.weixin.qq.com/sns/userinfo?access_token="+accessToken+"&openid="+openId;
	// JSONObject jsonObject=getAskJSONObject(requestUrl);
	//
	// UserInfoWeiXin uinfo=new UserInfoWeiXin();
	//
	//
	// if(null!=jsonObject){
	//
	// uinfo.setCity(jsonObject.getString("city"));
	// uinfo.setHeadimgurl(jsonObject.getString("headimgurl"));
	// uinfo.setCountry(jsonObject.getString("country"));
	// uinfo.setNickname(jsonObject.getString("nickname"));
	// uinfo.setSex(jsonObject.getString("sex"));
	// uinfo.setOpenId(jsonObject.getString("openid"));
	//
	// }
	//
	//
	//
	// return uinfo;
	// }
	//
}
