package nc.utils.commonplugin.PushResponse;

public class TokenVO {
	private String access_token;//接口令牌
	private String expire;//有效期，单位秒
	
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getExpire() {
		return expire;
	}
	public void setExpire(String expire) {
		this.expire = expire;
	}
}
