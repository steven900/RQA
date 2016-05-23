package wx.weixin.bean;

/**
 * 视图型菜单按钮
 * @author Jdog.Asher
 * @Date 2014-8-15
 */
public class ViewButton extends Buttonw {
	
	public ViewButton(String name, String type, String url) {
		super(name);
		this.type = type;
		this.url = url;
	}

	private String type;
	private String url;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
