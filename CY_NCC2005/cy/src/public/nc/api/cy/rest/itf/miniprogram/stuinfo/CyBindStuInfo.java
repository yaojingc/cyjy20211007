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
import nc.vo.cy.creditcontract.AggCreditcontractHVO;
import nc.vo.cy.pojo.ResponsePOJO;
import nc.vo.cy.studentfile.StudentBindVO;
import nccloud.itf.cy.cy.IStudenthvoMaintain;

/**
 * 家长端学生信息绑定
 * Edit By yaojing V20210830 此次修订： 不在生成学费收款合同，只创建学生档案
 * 
 * V20210923  此次修订将创建学生档案与生成学费收款合同封装在一个接口中，保证事务一致性
 */
public class CyBindStuInfo extends MiniProgramServlet implements IHttpServletAdaptor {

	@Override
	public void doAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		super.virtualLogin();
		String resp = "";
		try {
			InputStream is = request.getInputStream();
			DataInputStream input = new DataInputStream(is);
			String serObj = XmlUtils.parseRequst(request);
			
			if (CyCommonUtils.isEmpty(serObj)) {
				resp = ResponsePOJO.toRespObj(null, "400", "操作失败：信息为空");
				return;
			}
			StudentBindVO sbind = (StudentBindVO) JSONTool.getObjByJson(serObj, StudentBindVO.class);

			if (sbind.getSname().isEmpty() || sbind.getIdcard().isEmpty() || sbind.getClassno().isEmpty()
					|| sbind.getPhoneno().isEmpty()) {
				resp = ResponsePOJO.toRespObj(null, "400", "操作失败：信息不完整");
			}

			AggCreditcontractHVO[] result = CyCommonUtils.getInstance(IStudenthvoMaintain.class).bindStuManager(sbind);
			if (CyCommonUtils.isNotEmpty(result)) {
				resp = ResponsePOJO.toRespObj(null, "200", "操作成功");
			} else {
				resp = ResponsePOJO.toRespObj(null, "400", "操作失败");
			}
		} catch (Exception ex) {
			resp = ResponsePOJO.toRespObj(null, "400", "操作失败:" + ex.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
		response.getOutputStream().write(resp.getBytes());
	}
}
