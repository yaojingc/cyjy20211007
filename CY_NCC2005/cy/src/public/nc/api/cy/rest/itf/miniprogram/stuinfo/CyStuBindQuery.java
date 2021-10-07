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
import nc.vo.cy.studentfile.pojo.StudentBindQueryPOJO;
import nc.vo.pub.BusinessException;
import nccloud.itf.cy.cy.IStudenthvoMaintain;


/**
 * 查询学生信息是否绑定 :
 * 	1.需要查询学生档案是否绑定 
 */
public class CyStuBindQuery extends MiniProgramServlet implements IHttpServletAdaptor {
	
	@Override
	public void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		super.virtualLogin();
		
		InputStream is = request.getInputStream();
		DataInputStream input = new DataInputStream(is);
		String requestJson = XmlUtils.parseRequst(request);

		StudentBindQueryPOJO queryvo = (StudentBindQueryPOJO) JSONTool.getObjByJson(requestJson,
				StudentBindQueryPOJO.class);
		/*
		 * 首先查询改身份证号是否存在，在查询手机号和身份证是否绑定
		 */
		String idcard = queryvo.getIdcard();// 学生身份证号
		String parentphone = queryvo.getParentphone();// 家长手机号
		String openid = queryvo.getOpenid();
		String status = "";
		String resp = "";
		try {
			status = CyCommonUtils.getInstance(IStudenthvoMaintain.class).queryStudentByIdcard(idcard, parentphone,
					openid);
		} catch (BusinessException e) {
			resp = ResponsePOJO.toRespObj(null, "400", "系统异常");
			e.printStackTrace();
		} finally {
			CyCommonUtils.logout();
		}

		if (status.equals("-1")) {// 无此学生需要在学生档案中创建此学生
			resp = ResponsePOJO.toRespObj(null, status, "查无此学生");
		} else if (status.equals("0")) {
			resp = ResponsePOJO.toRespObj(null, status, "未绑定");
		} else if (status.equals("1")) {
			resp = ResponsePOJO.toRespObj(null, status, "已绑定");
		}
		response.getOutputStream().write(resp.getBytes());
	}

}
