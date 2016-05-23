package wx.weixin.bean.message;

/**
 * 回复视频消息 的 视频
 * @author Jdog.Asher
 *
 * @date 2014年8月24日 下午5:03:24
 */
public class Video {
	
	public Video(String mediaId, String title, String desc) {
		this.MediaId = mediaId;
		this.Title = title;
		this.Description = desc;
	}

	private String MediaId;
	private String Title;
	private String Description;
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	
	
}
