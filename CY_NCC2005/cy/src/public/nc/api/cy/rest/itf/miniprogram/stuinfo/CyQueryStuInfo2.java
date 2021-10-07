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
import nc.vo.cy.studentfile.pojo.StudentQueryPOJO;
import nc.vo.pub.BusinessException;
import nccloud.itf.cy.cy.IStudenthvoMaintain;

/**
 * 根据身份证号查询学生学生档案
 * 
 * 入参只有身份证号
 */
public class CyQueryStuInfo2 extends MiniProgramServlet implements IHttpServletAdaptor {
	
	@Override
	public void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		super.virtualLogin();
		
		InputStream is = request.getInputStream();
		DataInputStream input = new DataInputStream(is);
		String requestJson = XmlUtils.parseRequst(request);
		
		StudentQueryPOJO queryvo = (StudentQueryPOJO) JSONTool.getObjByJson(requestJson, StudentQueryPOJO.class);
		String idcard = queryvo.getIdcard();// 学生身份证号
		String resp = "";
		try {
			queryvo = CyCommonUtils.getInstance(IStudenthvoMaintain.class).queryStudentByIdcard2(idcard);
			if (MMValueCheck.isNotEmpty(queryvo)) {
				resp = ResponsePOJO.toRespObj(queryvo, "200", "查询成功");
			} else {
				resp = ResponsePOJO.toRespObj(null, "400", "学生档案查询为空");
			}
		} catch (BusinessException e) {
			resp = ResponsePOJO.toRespObj(null, "400", "学生档案查询异常");
		} finally {
			CyCommonUtils.logout();
		}
		response.getOutputStream().write(resp.getBytes());
	}
}
