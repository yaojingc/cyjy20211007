package nc.api.cy.rest.itf.miniprogram.stuinfo;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nc.api.cy.rest.itf.miniprogram.common.MiniProgramServlet;
import nc.bs.framework.adaptor.IHttpServletAdaptor;
import nc.util.mmf.framework.base.MMValueCheck;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.commonplugin.JSONTool;
import nc.utils.swiftpass.XmlUtils;
import nc.vo.cy.pojo.ResponsePOJO;
import nc.vo.cy.studentfile.pojo.StudentBindQueryPOJO;
import nc.vo.pub.BusinessException;
import nccloud.itf.cy.cy.IStudenthvoMaintain;

/**
 * 绑定家长手机号
 */
public class CyBindParentPhone extends MiniProgramServlet implements IHttpServletAdaptor {

	@Override
	public void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		super.virtualLogin();
		
		InputStream is = request.getInputStream();
		DataInputStream input = new DataInputStream(is);
		String requestJson = XmlUtils.parseRequst(request);
		
		StudentBindQueryPOJO queryvo = (StudentBindQueryPOJO) JSONTool.getObjByJson(requestJson,
				StudentBindQueryPOJO.class);

		String idcard = queryvo.getIdcard();// 学生身份证号
		String parentphone = queryvo.getParentphone();// 家长手机号
		String openid = queryvo.getOpenid();// 微信的openid
		String status = "";	
		String resp = "";
		try {
			status = CyCommonUtils.getInstance(IStudenthvoMaintain.class).addToParentTab(idcard, parentphone, openid);
		} catch (BusinessException e) {
			e.printStackTrace();
		} finally {
			CyCommonUtils.logout();
		}
		if (MMValueCheck.isNotEmpty(status)) {// 无此学生需要在学生档案中创建此学生
			resp = ResponsePOJO.toRespObj(null, "200", "家长联系方式绑定成功");
			response.getOutputStream().write(ResponsePOJO.toRespObj(null, "200", "家长联系方式绑定成功").getBytes());
		} else {
			resp = ResponsePOJO.toRespObj(null, "400", "家长联系方式绑定失败，请联系管理员处理！ ");
		}
		response.getOutputStream().write(resp.getBytes());
	}
	
}
