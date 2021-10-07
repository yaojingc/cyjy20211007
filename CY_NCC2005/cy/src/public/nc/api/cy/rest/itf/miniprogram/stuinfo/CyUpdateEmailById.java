package nc.api.cy.rest.itf.miniprogram.stuinfo;

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
import nc.utils.swiftpass.XmlUtils;
import nc.vo.cy.pojo.ResponsePOJO;
import nc.vo.cy.studentfile.pojo.StudentQueryPOJO;
import nc.vo.pub.BusinessException;
import nccloud.itf.cy.cy.IStudenthvoMaintain;


/**
 * 更新绑定的邮箱 入参（idcard，email）
 */
public class CyUpdateEmailById extends MiniProgramServlet implements IHttpServletAdaptor {
	
	@Override
	public void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		super.virtualLogin();
		
		InputStream is = request.getInputStream();
		DataInputStream input = new DataInputStream(is);
		String requestJson = XmlUtils.parseRequst(request);
		
		StudentQueryPOJO queryvo = (StudentQueryPOJO) JSONTool.getObjByJson(requestJson, StudentQueryPOJO.class);
		String result = "";
		String resp = "";
		try {
			result = CyCommonUtils.getInstance(IStudenthvoMaintain.class).updateEmailByID(queryvo);
		} catch (BusinessException e) {
			resp = ResponsePOJO.toRespObj(null, "400", result + ",请联系管理员");
		} finally {
			CyCommonUtils.logout();
		}
		resp = ResponsePOJO.toRespObj(null, "200", result);
		// 响应返回
		response.getOutputStream().write(resp.getBytes());
	}

}
