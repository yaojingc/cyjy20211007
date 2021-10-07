package nc.api.cy.rest.itf.miniprogram.sms;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nc.api.cy.rest.itf.miniprogram.common.MiniProgramServlet;
import nc.bs.framework.adaptor.IHttpServletAdaptor;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.commonplugin.JSONTool;
import nc.utils.smsutil.SmsUtils;
import nc.utils.swiftpass.XmlUtils;
import nc.vo.cy.pojo.ResponsePOJO;
import nc.vo.cy.sms.SmsReceivePojo;
import nccloud.impl.cy.cy.CyCommonSqlUtilImpl;
import nccloud.itf.cy.cy.ICyCommonSqlUtil;

/**
 * 短信发送接口 1.发送家长端登录验证码短信 2.向家长发送催费提醒短信
 * 
 * 此接口只接收入参为： 1.消息类型（参照自定义档案：） 2.消息正文 3.待发送手机号
 * 
 * @author yao
 */
public class CySmsSend extends MiniProgramServlet implements IHttpServletAdaptor {

	@Override
	public void doAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String resp = "";
		SmsReceivePojo smsReceivePojo = null;
		try {
			super.virtualLogin();
			InputStream is = request.getInputStream();
			DataInputStream input = new DataInputStream(is);
			String requestJson = XmlUtils.parseRequst(request);
			smsReceivePojo = (SmsReceivePojo) JSONTool.getObjByJson2(requestJson, SmsReceivePojo.class);

			// 消息正文(验证码 or 自定义短信文本)
			String smsContent = "";
			if (CyCommonUtils.isNotEmpty(smsReceivePojo.getSmsContent())) {
				smsContent = smsReceivePojo.getSmsContent();// 直接取自定义消息文本
			} else {
				smsContent = SmsUtils.generateVerificateCode();// 生成验证码
			}

			// 需要推送的手机号
			String[] phoneNumbers = smsReceivePojo.getPhoneNumbers();
			// 推送消息类型，这里直接定义为推送短信的模板id
			String smsTemplateCode = CyCommonUtils.getInstance(ICyCommonSqlUtil.class).queryDefdocByField("name", "code",
					smsReceivePojo.getSmsTemplateid(), "短信模板类型");

			String result = SmsUtils.sendSms(new String[] {smsContent}, phoneNumbers, smsTemplateCode);
			
			if("OK".equals(result)) {
				smsReceivePojo.setSmsContent(smsContent);
				resp = ResponsePOJO.toRespObj(smsReceivePojo, "200", "短信发送成功");
			}else{
				resp = ResponsePOJO.toRespObj(smsReceivePojo, "400", "短信发送发送异常：NOK");
			}
			
			
		} catch (Exception e) {
			resp = ResponsePOJO.toRespObj(smsReceivePojo, "400", "短信发送发送异常：" + e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
		response.getOutputStream().write(resp.getBytes());
	}

}
