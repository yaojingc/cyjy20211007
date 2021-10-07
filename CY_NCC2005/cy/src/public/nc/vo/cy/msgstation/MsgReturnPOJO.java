package nc.vo.cy.msgstation;

import oracle.sql.BLOB;
import sun.misc.BASE64Encoder;

import java.util.Base64;
import nc.vo.pub.lang.UFDate;

public class MsgReturnPOJO {

	

		// 消息中转站主键
		private String pk_msgstation;

		// 消息接收端
		private String receiveport;

		// 消息接收人
		private String receiver;

		// 接收人身份证号
		private String receiverid;

		// 消息内容
//		private BLOB msg;
		
		// 消息内容
		private String msg;
		
		// 消息标题
		private String title;

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		// 接收时间
		private String receivetime;
	


		public String getPk_msgstation() {
			return pk_msgstation;
		}

		public void setPk_msgstation(String pk_msgstation) {
			this.pk_msgstation = pk_msgstation;
		}

		public String getReceiveport() {
			return receiveport;
		}

		public void setReceiveport(String receiveport) {
			this.receiveport = receiveport;
		}

		public String getReceiver() {
			return receiver;
		}

		public void setReceiver(String receiver) {
			this.receiver = receiver;
		}

		public String getReceiverid() {
			return receiverid;
		}

		public void setReceiverid(String receiverid) {
			this.receiverid = receiverid;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
	        this.msg = msg;
		}

		public String getReceivetime() {
			return receivetime;
		}

		public void setReceivetime(String receivetime) {
			this.receivetime = receivetime;
		}

}
