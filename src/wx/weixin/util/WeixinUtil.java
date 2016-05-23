package wx.weixin.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.http.Consts;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import net.sf.json.JSONObject;
import wx.weixin.bean.Menu;
import wx.weixin.bean.Signature;
import wx.weixin.bean.WeixinOauth2Token;
import wx.weixin.data.ParametersAPI;
import wx.weixin.data.UrlRef;

public class WeixinUtil {

	/**
	 * 
	 * 支持http/https请求并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return String
	 */
	public static String httpRequest(String requestUrl, String requestMethod,
			String outputStr) {

		String jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
					.openConnection();
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			if (requestUrl.indexOf("https") != -1) {
				TrustManager[] tm = { new MyX509TrustManager() };
				SSLContext sslContext = SSLContext
						.getInstance("SSL", "SunJSSE");
				sslContext.init(null, tm, new java.security.SecureRandom());
				// 从上述SSLContext对象中得到SSLSocketFactory对象
				SSLSocketFactory ssf = sslContext.getSocketFactory();

				httpUrlConn.setSSLSocketFactory(ssf);
			}
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);
			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();
			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = buffer.toString();
		} catch (ConnectException ce) {
			System.out.println("Weixin server connection timed out.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	/**
	 * 检验微信签名，验证消息的真实性
	 * 
	 * @param signature
	 * @return 消息的真实性
	 */
	public static boolean ChackSignature(Signature signature) {
		if (signature != null && signature.getSignature() != null) {
			List<String> params = new ArrayList<String>();
			System.out.println("---------------");
			params.add(ParametersAPI.TOKEN);
			params.add(signature.getTimestamp());
			params.add(signature.getNonce());
			Collections.sort(params, new Comparator<String>() {
				public int compare(String o1, String o2) {
					return o1.compareTo(o2);
				}
			});
			// 2. 将三个参数字符串拼接成一个字符串进行sha1加密
			String temp = SHA1Util.encode(params.get(0) + params.get(1)
					+ params.get(2));
			if (temp.equals(signature.getSignature())) {
				return true;
			}
		}
		return false;

	}

	public static WeixinOauth2Token getOauth2AccessToken(String appId,
			String appSecret, String code) {

		WeixinOauth2Token wal = null;
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		requestUrl = requestUrl.replace("APPID", appId);
		requestUrl = requestUrl.replace("SECRET", appSecret);
		requestUrl = requestUrl.replace("CODE", code);
		JSONObject jsonObject = CommonUtil
				.httpsRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			wal = new WeixinOauth2Token();
			wal.setAccessToken(jsonObject.getString("access_token"));
		}
		return null;

	}

	/**
	 * 直接获取解析完成后的token
	 * @return
	 */
	public static String getPrasedAccessToken(){
		String access_token_json=WeixinUtil.getAccessTokenFromServers();
		return access_token_json;
	}
	
	/**
	 * 从微信端获取权限
	 */
	private static long expire_time = 0;
	private static String access_token = null;
	public static String getAccessTokenFromServers() {
		StringBuffer bufferRes = new StringBuffer();
		URL realUrl;
		try {
			realUrl = new URL(
					"https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
							+ ParametersAPI.APPID + "&secret="
							+ ParametersAPI.APPSECRET);
			HttpURLConnection conn = (HttpURLConnection) realUrl
					.openConnection();
			// 连接超时
			conn.setConnectTimeout(25000);
			// 读取超时 --服务器响应比较慢，增大时间
			conn.setReadTimeout(25000);
			conn.connect();
			// 获取URLConnection对象对应的输出流
			// OutputStreamWriter out = new
			// OutputStreamWriter(conn.getOutputStream());
			InputStream in = conn.getInputStream();
			BufferedReader read = new BufferedReader(new InputStreamReader(in,
					"UTF-8"));
			String valueString = null;
			while ((valueString = read.readLine()) != null) {
				bufferRes.append(valueString);
			}
			expire_time = new Date().getTime()/1000+7000;
			in.close();
			if (conn != null) {
				// 关闭连接
				conn.disconnect();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String access_token_json =  bufferRes.toString();
		JSONObject jsonObject = JSONObject.fromObject(access_token_json);
		access_token = jsonObject.getString("access_token");
		return access_token;
	}

	/**
	 * 获取接口请求地址
	 */
	public static String getWeixinUrl(String url, String access_token) {
		return url.replace("ACCESS_TOKEN", access_token);
	}

	public static WeixinOauth2Token refreshOauth2AccessToken(String appId,
			String appSecret, String code) {

		WeixinOauth2Token wat = null;
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		requestUrl = requestUrl.replace("APPID", appId);
		requestUrl = requestUrl.replace("SECRET", appSecret);
		requestUrl = requestUrl.replace("CODE", code);
		// JSONObject jsonObject=CommonUtil.httpsRequest(requestUrl,"GET",null);
		return wat;

	}

	/**
	 * 微信创建菜单
	 * 
	 * @return
	 */
	public static String MenuCreate(Menu menu) {
		String result = "";
		String line = "";
		String accessToken = WeixinUtil.getAccessTokenFromServers();
		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			String jsonMenu = JSONObject.fromObject(menu).toString();
			StringEntity entity = new StringEntity(jsonMenu,
					ContentType.create("plain/text", Consts.UTF_8));
			
			HttpPost post = new HttpPost(WeixinUtil.getWeixinUrl(
					UrlRef.CREATE_MENU, access_token));
			post.setEntity(entity);
			CloseableHttpResponse response = httpClient.execute(post);

			InputStream is = response.getEntity().getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			while ((line = br.readLine()) != null) {
				result += line;
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询菜单
	 */
	public static String getMenus() {
		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			String access_token = getAccessTokenFromServers();
			
			HttpGet get = new HttpGet(getWeixinUrl(UrlRef.QUERY_MENU,
					access_token));

			CloseableHttpResponse response = httpClient.execute(get);
			InputStream is = response.getEntity().getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String result = "";
			String line = "";
			while ((line = br.readLine()) != null) {
				result += line;
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除菜单
	 */
	public static String deleteMenus() {
		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			String access_token = getAccessTokenFromServers();
			HttpGet get = new HttpGet(getWeixinUrl(UrlRef.DELETE_MENU,
					access_token));

			CloseableHttpResponse response = httpClient.execute(get);
			InputStream is = response.getEntity().getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String result = "";
			String line = "";
			while ((line = br.readLine()) != null) {
				result += line;
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 订阅用户连接转换 ，三授权信息
	 * @param href
	 * @return
	 */
	public static String getBookUserInfo(String href){
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+ParametersAPI.APPID+"&redirect_uri="+href+"&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
		return url;
	}
	
	
	public static void main(String[] args) {
		String json = getAccessTokenFromServers();
		System.out.println(json);
		
		System.out.println(new Date().getTime()/1000);
	}
	

}
