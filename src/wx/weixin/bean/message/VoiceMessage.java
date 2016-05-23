package wx.weixin.bean.message;

/**
 * 语音消息
 * @author Jdog.Asher
 * @Date 2014-8-12
 */
public class VoiceMessage extends BaseMessage {

	private static final long serialVersionUID = -2991821898754846310L;

	private String MediaId;
	private String Format;
	private String Recognition;
	

	public String getMediaId() {
		return MediaId;
	}


	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}


	public String getFormat() {
		return Format;
	}


	public void setFormat(String format) {
		Format = format;
	}


	public String getRecognition() {
		return Recognition;
	}


	public void setRecognition(String recognition) {
		Recognition = recognition;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
