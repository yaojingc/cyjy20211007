package nc.utils.miniprogram.pojo;

import com.alibaba.fastjson.annotation.JSONField;

public class WxMsgReturnPojo {
	
	// 发送方账号（openid）
	@JSONField(ordinal = 1)
	private String touser;
    // 消息类型 (默认值：text)
	@JSONField(ordinal = 2)
    private String msgtype;
    // 消息正文
	@JSONField(ordinal = 3)
    private MsgDetailContent text;
    
    
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	public MsgDetailContent getText() {
		return text;
	}
	public void setText(MsgDetailContent text) {
		this.text = text;
	}
    
    
    
}
