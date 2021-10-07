package nc.utils.commonplugin.PushRequest;

import java.util.List;

public class PushParamPOJO {
	//Y 必传属性
		private String tenantId;		//Y	租户ID
		private String appId;			//Y	应用ID，对应ykjId【接口常用字段说明】
		private String title;			//Y	文本内容
		private String content;			//Y	描述文本
		private String sendScope;		//Y	接收范围，list，根据填写yhtUserIds批量发送
		private List<String> yhtUserIds;//Y	sendScope=list时必须	sendScope=list时，接收人范围，yhtUserIds列表结构，用户ID列表
		private String url;				//N	移动端跳转的地址
		private String webUrl;			//N	web端跳转的地址
		private String highlight;		//N	需要高亮的文本内容
		private String miniProgramUrl;	//N	小程序url
		private String esnData;			//N	业务属性集合 其中fromId 标记已读时必传 业务id
		public String getTenantId() {
			return tenantId;
		}
		public void setTenantId(String tenantId) {
			this.tenantId = tenantId;
		}
		public String getAppId() {
			return appId;
		}
		public void setAppId(String appId) {
			this.appId = appId;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getWebUrl() {
			return webUrl;
		}
		public void setWebUrl(String webUrl) {
			this.webUrl = webUrl;
		}
		public String getSendScope() {
			return sendScope;
		}
		public void setSendScope(String sendScope) {
			this.sendScope = sendScope;
		}
		public List<String> getYhtUserIds() {
			return yhtUserIds;
		}
		public void setYhtUserIds(List<String> yhtUserIds) {
			this.yhtUserIds = yhtUserIds;
		}
		public String getHighlight() {
			return highlight;
		}
		public void setHighlight(String highlight) {
			this.highlight = highlight;
		}
		public String getMiniProgramUrl() {
			return miniProgramUrl;
		}
		public void setMiniProgramUrl(String miniProgramUrl) {
			this.miniProgramUrl = miniProgramUrl;
		}
		public String getEsnData() {
			return esnData;
		}
		public void setEsnData(String esnData) {
			this.esnData = esnData;
		}
}
