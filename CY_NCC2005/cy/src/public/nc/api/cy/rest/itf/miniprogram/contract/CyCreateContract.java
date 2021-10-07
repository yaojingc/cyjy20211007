package nc.api.cy.rest.itf.miniprogram.contract;

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
import nc.vo.cy.creditcontract.pojo.CContractCreateParamPojo;
import nc.vo.cy.pojo.ResponsePOJO;
import nc.vo.cy.studentfile.StudentBindVO;
import nc.vo.pub.BusinessException;
import nccloud.api.rest.utils.NCCRestUtils;
import nccloud.itf.cy.cy.ICreditcontracthvoMaintain;

/**
 * NCC虚拟合同创建
 * 
 * @param requestJson 
 * 
 * @return
 */
public class CyCreateContract extends MiniProgramServlet implements IHttpServletAdaptor {

	@Override
	public void doAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		super.virtualLogin();
		InputStream is = request.getInputStream();
		DataInputStream input = new DataInputStream(is);
		String requestJson = XmlUtils.parseRequst(request);
		String resp = "";
		try {
			
			StudentBindVO contractCreateVO = (StudentBindVO) JSONTool.getObjByJson(requestJson, StudentBindVO.class);

//			CContractCreateParamPojo contractCreateVO = (CContractCreateParamPojo) JSONTool.getObjByJson2(requestJson,
//					CContractCreateParamPojo.class);

			CyCommonUtils.getInstance(ICreditcontracthvoMaintain.class).createContract(contractCreateVO);
			resp = ResponsePOJO.toRespObj(null, "200", "合同创建成功");
		} catch (BusinessException e) {
			resp = ResponsePOJO.toRespObj(null, "400", "合同创建失败：" + e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
		response.getOutputStream().write(resp.getBytes());
	}
}
