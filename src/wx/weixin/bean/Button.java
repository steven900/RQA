package wx.weixin.bean;

import java.util.List;


public class Button {

	public Button(String type, String name, String key, String url, List<Button> subButtons) {
		this.type = type;
		this.name = name;
		this.key = key;
		this.url = url;
		this.sub_button = subButtons;
	}
	
	private String type; // 必填 菜单的响应动作类型，目前有click、view两种类型
	private String name; // 必填 菜单标题，不超过16个字节，子菜单不超过40个字节
	private String key; // click类型必须	 菜单KEY值，用于消息接口推送，不超过128字节
	private String url; // view类型必须 网页链接，用户点击菜单可打开链接
	private List<Button> sub_button; // 子按钮
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<Button> getSub_button() {
		return sub_button;
	}
	public void setSub_button(List<Button> sub_button) {
		this.sub_button = sub_button;
	}
	
}
