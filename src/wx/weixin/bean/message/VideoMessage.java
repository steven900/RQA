package wx.weixin.bean.message;



/**
 * 视频消息
 * @author Jdog.Asher
 * @Date 2014-8-12
 */
public class VideoMessage extends BaseMessage {

	private static final long serialVersionUID = 4185692408606258136L;

	private String MediaId;
	private String ThumbMediaId;
	
	private Video Video;

	public Video getVideo() {
		return Video;
	}

	public void setVideo(Video video) {
		Video = video;
	}

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
