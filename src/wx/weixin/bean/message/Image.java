package wx.weixin.bean.message;

public class Image {
	
	public Image(String mediaId) {
		this.MediaId = mediaId;
	}

	private String MediaId;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String MediaId) {
		this.MediaId = MediaId;
	}

}
