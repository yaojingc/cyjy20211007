package nc.vo.cy.bankcollflow.pojo;

import java.util.List;

public class ResponsePOJO {

	// 响应状态码
	private String code;
	// 响应说明
	private String msg;
	// 签名方式
	private String signType;
	// 签名
	private String sign;
	// 业务数据
	private List<ResponseDetailPOJO> data;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public List<ResponseDetailPOJO> getData() {
		return data;
	}

	public void setData(List<ResponseDetailPOJO> data) {
		this.data = data;
	}
}
