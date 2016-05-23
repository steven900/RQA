package wx.weixin.bean.message;

import java.io.Serializable;

/**
 * 基础消息
 * @author Jdog.Asher
 * @Date 2014-8-12
 */
public class BaseMessage implements Serializable {

	private static final long serialVersionUID = -2861191404230066532L;
	private String ToUserName;
	private String FromUserName;
	private long CreateTime;
	private String MsgType;
	private String MsgId;
	
	

	public String getToUserName() {
		return ToUserName;
	}


	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}


	public String getFromUserName() {
		return FromUserName;
	}


	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}


	public long getCreateTime() {
		return CreateTime;
	}


	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}


	public String getMsgId() {
		return MsgId;
	}


	public void setMsgId(String msgId) {
		MsgId = msgId;
	}


	public String getMsgType() {
		return MsgType;
	}


	public void setMsgType(String msgType) {
		MsgType = msgType;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
