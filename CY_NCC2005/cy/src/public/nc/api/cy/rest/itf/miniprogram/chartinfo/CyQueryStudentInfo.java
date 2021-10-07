package nc.api.cy.rest.itf.miniprogram.chartinfo;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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
import nc.vo.cy.stumarkdata.CurriculumInfo;
import nc.vo.cy.stumarkdata.CurriculumMsg;
import nc.vo.cy.stumarkdata.StudentInfoVO;
import nccloud.itf.cy.cy.IStumarkdatahvoMaintain;

public class CyQueryStudentInfo extends MiniProgramServlet implements IHttpServletAdaptor{

	@Override
	public void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		super.virtualLogin();
		String resp = "";
		
		try {
			InputStream is = request.getInputStream();
			DataInputStream input = new DataInputStream(is);
			String requestJson = XmlUtils.parseRequst(request);
			StudentQueryPOJO queryvo = (StudentQueryPOJO) JSONTool.getObjByJson(requestJson, StudentQueryPOJO.class);
			String idcard = queryvo.getIdcard();// 学生身份证号
			//根据学生身份证号查询返回信息
			StudentInfoVO studentvo = CyCommonUtils.getInstance(IStumarkdatahvoMaintain.class)
					.queryStudentInfos(idcard);
			
			if (MMValueCheck.isNotEmpty(studentvo)) {
				resp = ResponsePOJO.toRespObj(studentvo, "200", "查询成功");
			} else {
				resp = ResponsePOJO.toRespObj(null, "400", "学生档案查询为空");
			}
		}catch (Exception e) {
			resp = ResponsePOJO.toRespObj(null, "400", "查询失败：" + e.getMessage());
		}finally {
			CyCommonUtils.logout();
		}
		response.getOutputStream().write(resp.getBytes());
	}

}
