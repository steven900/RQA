package wx.weixin.data;

public class UrlRef {
	/**
	 * 创建菜单
	 */
	public static final String CREATE_MENU = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	
	/**
	 * 查询菜单
	 */
	public static final String QUERY_MENU = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
	
	/**
	 * 删除菜单
	 */
	public static final String DELETE_MENU = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	
	/**
	 * 客服消息发送 48小时内无限制发送消息
	 */
	public static final String CUSTOM_SEND = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
	
	/**
	 * 生成二维码
	 */
	public static final String CREATE_QRCODE = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";
	
	
	/**
	 * 二维码图片
	 */
	public static final String QRCODE_PIC = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
	
	/**
	 * 图片上传
	 */
	public static final String UPLOAD_IMAGE = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=ACCESS_TOKEN";
	
	/**
	 * 获取关注者信息
	 */
	public static final String USER_INFO = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
	/**
	 * 关注者列表 每次1万，多次拉取
	 */
	public static final String USER_LIST = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
	
	/**
	 * 创建分组
	 */
	public static final String CREATE_GROUP = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=ACCESS_TOKEN";
	
	/**
	 * 查询分组
	 */
	public static final String QUERY_GROUP = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=ACCESS_TOKEN";
	
	/**
	 * 查询用户所在分组
	 */
	public static final String QUERY_USER_GROUP = "https://api.weixin.qq.com/cgi-bin/groups/getid?access_token=ACCESS_TOKEN";
	
	/**
	 * 修改分组名
	 */
	public static final String MODIFY_GROUP_NAME = "https://api.weixin.qq.com/cgi-bin/groups/update?access_token=ACCESS_TOKEN";
	
	/**
	 * 移动用户
	 */
	public static final String MOVE_GROUP_MEMBER = "https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=ACCESS_TOKEN";
	
	/**
	 * 上传媒体文件
	 */
	public static final String UPLOAD_MEDIA = "http://file.api.weixin.qq.com/cgi-bin/media/upload?type=TYPE&access_token=ACCESS_TOKEN";
	
	/** 高级群发接口，一个用户，一个月只能接收4条 **/
	/**
	 * 上传图文
	 */
	public static final String UPLOAD_NEWS = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN";
	/**
	 * 根据分组进行群发
	 */
	public static final String GROUP_SENDALL = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN";
	/**
	 * OPENID list 群发
	 */
	public static final String OPENID_SENDALL = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=ACCESS_TOKEN";
	
	/**
	 * 微信企业支付
	 */
	public static final String ENTERPRISEPAY = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
	
}
