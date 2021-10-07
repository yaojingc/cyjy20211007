package nc.utils.commonplugin.PushResponse;

public class ResultPOJO <A>{
private String code;//结果码 200表示成功
	
	private A data;
	
	private String message;//结果信息，若有错误，该字段会返回具体错误信息

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public A getData() {
		return data;
	}

	public void setData(A data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
