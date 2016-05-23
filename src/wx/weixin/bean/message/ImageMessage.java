package wx.weixin.bean.message;




public class ImageMessage extends BaseMessage{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String picUrl;
	private Image Image;

	
	
	public Image getImage() {
		return Image;
	}

	public void setImage(Image image) {
		Image = image;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	
	
	
	
}
