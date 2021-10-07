package nc.utils.miniprogram;

public class AccessToken {

	// token 凭证
    private String access_token;
    // 凭证有效时间 默认为7200s
    private long expires_in;
    // token失效的时间节点
    private long timeOut;
    
    
  //判断当前token是否已经超时
    public Boolean isTimeOut() {
        return System.currentTimeMillis() > timeOut;
    }
    
    
	public long getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(long expires_in) {
		this.expires_in = expires_in;
	}

    public AccessToken(String access_token, long expiresIn) {
        this.access_token = access_token;
        this.expires_in = expiresIn;
        this.timeOut = System.currentTimeMillis() + expiresIn * 1000;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public long getOutTime() {
        return timeOut;
    }

    public void setOutTime(long expiresIn) {
        this.timeOut = System.currentTimeMillis() + expiresIn * 1000;
    }
}
