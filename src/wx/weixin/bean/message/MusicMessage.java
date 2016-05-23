package wx.weixin.bean.message;

import wx.weixin.bean.Music;


public class MusicMessage extends BaseMessage {

	private static final long serialVersionUID = 751096666939248523L;

	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music Music) {
		this.Music = Music;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
