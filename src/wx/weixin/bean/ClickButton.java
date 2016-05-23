package wx.weixin.bean;

/**
 * 点击型菜单按钮
 * @author Jdog.Asher
 * @Date 2014-8-15
 */
public class ClickButton extends Buttonw {
	
	public ClickButton(String name, String type, String key) {
		super(name);
		this.type = type;
		this.key = key;
	}

	private String type;
	private String key;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	
}
